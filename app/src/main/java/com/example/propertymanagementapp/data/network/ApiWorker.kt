package com.example.propertymanagementapp.data.network

import com.example.propertymanagementapp.app.Config
import com.example.propertymanagementapp.helpers.SessionManager.Companion.TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object ApiWorker{

    private const val REQUEST_TIMEOUT:Long = 60
    private var okHttpClient: OkHttpClient? = null
    val client: OkHttpClient
        get(){
            if(okHttpClient == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                var httpBuilder = OkHttpClient.Builder()
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(object: Interceptor {
                        override fun intercept(chain: Interceptor.Chain?): Response {
                            val original = chain!!.request()
                            val requestBuilder = original.newBuilder()
                                .addHeader(
                                    "Authentication",
                                    "Bearer $TOKEN"
                                )
                                .addHeader("Accept", "application/json")
                                .addHeader("Content-Type", "application/json")
                            val request = requestBuilder.build()
                            return chain.proceed(request)
                        }

                    })
                okHttpClient = httpBuilder.build()
            }
            return okHttpClient!!

        }
}