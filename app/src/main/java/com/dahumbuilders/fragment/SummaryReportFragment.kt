package com.dahumbuilders.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dahumbuilders.DahumViewModel
import com.dahumbuilders.R
import com.dahumbuilders.SummaryAdapter
import com.dahumbuilders.model.Summary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_summary_report.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SummaryReportFragment : Fragment() {
    private var adapter: SummaryAdapter? = null
    private var param1: String? = null
    private var param2: String? = null
    private var summary: List<Summary> = emptyList()

    private lateinit var dahumViewModel: DahumViewModel
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dahumViewModel = ViewModelProviders.of(this).get(DahumViewModel::class.java)
        if (savedInstanceState != null) {
            val listSummaryStr = savedInstanceState.getString("h")
            val itemType = object : TypeToken<List<Summary>>() {}.type

            summary = gson.fromJson<List<Summary>>(listSummaryStr, itemType)
            Log.d("lwg", "onCreate()frag   savedInstanceState != null")
        } else {
            Log.d("lwg", "onCreate()frag   savedInstanceState == null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_summary_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = SummaryAdapter(summary)
        recyclerview.adapter = adapter

        Log.d("lwg", "onViewCreated-frag")
        swipeRefresh?.setOnRefreshListener {
            dahumViewModel.fetchPostsFromWebService().observe(viewLifecycleOwner, Observer {
                summary = it
                adapter?.summary = summary
                adapter?.notifyDataSetChanged()

                swipeRefresh?.isRefreshing = false
            })

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SummaryReportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("h", gson.toJson(summary))
        Log.d("lwg", "onSaveInstanceState.putString: " + gson.toJson(summary))
    }

    override fun onResume() {
        super.onResume()
        if (summary.isNotEmpty()) {
            Log.d("lwg", "onResume-frag " + summary.get(0).DatePaid)
            adapter?.summary = summary
            adapter?.notifyDataSetChanged()
        } else {
            Log.d("lwg", "onResume-frag ")
        }
    }

}