package com.example.backpactest.view.viewholder

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.backpactest.R
import com.example.backpactest.databinding.ItemLocalWeatherViewBinding
import com.example.backpactest.model.vo.LocalWeatherVo
import com.project.utils.GlideHelper

class WeatherHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



class WeatherItemViewHolder(
    itemView: View,
    private val context: Context
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemLocalWeatherViewBinding

    init {
        binding = DataBindingUtil.bind(itemView)!!
    }


    fun bind(item: LocalWeatherVo) {

        item.local?.let {
            binding.localTitleTextView.text = it.localName
        }


        item.todayWeather?.let {

            binding.todayTemper.text = "${it.temper}\u2103"
            binding.todayHumidity.text = "${it.humidity}%"

            binding.todayWeatherName.text = it.weatherStatus.fullName

            GlideHelper.load(binding.todayWeatherIcon, context.getString(R.string.icon_url, it.weatherStatus.status))
        }

        item.tomorrowWeather?.let {

            binding.tomorrowTemper.text = "${it.temper}\u2103"
            binding.tomorrowHumidity.text = "${it.humidity}%"

            binding.tomorrowWeatherName.text = it.weatherStatus.fullName

            GlideHelper.load(binding.tomorrowWeatherIcon, context.getString(R.string.icon_url, it.weatherStatus.status))
        }
    }

}