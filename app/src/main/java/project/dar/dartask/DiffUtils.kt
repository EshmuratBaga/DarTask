package project.dar.dartask

import android.os.Bundle
import android.support.v7.util.DiffUtil
import project.dar.dartask.model.WeatherResult

class DiffUtils(private val weatherOld: List<WeatherResult>, private val weatherNew: List<WeatherResult>) : DiffUtil.Callback(){

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun getOldListSize(): Int {
        return weatherOld.size
    }

    override fun getNewListSize(): Int {
        return weatherNew.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return weatherOld[p0] == weatherNew[p1]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val weatherNew = weatherNew[newItemPosition]
        val weatherOld = weatherOld[oldItemPosition]
        val diff = Bundle()
        if (weatherNew.name != weatherOld.name){
            diff.putString("name", weatherNew.name)
        }

        if (diff.size() == 0) return null
        return diff
    }
}