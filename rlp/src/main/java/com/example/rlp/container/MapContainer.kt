package com.example.rlp.container

import com.example.rlp.Container
import java.lang.RuntimeException

class MapContainer<M : Map<K, V>?, K, V> internal constructor(var mapType: Class<*>? = null) :
    Container<V> {
    var keyType: Container<*>? = null
    var valueType: Container<*>? = null

    override fun getType(): ContainerType {
        return ContainerType.MAP
    }

    override fun asRaw(): Class<V> {
        throw RuntimeException("not a raw type")
    }

    override fun asCollection(): CollectionContainer<out MutableCollection<V>?, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<M, K, V> {
        return this
    }
}