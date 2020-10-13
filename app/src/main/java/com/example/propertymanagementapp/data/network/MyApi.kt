package com.example.propertymanagementapp.data.network

import com.example.propertymanagementapp.app.Config
import com.example.propertymanagementapp.data.models.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {
//
//    @POST("auth/login")
//    fun login(@Body user: User) : Call<LoginResponse>

    @POST("auth/login")
    fun login(@Body user:User): Single<LoginResponse>
//
//    @FormUrlEncoded
//    @POST("auth/login")
//    fun login(
//        @Field("email") email:String,
//        @Field("password") password:String
//    ):Call<ResponseBody>

    @POST("property")
    fun addProperty(@Body property:MyProperty):Single<PropertyResponse>

    @GET("property")
    fun getProperty(@Body property:MyApi):Single<PropertyResponse>

    @POST("auth/register")
    fun registerLandlord(@Body landlord:Landlord) : Call<RegisterResponse>


    @POST("auth/register")
    fun registerTenant(@Body tenant:Tenant) : Call<RegisterResponse>

//    @PUT("")
//    fun updateUser(@Body user: User, @Query("id") id: Int)
//
//    @DELETE("")
//    fun deleteUser(@Path("id") id: Int)


    @Multipart
    @POST("upload/property/picture")
    fun uploadImage(
        @Part image: MultipartBody.Part,
//        @Part ("id") id:String
    ):Call<ImageResponse>


    companion object {
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(ApiWorker.client)
                .build()
                .create(MyApi::class.java)
        }
    }
}