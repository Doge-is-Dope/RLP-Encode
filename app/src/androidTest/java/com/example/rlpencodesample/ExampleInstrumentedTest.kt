package com.example.rlpencodesample

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nftco.flow.sdk.FlowTransaction
import com.nftco.flow.sdk.hexToBytes

import org.junit.Test
import org.junit.runner.RunWith
import org.tdf.rlp.RLPCodec
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun rlp_decode_tx() {
        val hex =
            "f9014df90148b8e3696d706f72742056616c7565446170702066726f6d203078356138313433646138303538373430630a2020202020202020202020200a2020202020202020202020207472616e73616374696f6e2876616c75653a2055466978363429207b0a202020202020202020202020202020207072657061726528617574686f72697a65723a20417574684163636f756e7429207b0a20202020202020202020202020202020202020202020202056616c7565446170702e73657456616c75652876616c7565290a202020202020202020202020202020207d0a2020202020202020202020207de09f7b2276616c7565223a22313233222c2274797065223a22554669783634227da00fa7a0eca73d842b6f18a929f5b7d9fb533069781d065087d0f26d1f9a7dcaa68201f48828de65b3b5e83245800888f086a545ce3c552dc98828de65b3b5e83245c0c0"
        val bytes = hex.hexToBytes()
        println("bytes: ${bytes.contentToString()}")
        val tx = FlowTransaction.of(bytes)
        println("tx: $tx")
    }

    @Test
    fun hex_to_bytes() {
        val book = Book(
            id = 1,
            title = "abc",
            testStringList = listOf(),
            testBytes = byteArrayOf(-61, -128, -128, -128),
            testIntsList = listOf(intArrayOf(111, 222, 333)),
//            testBytesList = listOf()
        )

        val encoded = RLPCodec.encode(book)
        println("encoded: ${Arrays.toString(encoded)}")
        val decoded = RLPCodec.decode(encoded, Book::class.java)
        println("decoded: $decoded")
    }
}