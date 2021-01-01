package com.dahumbuilders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dahumbuilders.model.Summary
import com.dahumbuilders.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DahumRepository {

    fun fetchSummaryReport(): LiveData<List<Summary>> {
        val data = MutableLiveData<List<Summary>>()

        val call: Call<List<Summary>> = ApiClient.getClient.getSummaryReport()
        call.enqueue(object : Callback<List<Summary>> {
            override fun onResponse(call: Call<List<Summary>>, response: Response<List<Summary>>) {
                val map = hashMapOf<String, Summary>()
                if (response.isSuccessful) {
                    data.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<List<Summary>>, t: Throwable) {
                Log.d("lwg", "response " + t.message )
            }
        })

        return data
    }
}