package com.example.rlpencodesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.tdf.rlp.RLPCodec
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val book = Book(
            id = 1,
            title = "abc",
            testStringList = listOf(),
            testBytes = byteArrayOf(-61, -128, -128, -128),
        )
        val encoded = RLPCodec.encode(book)
        Log.d("Test", "Encoded: ${Arrays.toString(encoded)}")
//
        val decoded = RLPCodec.decode(encoded, Book::class.java)
        Log.d("Test", "Decoded: $decoded")

//        val encodedTx = encode(Payload())
////
//        val decodedTx = RLPCodec.decode(encoded, Payload::class.java)
//        Log.d("Test", "Decoded tx: $decoded")
    }
}

