package project.dar.dartask

import android.app.Application
import android.content.Context
import project.dar.dartask.di.component.ApplicationComponent
import project.dar.dartask.di.component.DaggerApplicationComponent
import project.dar.dartask.di.module.ApplicationModule
import project.dar.dartask.di.module.CityServiceModule
import project.dar.dartask.di.module.WeatherServiceModule

class App : Application(){
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .weatherServiceModule(WeatherServiceModule(this))
                .cityServiceModule(CityServiceModule(this))
                .build()

        applicationComponent.inject(this)
    }

    companion object {

        fun component(ctx: Context?): ApplicationComponent {
            val app = ctx?.applicationContext as App
            return app.applicationComponent
        }
    }


}