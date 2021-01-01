package com.dahumbuilders.service

import com.dahumbuilders.model.Summary
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("forex/dahum.php")
    fun getSummaryReport() : Call<List<Summary>>
}