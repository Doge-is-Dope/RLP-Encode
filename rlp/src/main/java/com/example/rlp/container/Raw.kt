package com.example.rlp.container

import com.example.rlp.Container
import java.lang.RuntimeException

class Raw<V>(var rawType: Class<V>) : Container<V> {

    override fun getType(): ContainerType = ContainerType.RAW

    override fun asRaw(): Class<V> {
        return rawType
    }

    override fun asCollection(): CollectionContainer<out Collection<V>?, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<out Map<*, V>?, *, V> {
        throw RuntimeException("not a map container")
    }
}