package project.dar.dartask.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import project.dar.dartask.di.qualifiers.ApplicationContext
import project.dar.dartask.service.CityService
import project.dar.dartask.service.WeatherService
import project.dar.dartask.ui.MainDataContract
import project.dar.dartask.ui.MainViewModelFactory
import project.dar.dartask.ui.Repository

@Module(includes = [(WeatherServiceModule::class), (CityServiceModule::class)])
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


    @Provides
    fun repository(weatherService: WeatherService, cityService: CityService, disposable: CompositeDisposable): MainDataContract.Repository{
        return Repository(weatherService, cityService, disposable)
    }

    @Provides
    fun provideMainViewModelFactory(repository: MainDataContract.Repository): MainViewModelFactory{
        return MainViewModelFactory(repository)
    }

}