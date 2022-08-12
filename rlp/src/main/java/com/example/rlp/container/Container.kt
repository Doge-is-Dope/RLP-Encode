package com.example.rlp.container

import com.example.rlp.RLPDecoding
import java.lang.RuntimeException
import java.lang.reflect.Field
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

interface Container<V> {
    val type: ContainerType
    fun asRaw(): Class<V>
    fun asCollection(): CollectionContainer<Collection<V>, V>
    fun asMap(): MapContainer<Map<*, V>, *, V>

    companion object {
        @JvmStatic
        fun fromField(field: Field): Container<*> {
            val container = fromType(field.genericType)
            var clazz: Class<*>? = null
            if (field.isAnnotationPresent(RLPDecoding::class.java)) {
                clazz = field.getAnnotation(RLPDecoding::class.java).`as`.javaObjectType
            }
            if (clazz == null || clazz == Void::class.java) return container
            if (!MutableCollection::class.java.isAssignableFrom(clazz) && !MutableMap::class.java.isAssignableFrom(
                    clazz
                )
            ) throw RuntimeException("@RLPDecoding.as must be a collection of map type while " + clazz.name + " found")
            if (container.type === ContainerType.RAW) throw RuntimeException("@RLPDecoding.as is used on collection or map typed field other than " + field.name)
            if (!field.type.isAssignableFrom(clazz)) throw RuntimeException("cannot assign " + clazz + " to " + field.type)
            if (container.type === ContainerType.COLLECTION) {
                container.asCollection().collectionType = clazz
            }
            if (container.type === ContainerType.MAP) {
                container.asMap().mapType = clazz
            }
            return container
        }

        // from class without generic variable
        @JvmStatic
        fun fromClass(clazz: Class<*>): Container<*> {
            if (MutableCollection::class.java.isAssignableFrom(clazz)) {
                if (!SUPPORTED_COLLECTIONS.contains(clazz)) throw RuntimeException(
                    "$clazz is not supported, please use one of type "
                            + SUPPORTED_COLLECTIONS.stream()
                        .map { x: Class<out MutableCollection<*>?> -> x.name }
                        .reduce("") { obj: String, str: String -> obj + str }
                )
                return CollectionContainer<Collection<Any>, Any>(collectionType = clazz)
            }
            if (MutableMap::class.java.isAssignableFrom(clazz)) {
                if (!SUPPORTED_MAPS.contains(clazz)) throw RuntimeException(
                    "$clazz is not supported, please use one of type "
                            + SUPPORTED_MAPS.stream()
                        .map { x: Class<out MutableMap<*, *>?> -> x.name }
                        .reduce("") { obj: String, str: String -> obj + str })
                return MapContainer<Map<Any, Any>, Any, Any>(mapType = clazz)
            }
            return Raw(rawType = clazz)
        }

        fun fromType(type: Type): Container<*> {
            if (type is Class<*>) {
                return fromClass(type)
            }

            if (type is GenericArrayType) {
                return fromClass(type as Class<*>)
            }

            if (type !is ParameterizedType)
                throw RuntimeException("type variable $type is not allowed in rlp decoding")
            val types = type.actualTypeArguments
            val clazz = type.rawType as Class<*>
            val container = fromClass(clazz)
            return when (container.type) {
                ContainerType.RAW -> container
                ContainerType.MAP -> {
                    val con = container.asMap()
                    con.keyType = fromType((types[0])) as Container<Any?>
                    con.valueType = fromType(types[1]) as Container<Any?>
                    con
                }
                ContainerType.COLLECTION -> {
                    val con = container.asCollection()
                    con.contentType = fromType(types[0]) as Container<Any?>?
                    con
                }
            }
        }

        private val SUPPORTED_MAPS: Set<Class<out MutableMap<*, *>?>> = HashSet(
            listOf(
                MutableMap::class.java, HashMap::class.java,
                ConcurrentMap::class.java, ConcurrentHashMap::class.java,
                TreeMap::class.java
            )
        )
        private val SUPPORTED_COLLECTIONS: Set<Class<out MutableCollection<*>?>> = HashSet(
            listOf(
                MutableCollection::class.java, MutableList::class.java, ArrayList::class.java,
                MutableSet::class.java, Queue::class.java, Deque::class.java,
                HashSet::class.java, TreeSet::class.java, LinkedList::class.java,
                ArrayDeque::class.java
            )
        )
    }
}