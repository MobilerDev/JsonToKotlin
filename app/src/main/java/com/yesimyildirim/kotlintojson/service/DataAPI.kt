package com.yesimyildirim.kotlintojson.service

import com.yesimyildirim.kotlintojson.model.OrnekItem
import retrofit2.Call
import retrofit2.http.GET

interface DataAPI {

    @GET("orgs")
    fun getData():Call<List<OrnekItem>>
}