package com.example.rlp.container

import java.lang.RuntimeException

class MapContainer<out M : Map<K, V>, K, V> internal constructor(
    var mapType: Class<*>,
    override val type: ContainerType = ContainerType.MAP
) : Container<V> {

    var keyType: Container<in K>? = null

    var valueType: Container<in V>? = null

    override fun asRaw(): Class<V> {
        throw RuntimeException("not a raw type")
    }

    override fun asCollection(): CollectionContainer<Collection<V>, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<M, K, V> {
        return this
    }
}