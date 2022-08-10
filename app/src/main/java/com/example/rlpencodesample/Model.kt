package com.example.rlpencodesample

class Book(
    val title: String,
    val testBytesList: List<List<Byte>>,
) {
    constructor() : this(
        title = "",
        testBytesList = listOf()
    )
}


class Cat(
    val name: String,
    val testBytesList: List<ByteArray>, // This does not work in Android
) {
    constructor() : this(
        name = "",
        testBytesList = emptyList()
    )
}