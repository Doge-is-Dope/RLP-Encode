package com.example.rlp.container

import com.example.rlp.Container
import com.example.rlp.MapContainer

class CollectionContainer<C : Collection<T>?, T> internal constructor(var collectionType: Class<*>) :
    Container<T> {
    var contentType: Container<*>? = null

    override fun getType(): ContainerType {
        return ContainerType.COLLECTION
    }

    override fun asRaw(): Class<T> {
        throw Exception("not a raw type")
    }

    override fun asCollection(): CollectionContainer<C, T> {
        return this
    }

    override fun asMap(): MapContainer<out MutableMap<*, T>?, *, T> {
        throw Exception("not a map container")
    }
}