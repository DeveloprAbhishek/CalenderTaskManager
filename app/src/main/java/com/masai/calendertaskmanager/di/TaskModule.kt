package com.masai.calendertaskmanager.di

import android.content.Context
import androidx.room.Room
import com.masai.calendertaskmanager.Utils.Constants.Companion.BASE_URL
import com.masai.calendertaskmanager.api.local.TaskDao
import com.masai.calendertaskmanager.api.local.TaskDatabase
import com.masai.calendertaskmanager.api.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    @Singleton
    fun providesAPIService(): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext context: Context): TaskDatabase {
        val builder =Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "task_db")
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideTaskDAO(db: TaskDatabase): TaskDao {
        return db.getTaskDao()
    }
}