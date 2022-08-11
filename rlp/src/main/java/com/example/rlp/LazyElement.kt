package com.example.rlp

import java.math.BigInteger

class LazyElement internal constructor(private val parser: RLPParser) : RLPElement {
    private var delegate: RLPElement? = null
    private val encoded: LazyByteArray = parser.lazyByteArray

    private fun parse() {
        if (delegate != null) return
        delegate = parser.readLazy()
        // release gc
    }

    override fun isRLPList(): Boolean {
        return if (delegate != null) delegate!!.isRLPList else parser.peekIsList()
    }

    override fun isRLPItem(): Boolean {
        return !isRLPList
    }

    override fun asRLPList(): RLPList {
        parse()
        return delegate!!.asRLPList()
    }

    override fun asRLPItem(): RLPItem {
        parse()
        return delegate!!.asRLPItem()
    }

    override fun isNull(): Boolean {
        parse()
        return delegate!!.isNull
    }

    override fun getEncoded(): ByteArray {
        if (delegate == null || isRLPItem) return encoded.get()
        parse()
        return delegate!!.encoded
    }

    override fun asBytes(): ByteArray {
        parse()
        return delegate!!.asBytes()
    }

    override fun asByte(): Byte {
        parse()
        return delegate!!.asByte()
    }

    override fun asShort(): Short {
        parse()
        return delegate!!.asShort()
    }

    override fun asInt(): Int {
        parse()
        return delegate!!.asInt()
    }

    override fun asLong(): Long {
        parse()
        return delegate!!.asLong()
    }

    override fun asBigInteger(): BigInteger {
        parse()
        return delegate!!.asBigInteger()
    }

    override fun asString(): String {
        parse()
        return delegate!!.asString()
    }

    override fun asBoolean(): Boolean {
        parse()
        return delegate!!.asBoolean()
    }

    override fun <T> `as`(clazz: Class<T>): T {
        parse()
        return delegate!!.`as`(clazz)
    }

    override fun get(index: Int): RLPElement {
        parse()
        return delegate!![index]
    }

    override fun add(element: RLPElement): Boolean {
        parse()
        return delegate!!.add(element)
    }

    override fun set(index: Int, element: RLPElement): RLPElement {
        parse()
        return delegate!!.set(index, element)
    }

    override fun size(): Int {
        parse()
        return delegate!!.size()
    }
}