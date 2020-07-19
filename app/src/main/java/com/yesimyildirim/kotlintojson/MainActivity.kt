package com.yesimyildirim.kotlintojson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yesimyildirim.kotlintojson.model.OrnekItem
import com.yesimyildirim.kotlintojson.service.DataAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BaseUrl="https://api.github.com/users/hadley/"
    private var ornekModels:ArrayList<OrnekItem>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }
    private fun loadData(){
        val retrofit= Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service=retrofit.create(DataAPI::class.java)
        val call =service.getData()

        call.enqueue(object : Callback<List<OrnekItem>> {
            override fun onFailure(call: Call<List<OrnekItem>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<OrnekItem>>, response: Response<List<OrnekItem>>) {
               if (response.isSuccessful){
                   response.body()?.let {
                       ornekModels= ArrayList(it)
                       for (ornekModel:OrnekItem in ornekModels!!){
                            println(ornekModel.avatarUrl)
                       }
                   }
               }
            }

        })

    }
}