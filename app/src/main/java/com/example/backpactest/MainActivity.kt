package com.example.backpactest

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.backpactest.databinding.ActivityMainBinding
import com.example.backpactest.view.adpter.WeatherRecyclerAdapter
import com.example.backpactest.viewmodel.MainViewModel

class MainActivity : BaseActivityWithVM<ActivityMainBinding, MainViewModel>() {

    private lateinit var mainAdapter: WeatherRecyclerAdapter


    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.lifecycleOwner = this

        mainAdapter = WeatherRecyclerAdapter()

        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        dataBinding.swipeLayout.setOnRefreshListener {
            dataBinding.swipeLayout.isRefreshing = false
            viewModel.getLocationWeatherInfo()
        }

        dataBinding.recyclerView.visibility = View.GONE
        dataBinding.loadingProgressBar.visibility = View.VISIBLE

        viewModel.getLocationWeatherInfo()
    }

    override fun createObserveData() {
        viewModel.locationWeatherLiveData.observe(this, Observer{

            dataBinding.recyclerView.visibility = View.VISIBLE
            dataBinding.loadingProgressBar.visibility = View.GONE

            mainAdapter.submitList(it)
            mainAdapter.notifyDataSetChanged()
        })
    }
}