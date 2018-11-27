package project.dar.dartask.service

import io.reactivex.Observable
import io.reactivex.Single
import project.dar.dartask.model.WeatherResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather?")
    fun weather(@Query("q") city: String, @Query("appid") id: String): Observable<Response<WeatherResult>>

    companion object {
        const val KEY = "ce52acfb42ea9f7414a5831c70484e50"
    }
}