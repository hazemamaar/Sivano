package com.android.sivano.di

import android.content.Context
import android.content.SharedPreferences
import com.android.sivano.common.uitil.C
import com.android.sivano.common.uitil.C.PREFERENCES_NAME
import com.android.sivano.common.uitil.C.token
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.data.remote.ApiService
import com.android.sivano.repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return localHttpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        complexPreferences: ComplexPreferences,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                val token = complexPreferences.getString(token, "")
                val lang="en"
                val newRequest = builder.apply {
//                    addHeader("Accept", "application/json")
                    if (token.isNotEmpty())
                    {addHeader("Authorization", "$token")
                    addHeader("lang","$lang")
                    }
                    build()
                }
                return@addInterceptor chain.proceed(newRequest.build())
            }

            .build()

    @Provides
    fun providesApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .run {
                baseUrl(C.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ) = context

    @Singleton
    @Provides
    fun provideAuthRepo(apiService: ApiService): AuthRepo = AuthRepo(apiService)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSharedPreferencesEditor(preferences: SharedPreferences): SharedPreferences.Editor =
        preferences.edit()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideComplexPreference(
        GSON: Gson,
        preferences: SharedPreferences,
        editor: SharedPreferences.Editor,
    ): ComplexPreferences =
        ComplexPreferences(GSON, preferences, editor)



}