package com.kkh.single.module.template.data.di

import com.kkh.single.module.template.data.api.MemberApi
import com.kkh.single.module.template.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideTestApi(retrofit: Retrofit): MemberApi {
        return retrofit.create(MemberApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(memberApi: MemberApi): RemoteDataSource {
        return RemoteDataSource(memberApi)
    }
}