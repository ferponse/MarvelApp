package com.example.marvelapp.di

import com.marvel.data.di.MarvelDataModule
import dagger.Module

@Module(includes = [
    MarvelDataModule::class
])
interface DataModules