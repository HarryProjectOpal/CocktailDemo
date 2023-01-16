package co.projectopal.cocktaildemo.data.source

import co.projectopal.cocktaildemo.data.models.Cocktail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {

    @GET("search.php")
    suspend fun getCocktailsByName(@Query("s") name: String): CocktailResponse

    @GET("search.php")
    suspend fun getCocktailsByFirstLetter(@Query("f") firstLetter: String): CocktailResponse

    @GET("random.php")
    suspend fun getRandomCocktail(): CocktailResponse

    companion object {
        fun create(retrofit: Retrofit) = retrofit.create<CocktailApiService>()
    }
}

data class DrinksResponse(val drinks: List<Cocktail>?)
typealias CocktailResponse = Response<DrinksResponse>
