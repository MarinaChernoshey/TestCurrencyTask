package com.example.testtask

import android.app.Application
import com.facebook.stetho.Stetho

class TestTaskApplication : Application() {
    lateinit var dependencyFactory: DependencyFactory
        private set

    override fun onCreate() {
        super.onCreate()
        dependencyFactory = DependencyFactory()
        Stetho.initializeWithDefaults(this);
    }
}