package com.zzz.dishesapp

import android.app.Application
import com.zzz.dishesapp.feature_recipes.di.dishModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DishApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DishApp)
            //add modules
            modules(dishModule)
        }
    }
}