package project.dar.dartask.service

import io.reactivex.Observable
import io.reactivex.Single
import project.dar.dartask.model.CityRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {

    @GET("json?sessiontoken=1098765432&components=country:kz&types=(cities)")
    fun city(@Query("input") s: String?, @Query("key") key: String): Observable<Response<CityRequest>>

    companion object {
        const val KEY = "AIzaSyCbCwWJ9Apa8OM-mddlIjuu5Q-0w-yoSJ4"
        const val KEY2 = "AIzaSyBcnpbwtXARyJs1jV9zvntj_TNDojQva5c"
    }
}