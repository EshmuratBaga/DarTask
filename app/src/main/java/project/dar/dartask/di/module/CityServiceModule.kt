package project.dar.dartask.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import project.dar.dartask.service.CityService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CityServiceModule(val app: Application) {

    @Provides
    @Singleton
    fun provideService(): CityService {
        val builder = Retrofit.Builder()
                .baseUrl(BASIC)
                .client(OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        return builder.build().create(CityService::class.java)
    }

    companion object {
        const val BASIC = "https://maps.googleapis.com/maps/api/place/autocomplete/"
    }
}