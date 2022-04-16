package com.muzafferus.countrydetail.di

import android.content.Context
import androidx.room.Room
import com.muzafferus.countrydetail.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "country_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCountryDao(db: AppDatabase) = db.countryDao()
}