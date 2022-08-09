package com.example.rlpencodesample

import org.kethereum.rlp.RLPElement
import org.kethereum.rlp.RLPList
import org.kethereum.rlp.RLPType
import org.kethereum.rlp.decodeRLP

fun <T> decode(data: ByteArray, clazz: Class<T>?): T {
    val rlpType = data.decodeRLP()
    return decode(rlpType, clazz)
}

@Suppress("UNCHECKED_CAST")
fun <T> decode(element: RLPType, clazz: Class<T>?): T {
    return when (clazz) {
        RLPType::class.java -> element as T
        RLPElement::class.java -> (element as RLPElement) as T
        RLPList::class.java -> (element as RLPList) as T

        else -> throw Exception("")
    }

}