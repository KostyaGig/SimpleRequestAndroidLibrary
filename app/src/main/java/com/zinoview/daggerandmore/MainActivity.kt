package com.zinoview.daggerandmore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zinoview.firstkibrary.Request
import com.zinoview.firstkibrary.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Any?.log(text: String) {
    Log.d("kzinoview",text)
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringRequest = Request.StringRequest()
        CoroutineScope(Dispatchers.Main).launch {
            log(stringRequest.make(BASE_URL))
        }
    }

    private companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}