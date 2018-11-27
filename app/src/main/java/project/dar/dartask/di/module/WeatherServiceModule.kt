package project.dar.dartask.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import project.dar.dartask.service.WeatherService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class WeatherServiceModule(val app: Application) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl(BASIC)
                .client(OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    companion object {
        const val BASIC = "https://api.openweathermap.org/data/2.5/"
    }
}