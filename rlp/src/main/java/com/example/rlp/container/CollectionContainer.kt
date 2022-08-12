package com.example.rlp.container

class CollectionContainer<out C : Collection<V>, V> internal constructor(
    var collectionType: Class<*>,
    override val type: ContainerType = ContainerType.COLLECTION
) : Container<V> {

    var contentType: Container<in V>? = null

    override fun asRaw(): Class<V> {
        throw Exception("not a raw type")
    }

    override fun asCollection(): CollectionContainer<C, V> {
        return this
    }

    override fun asMap(): MapContainer<Map<*, V>, *, V> {
        throw Exception("not a map container")
    }
}