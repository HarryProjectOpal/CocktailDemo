package co.projectopal.cocktaildemo.data.repositories

import co.projectopal.cocktaildemo.data.models.Cocktail

interface CocktailRepository {

    suspend fun getCocktailsByName(name: String): GetCocktailListResponse

    suspend fun getCocktailsByFirstLetter(letter: String): GetCocktailListResponse

    suspend fun getRandomCocktail(): GetCocktailResponse

    sealed class GetCocktailListResponse {
        class Success(val cocktails: List<Cocktail>): GetCocktailListResponse()
        object Failure : GetCocktailListResponse()
    }

    sealed class GetCocktailResponse {
        class Success(val cocktail: Cocktail): GetCocktailResponse()
        object Failure : GetCocktailResponse()
    }

}