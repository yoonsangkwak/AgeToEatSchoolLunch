package site.yoonsang.agetoeatschoollunch.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import site.yoonsang.agetoeatschoollunch.database.AllergyDatabase
import site.yoonsang.agetoeatschoollunch.network.NeisApi
import site.yoonsang.agetoeatschoollunch.util.Constants
import site.yoonsang.agetoeatschoollunch.util.SessionManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAllergyDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AllergyDatabase::class.java,
        Constants.ALLERGY_TABLE
    ).build()

    @Singleton
    @Provides
    fun provideAllergyDao(db: AllergyDatabase) =
        db.allergyDao()

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constants.SCHOOL_NAME, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) =
        SessionManager(preferences)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NeisApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNeisApi(retrofit: Retrofit): NeisApi =
        retrofit.create(NeisApi::class.java)
}