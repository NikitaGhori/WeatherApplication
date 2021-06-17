package com.example.weatherapplication.Adapter

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapplication.Model.DaysModel
import com.example.weatherapplication.R
import java.text.SimpleDateFormat
import java.util.*

class DaysDataAdapter(val items: List<DaysModel>, val context: Context): RecyclerView.Adapter<DaysDataAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtDate: TextView = view.findViewById(R.id.textViewDate)
        var txtDes: TextView = view.findViewById(R.id.textViewDesc)
        var txtMaxTemp: TextView = view.findViewById(R.id.textViewMaxtemp)
        var txtMinTemp: TextView = view.findViewById(R.id.textViewMintemp)
        var imgWeatherIcon: ImageView = view.findViewById(R.id.imgWeatherIcon)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_days_list, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtDes.text=items[position].weather.description
        holder.txtMaxTemp.text=items[position].max_temp.toString()+"\u00B0"
        holder.txtMinTemp.text=items[position].low_temp.toString()+"\u00B0"
        var imgURL="https://www.weatherbit.io/static/img/icons/"+ items[position].weather.icon +".png"

        Glide.with(context).load(imgURL).into(holder.imgWeatherIcon);



        val dateformat = SimpleDateFormat("yyyy-MM-dd")
          val date:Date  = dateformat.parse(items[position].datetime);
        val month = DateFormat.format("MMM", date) as String //06

        val day = DateFormat.format("EEE", date) as String //2013

        val date1 = DateFormat.format("dd", date) as String //20


        holder.txtDate.text=day+", "+month+" "+date1.toString()

/*
	2:11 PM
*/
    }

    override fun getItemCount(): Int {
        return items.size
    }



}