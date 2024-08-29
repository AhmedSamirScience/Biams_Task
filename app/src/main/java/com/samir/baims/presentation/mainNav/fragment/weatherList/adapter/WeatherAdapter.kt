package com.samir.baims.presentation.mainNav.fragment.weatherList.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samir.baims.data.remote.dto.weather.WeatherDtoRs
import com.samir.baims.databinding.ItemWeatherBinding
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.presentation.mainNav.fragment.weatherList.clickEvent.WeatherClickListener


class WeatherAdapter (myList: List<WeatherList.WeatherSingleItem> = listOf()) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var itemList: List<WeatherList.WeatherSingleItem> = ArrayList()
    var weatherItemClickListener: WeatherClickListener?= null
    private var parent: ViewGroup? = null

    init {
        this.itemList = myList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemWeatherBinding = ItemWeatherBinding.inflate(layoutInflater, parent, false)
        this.parent = parent
        return ViewHolder(itemBinding)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myItemTX = itemList[position]
        holder.bind(holder.myItemTX!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitMyList(myList: List<WeatherList.WeatherSingleItem>, weatherClickListener: WeatherClickListener) {
        this.itemList = myList
         this.weatherItemClickListener = weatherClickListener
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var bindingItem: ItemWeatherBinding) : RecyclerView.ViewHolder(bindingItem.root)  , View.OnClickListener{
        var myItemTX: WeatherList.WeatherSingleItem? = null

        init {
            bindingItem.cvContainer.setOnClickListener(this)
        }

        fun bind(myItem: WeatherList.WeatherSingleItem?) {
         //  bindingItem.myItem = myItem

           bindingItem.tvCloudValue.text = myItem?.clouds
           bindingItem.tvHumadityValue.text = myItem?.humidity
           bindingItem.tvTempratureValue.text = myItem?.temp
           bindingItem.tvWeatherMainValue.text = myItem?.tempText



        }

        override fun onClick(v: View?) {
            when(v) {
                bindingItem.cvContainer -> {
                    /**
                     * Handle the click event of the item to be animated where the color will be changed
                     */
                    notifyItemChanged(adapterPosition)

                    myItemTX?.let { weatherItemClickListener?.onItemClicked(it) }
                }
            }
        }
    }

}