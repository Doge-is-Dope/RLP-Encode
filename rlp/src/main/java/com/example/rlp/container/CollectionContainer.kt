package com.example.rlp.container

import com.example.rlp.Container

class CollectionContainer<C : Collection<V>?, V> internal constructor(var collectionType: Class<out C>) :
    Container<V> {
    var contentType: Container<out V>? = null

    override fun getType(): ContainerType = ContainerType.COLLECTION

    override fun asRaw(): Class<V> {
        throw Exception("not a raw type")
    }

    override fun asCollection(): CollectionContainer<out C, V> {
        return this
    }

    override fun asMap(): MapContainer<out Map<*, V>?, *, V> {
        throw Exception("not a map container")
    }
}