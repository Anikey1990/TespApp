package ru.anikey.core_network_impl.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.anikey.core_network_api.data.interfaces.TerminalsApiClient
import ru.anikey.core_network_impl.data.TerminalsApiClientImpl

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.dellin.ru/"
    }

    @Provides
    @NetworkScope
    fun provideTerminalsApiClient(retrofit: Retrofit): TerminalsApiClient =
        TerminalsApiClientImpl(retrofit = retrofit)

    @Provides
    @NetworkScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @NetworkScope
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
        .build()

}
