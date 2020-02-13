package com.project.network

import com.google.gson.GsonBuilder
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient{

    private val client by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
//            addInterceptor{ chain -> createHeaderChain(chain) }
        }.build()
    }

    private val gson by lazy {
        GsonBuilder().apply {
            setLenient()
        }.create()
    }


    private var baseHeader: Map<String, String>? = null


    private fun createBaseHeader() {
        if(baseHeader == null)
            baseHeader = hashMapOf<String, String>()
                .also {}
    }


    // 헤더 셋팅시에만 사용
    private fun createHeaderChain(chain: Interceptor.Chain): Response {
        val request = chain.request()

        createBaseHeader()

        return chain.proceed(request.newBuilder()
            .headers(Headers.of(baseHeader.toString()))
            .method(request.method(), request.body())
            .build())
    }

    private fun buildClient() = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        baseUrl("https://www.metaweather.com/api/")
        client(client)
    }.build()



    fun build() = buildClient()
}
