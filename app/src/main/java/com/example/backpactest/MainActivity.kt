package com.example.backpactest

import androidx.lifecycle.ViewModelProvider
import com.example.backpactest.databinding.ActivityMainBinding
import com.example.backpactest.viewmodel.MainViewModel

class MainActivity : BaseActivityWithVM<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_main




    override fun createObserveData() {

    }
}