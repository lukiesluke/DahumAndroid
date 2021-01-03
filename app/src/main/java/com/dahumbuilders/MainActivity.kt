package com.dahumbuilders

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dahumbuilders.fragment.SummaryReportFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SummaryReportFragment())
            .commit()
        Log.d("lwg", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lwg", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lwg", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lwg", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lwg", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lwg", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lwg", "onRestart")
    }
}