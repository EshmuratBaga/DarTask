package project.dar.dartask.ui

import io.reactivex.subjects.PublishSubject
import project.dar.dartask.model.City
import project.dar.dartask.model.CityRequest
import project.dar.dartask.model.Outcome
import project.dar.dartask.model.WeatherResult

interface MainDataContract {

    interface Repository{
        val weatherEvent: PublishSubject<Outcome<List<WeatherResult>>>

        fun clear()
        fun search(s: String?)

    }
}
