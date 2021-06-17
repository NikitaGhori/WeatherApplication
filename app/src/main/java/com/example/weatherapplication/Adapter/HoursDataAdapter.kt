package com.example.weatherapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapplication.Model.HoursModel
import com.example.weatherapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HoursDataAdapter(val items:List<HoursModel>, val context:Context): RecyclerView.Adapter<HoursDataAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtrh: TextView = view.findViewById(R.id.textViewrh)
        var txtTemp: TextView = view.findViewById(R.id.textViewtemp)
        var txtTime: TextView = view.findViewById(R.id.textViewtime)
        var imgWeatherIcon: ImageView = view.findViewById(R.id.imgWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_hours_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtTemp.text=items[position].temp.toString()+"\u00B0"
        holder.txtrh.text=items[position].rh.toString()+"%"
        var imgURL="https://www.weatherbit.io/static/img/icons/"+ items[position].weather.icon +".png"

        Glide.with(context).load(imgURL).into(holder.imgWeatherIcon);
        var localDateTime =LocalDateTime.parse(items[position].timestamp_local)
        holder.txtTime.text=localDateTime!!.format(DateTimeFormatter.ofPattern("hh:mm a"))
/*
	2:11 PM
*/
    }

    override fun getItemCount(): Int {
        return items.size
    }

}