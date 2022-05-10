package com.example.restaurantapp.module

import android.content.Context
import androidx.room.Room
import com.example.restaurantapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DEVELOPER_DATABASE_NAME = "developer.db"

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DEVELOPER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providerDeveloperDao(db: AppDatabase) = db.developerDao()
}