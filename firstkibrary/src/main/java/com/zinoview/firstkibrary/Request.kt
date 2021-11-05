package com.zinoview.firstkibrary

import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun Any?.log(text: String) {
    Log.d("kzinoview",text)
}

interface Request<T> {

    suspend fun make(url: String) : T

    class StringRequest : Request<kotlin.String> {

        private data class Post(
            @SerializedName("userId")
            private val userId: String,
            @SerializedName("title")
            private val title: String
        )  {
            fun giveTitle() = title
        }

        private interface Service {

            @GET("/posts")
            fun data() : Call<List<Post>>
        }


        override suspend fun make(url: String): String {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(Service::class.java)
            log("request in my library")
            val result = callWrapper(service.data())
            return result[0].giveTitle()
        }

        private suspend fun <T> callWrapper(call: Call<T>) : T {
            return suspendCoroutine<T> {  continuation ->
                call.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        continuation.resume(response.body()!!)
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        log("onFailure ${t.message}")
                    }

                })
            }
        }

    }
}