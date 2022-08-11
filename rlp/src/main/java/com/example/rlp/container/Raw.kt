package com.example.rlp.container

import com.example.rlp.Container
import com.example.rlp.MapContainer
import java.lang.RuntimeException

class Raw<V>(var rawType: Class<V>) : Container<V> {
    override fun getType(): ContainerType {
        return ContainerType.RAW
    }

    override fun asRaw(): Class<V> {
        return rawType
    }

    override fun asCollection(): CollectionContainer<out MutableCollection<V>?, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<out MutableMap<*, V>?, *, V> {
        throw RuntimeException("not a map container")
    }
}