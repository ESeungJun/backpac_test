package com.example.backpactest.view.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.backpactest.databinding.ItemLocalWeatherViewBinding
import com.example.backpactest.vo.LocalWeatherVo
import com.example.backpactest.vo.WeatherVo

class WeatherHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



class WeatherItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemLocalWeatherViewBinding

    init {
        binding = DataBindingUtil.bind(itemView)!!
    }


    fun bind(item: LocalWeatherVo) {

    }

}