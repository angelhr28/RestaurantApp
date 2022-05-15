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

    private const val RESTAURANT_DATABASE_NAME = "RESTAURANT.db"

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, RESTAURANT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providerBoardDao(db: AppDatabase) = db.boardDao()

    @Singleton
    @Provides
    fun providerCategoryDao(db: AppDatabase) = db.categoryDao()

    @Singleton
    @Provides
    fun providerProductDao(db: AppDatabase) = db.productDao()

    @Singleton
    @Provides
    fun providerRequestDao(db: AppDatabase) = db.requestDao()

    @Singleton
    @Provides
    fun providerRequestDetailDao(db: AppDatabase) = db.requestDetailDao()

}