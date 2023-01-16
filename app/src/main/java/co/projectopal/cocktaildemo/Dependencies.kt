package co.projectopal.cocktaildemo

import co.projectopal.cocktaildemo.data.repositories.CocktailRepository
import co.projectopal.cocktaildemo.data.repositories.CocktailRepositoryImpl
import co.projectopal.cocktaildemo.data.source.CocktailApiService
import co.projectopal.cocktaildemo.ui.view.CocktailsViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Suppress("USELESS_CAST")
object Dependencies {

    private val networkModule = module {
        factory {
            HttpLoggingInterceptor()
                .setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
        }

        single {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        single<Gson> {
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }

        single { CocktailApiService.create(retrofit = get()) }
    }

    private val viewModelModule = module {
        single { Dispatchers.IO as CoroutineDispatcher }

        viewModel { CocktailsViewModel(repository = get(), ioDispatcher = get()) }
    }


    private val repositoryModule = module {
        single { CocktailRepositoryImpl(apiService = get()) as CocktailRepository }
    }

    internal val modules = listOf(networkModule, viewModelModule, repositoryModule)
}