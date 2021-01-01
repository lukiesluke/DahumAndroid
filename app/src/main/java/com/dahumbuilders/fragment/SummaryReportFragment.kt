package com.dahumbuilders.fragment

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_summary_report.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SummaryReportFragment : Fragment() {
    private var summaryAdapter: SummaryAdapter? = null

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dahumViewModel: DahumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dahumViewModel = ViewModelProviders.of(this).get(DahumViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh?.setOnRefreshListener {
            dahumViewModel.fetchPostsFromWebService().observe(viewLifecycleOwner, Observer {
                summaryAdapter = SummaryAdapter(it)
                recyclerview.layoutManager = LinearLayoutManager(activity)
                recyclerview.adapter = summaryAdapter
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
}