package lt.andriusdaraskevicius.sampleapireader.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.andriusdaraskevicius.sampleapireader.data.IPostsService
import lt.andriusdaraskevicius.sampleapireader.util.AppConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides()
    fun provideJsonConverter(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostsService(retrofit: Retrofit): IPostsService {
        return retrofit.create(IPostsService::class.java)
    }

}