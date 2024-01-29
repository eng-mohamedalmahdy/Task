package com.lightfeather.task.di

import com.lightfeather.task.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}