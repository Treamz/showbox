package com.treamz.showbox.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.treamz.showbox.R
import com.treamz.showbox.common.Constants
import com.treamz.showbox.common.Constants.SIGN_IN_REQUEST
import com.treamz.showbox.common.Constants.SIGN_UP_REQUEST
import com.treamz.showbox.data.preferences.PrefRepository
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.repository.*
import com.treamz.showbox.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }


    @Provides
    @Singleton
    fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideTheMoveDbApi(httpClient: OkHttpClient): TheMovieDBApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TheMovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        theMovieDBApi: TheMovieDBApi,
        prefRepository: PrefRepository
    ): MovieRepository {
        return MovieRepositoryImpl(theMovieDBApi, prefRepository)
    }

    @Provides
    @Singleton
    fun provideShowsRepository(
        theMovieDBApi: TheMovieDBApi, prefRepository: PrefRepository
    ): TvRepository {
        return TvRepositoryImpl(theMovieDBApi, prefRepository)
    }


    @Provides
    @Singleton
    fun provideMultiSearchRepository(
        theMovieDBApi: TheMovieDBApi,
        prefRepository: PrefRepository
    ): MultiSearchRepository {
        return MultiSearchRepositoryImpl(theMovieDBApi, prefRepository)
    }


    @Provides
    @Singleton
    fun providePrefencesProvider(@ApplicationContext context: Context): PrefRepository {
        return PrefRepository(context = context)
    }

    @Provides
    fun provideFirebaseFirestore() : FirebaseFirestore = Firebase.firestore


    @Provides
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        auth: FirebaseAuth,
//        oneTapClient: SignInClient,
//        @Named(SIGN_IN_REQUEST)
//        signInRequest: BeginSignInRequest,
//        @Named(SIGN_UP_REQUEST)
//        signUpRequest: BeginSignInRequest,
//        theMovieDBApi: TheMovieDBApi,
//        db: FirebaseFirestore
//
//    ): AuthRepository = AuthRepositoryImpl(
//        auth = auth,
//        oneTapClient = oneTapClient,
//        signInRequest = signInRequest,
//        signUpRequest = signUpRequest,
//        theMovieDBApi = theMovieDBApi,
//        db = db
//
//    )


    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

//    @Provides
//    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository =
        impl
}