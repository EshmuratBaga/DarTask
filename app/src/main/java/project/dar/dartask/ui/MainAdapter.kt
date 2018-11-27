package project.dar.dartask.ui

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.city_item.view.*
import project.dar.dartask.DiffUtils
import project.dar.dartask.R
import project.dar.dartask.model.City
import project.dar.dartask.model.WeatherResult

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>(){
    var weatherResult: List<WeatherResult> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.city_item, p0, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherResult.size
    }

    override fun onBindViewHolder(holder: MainHolder, pos: Int) {
        val w = weatherResult[pos]
        holder.cityName.text = w.name
        holder.temp.text = w.data?.temp.toString()
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            val b = payloads[0] as Bundle
            if (b.containsKey("name"))
                holder.cityName.text = weatherResult[position].name
        }
    }

    fun update(data: List<WeatherResult>) {
        val diff = DiffUtils(weatherResult, data)
        val productDifUtil = DiffUtil.calculateDiff(diff)
//        (weatherResult as MutableList).clear()
//        (weatherResult as MutableList).addAll(data)
        weatherResult = data
        productDifUtil.dispatchUpdatesTo(this)
//        notifyDataSetChanged()
    }

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cityName = v.cityName
        val temp = v.temp
    }
}