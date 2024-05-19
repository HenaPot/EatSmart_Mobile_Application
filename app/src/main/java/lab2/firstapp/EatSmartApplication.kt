package lab2.firstapp

import android.app.Application
import lab2.firstapp.model.AppContainer
import lab2.firstapp.model.AppDataContainer

class EatSmartApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}