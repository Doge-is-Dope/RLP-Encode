package com.example.rlpencodesample

import org.tdf.rlp.RLP

class Book(
    private val id: Int,
    private val title: String,
    private val testStringList: List<String> = listOf(),
    private val testBytes: ByteArray,
) {
    constructor() : this(
        id = 0,
        title = "",
        testStringList = listOf(),
        testBytes = byteArrayOf(),
    )
}


internal class TransactionEnvelope(
    @RLP(0) val payload: Payload = Payload(),
    @RLP(1) val payloadSignatures: List<EnvelopeSignature> = emptyList(),
    @RLP(2) val envelopeSignatures: List<EnvelopeSignature> = emptyList()
)

internal class EnvelopeSignature(
    @RLP(0) val signerIndex: Int,
    @RLP(1) val keyIndex: Int,
    @RLP(2) val signature: ByteArray
) {
    // no-arg constructor required for decoding
    constructor() : this(0, 0, byteArrayOf())
}


internal class Payload(
    @RLP(0) val script: ByteArray,
    @RLP(1) val arguments: List<ByteArray>,
    @RLP(2) val referenceBlockId: ByteArray,
    @RLP(3) val gasLimit: Long,
    @RLP(4) val proposalKeyAddress: ByteArray,
    @RLP(5) val proposalKeyIndex: Long,
    @RLP(6) val proposalKeySequenceNumber: Long,
    @RLP(7) val payer: ByteArray,
    @RLP(8) val authorizers: List<ByteArray>
) {
    // no-arg constructor required for decoding
    constructor() : this(
        byteArrayOf(),
        listOf(),
        byteArrayOf(),
        0,
        byteArrayOf(),
        0,
        0,
        byteArrayOf(),
        listOf<ByteArray>()
    )
}