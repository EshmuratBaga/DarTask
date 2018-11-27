package project.dar.dartask.di.component

import dagger.Component
import project.dar.dartask.App
import project.dar.dartask.di.module.ApplicationModule
import project.dar.dartask.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(app: MainActivity)


}