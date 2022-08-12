package com.example.rlp.container

import java.lang.RuntimeException

class Raw<V>(
    var rawType: Class<V>,
    override val type: ContainerType = ContainerType.RAW
) : Container<V> {

    override fun asRaw(): Class<V> {
        return rawType
    }

    override fun asCollection(): CollectionContainer<Collection<V>, V> {
        throw RuntimeException("not a collection container")
    }

    override fun asMap(): MapContainer<Map<*, V>, *, V> {
        throw RuntimeException("not a map container")
    }
}