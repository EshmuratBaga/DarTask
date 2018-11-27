package project.dar.dartask.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import project.dar.dartask.extensions.failed
import project.dar.dartask.extensions.loading
import project.dar.dartask.extensions.success
import project.dar.dartask.model.City
import project.dar.dartask.model.Outcome
import project.dar.dartask.model.WeatherResult
import project.dar.dartask.service.CityService
import project.dar.dartask.service.WeatherService
import java.util.concurrent.TimeUnit

class Repository(val weatherService: WeatherService, val cityService: CityService, val disposable: CompositeDisposable) : MainDataContract.Repository{
    override val weatherEvent: PublishSubject<Outcome<List<WeatherResult>>> = PublishSubject.create()

    override fun search(s: String?) {
        weatherEvent.loading(true)
        disposable.add(
                cityService.city(s, CityService.KEY2)
                        .subscribeOn(Schedulers.io())
                        .delay(1, TimeUnit.SECONDS)
                        .map { it.body() }
                        .subscribe {mapper(it!!.city)}
        )
    }

    private fun mapper(city: List<City>?){
        val weatherList: MutableList<WeatherResult> = arrayListOf()
        city?.forEachIndexed { index, c ->
            weatherService.weather(c.terms!![0].value!!, WeatherService.KEY)
                    .subscribeOn(Schedulers.io())
                    .map {
                        if (it.isSuccessful) {
                            weatherList.add(it.body()!!)
                        }
                    }.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (index == city.size - 1) weatherEvent.success(weatherList)
                    }, {weatherEvent.failed(it)})
        }
    }

    override fun clear() {
        disposable.clear()
    }

}