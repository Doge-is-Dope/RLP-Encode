package com.example.rlp.container

import com.example.rlp.Container
import java.lang.RuntimeException

class MapContainer<M : Map<K, V>?, K, V> internal constructor(var mapType: Class<out M>) :
    Container<V> {
    var keyType: Container<out K>? = null
    var valueType: Container<out V>? = null

    override fun getType(): ContainerType = ContainerType.MAP

    override fun asRaw(): Class<V> {
        throw RuntimeException("not a raw type")
    }

    override fun asCollection(): CollectionContainer<out Collection<V>?, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<out M, out K, V> {
        return this
    }
}