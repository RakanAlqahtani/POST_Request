package com.example.postrequest.api

import com.example.postrequest.Model
import com.example.postrequest.ModelItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("test/")
    fun getItems(): Call<Model>

    @POST("test/")
    fun addItem(@Body item : ModelItem) : Call<ModelItem>
}