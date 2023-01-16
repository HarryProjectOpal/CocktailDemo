package co.projectopal.cocktaildemo.data.repositories

import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailListResponse
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailResponse
import co.projectopal.cocktaildemo.data.source.CocktailApiService

class CocktailRepositoryImpl(private val apiService: CocktailApiService): CocktailRepository {

    override suspend fun getCocktailsByName(name: String): GetCocktailListResponse {
        return try {
            val cocktails = apiService.getCocktailsByName(name = name).body()?.drinks

            if(!cocktails.isNullOrEmpty()) {
                GetCocktailListResponse.Success(cocktails)
            } else {
                GetCocktailListResponse.Failure
            }

        } catch (_: Exception) {
            GetCocktailListResponse.Failure
        }
    }

    override suspend fun getCocktailsByFirstLetter(letter: String): GetCocktailListResponse {
        return try {
            val cocktails = apiService.getCocktailsByFirstLetter(firstLetter = letter).body()?.drinks

            if(!cocktails.isNullOrEmpty()) {
                GetCocktailListResponse.Success(cocktails)
            } else {
                GetCocktailListResponse.Failure
            }

        } catch (_: Exception) {
            GetCocktailListResponse.Failure
        }
    }

    override suspend fun getRandomCocktail(): GetCocktailResponse {
        return try {
            val cocktail = apiService.getRandomCocktail().body()?.drinks?.firstOrNull()

            if(cocktail != null) {
                GetCocktailResponse.Success(cocktail)
            } else {
                GetCocktailResponse.Failure
            }

        } catch (_: Exception) {
            GetCocktailResponse.Failure
        }
    }
}