package com.dahumbuilders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dahumbuilders.model.Summary

class DahumViewModel(application: Application) : AndroidViewModel(application) {
    private var dahumRepository: DahumRepository = DahumRepository()

    fun fetchPostsFromWebService(): LiveData<List<Summary>> {
        return dahumRepository.fetchSummaryReport()
    }

}