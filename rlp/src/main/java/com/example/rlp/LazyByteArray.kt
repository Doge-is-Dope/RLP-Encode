package com.example.rlp

import java.util.*

// reduce byte array copy by lazy loading
internal class LazyByteArray {
    private var data: ByteArray
    private var offset = 0
    private var limit: Int

    constructor(data: ByteArray) {
        this.data = data
        limit = data.size
    }

    constructor(data: ByteArray, offset: Int, limit: Int) {
        this.data = data
        this.offset = offset
        this.limit = limit
    }

    fun get(): ByteArray {
        if (offset == 0 && limit == data.size) return data
        data = Arrays.copyOfRange(data, offset, limit)
        offset = 0
        limit = data.size
        return data
    }

    fun size(): Int {
        return limit - offset
    }

    companion object {
        @JvmField
        var EMPTY = LazyByteArray(ByteArray(0))
    }
}