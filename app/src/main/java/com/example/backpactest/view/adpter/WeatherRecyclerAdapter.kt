package com.example.backpactest.view.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.backpactest.databinding.ItemHeaderViewBinding
import com.example.backpactest.databinding.ItemLocalWeatherViewBinding
import com.example.backpactest.view.viewholder.WeatherHeaderViewHolder
import com.example.backpactest.view.viewholder.WeatherItemViewHolder
import com.example.backpactest.vo.LocalWeatherVo
import com.example.backpactest.vo.WeatherStatus
import com.example.backpactest.vo.WeatherViewType

class WeatherRecyclerAdapter : ListAdapter<LocalWeatherVo, RecyclerView.ViewHolder>(WeatherDiffUtil()) {


    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)
        if (item.localName.isEmpty() && item.todayWeather == null && item.tomorrowWeather == null)
            return WeatherViewType.HEADER.value

        return WeatherViewType.ITEM.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {

            WeatherViewType.HEADER.value -> {
                val binding = ItemLocalWeatherViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WeatherItemViewHolder(binding.root)
            }

            else -> {
                val binding = ItemHeaderViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WeatherHeaderViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is WeatherItemViewHolder)
           holder.bind(getItem(position))
        else
           return
    }


    class WeatherDiffUtil : DiffUtil.ItemCallback<LocalWeatherVo>() {
        override fun areItemsTheSame(oldItem: LocalWeatherVo, newItem: LocalWeatherVo) =
            oldItem.localName == newItem.localName

        override fun areContentsTheSame(oldItem: LocalWeatherVo, newItem: LocalWeatherVo) =
            oldItem.todayWeather?.weatherStatus ?: WeatherStatus.NONE == newItem.todayWeather?.weatherStatus ?: WeatherStatus.NONE
                    && oldItem.tomorrowWeather?.weatherStatus ?: WeatherStatus.NONE == newItem.tomorrowWeather?.weatherStatus ?: WeatherStatus.NONE
    }

}