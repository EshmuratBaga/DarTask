package project.dar.dartask.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import project.dar.dartask.extensions.toLiveData
import project.dar.dartask.model.City
import project.dar.dartask.model.CityRequest
import project.dar.dartask.model.Outcome
import project.dar.dartask.model.WeatherResult

class MainViewModel(val repository: MainDataContract.Repository) : ViewModel(){
    val weatherEvent: LiveData<Outcome<List<WeatherResult>>> by lazy { repository.weatherEvent.toLiveData() }

    fun search(s: String?) {
        repository.search(s)
    }

    override fun onCleared() {
        repository.clear()
        super.onCleared()
    }

}