package com.muzafferus.countrydetail.di

import com.muzafferus.countrydetail.repository.CountryRepository
import com.muzafferus.countrydetail.repository.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: CountryRepositoryImpl): CountryRepository

}