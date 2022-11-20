package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com"
class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_users.layoutManager = linearLayoutManager
        getJsonData();
    }

    private fun getJsonData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<JsonDataItem?> {
            override fun onResponse(call: Call<JsonDataItem?>, response: Response<JsonDataItem?>) {
                val responseBody = listOf(response.body()!!)
                print("hi world")

                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()

                recyclerview_users.adapter = myAdapter





            }

            override fun onFailure(call: Call<JsonDataItem?>, t: Throwable) {
            Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }
}