package co.projectopal.cocktaildemo.data.repositories

import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailListResponse
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailResponse

class MockCocktailRepository : CocktailRepository {

    private var latestGetCocktailByNameResponse: GetCocktailListResponse? = null
    private var latestGetCocktailByFirstLetterResponse: GetCocktailListResponse? = null
    private var latestGetRandomCocktailResponse: GetCocktailResponse? = null

    override suspend fun getCocktailsByName(name: String) =
        latestGetCocktailByNameResponse!!

    override suspend fun getCocktailsByFirstLetter(letter: String) =
        latestGetCocktailByFirstLetterResponse!!

    override suspend fun getRandomCocktail(): GetCocktailResponse =
        latestGetRandomCocktailResponse!!

    fun whenGetCocktailsByNameResponds(response: GetCocktailListResponse) {
        latestGetCocktailByNameResponse = response
    }

    fun whenGetCocktailsByFirstLetterResponds(response: GetCocktailListResponse) {
        latestGetCocktailByFirstLetterResponse = response
    }

    fun whenGetRandomCocktailResponds(response: GetCocktailResponse) {
        latestGetRandomCocktailResponse = response
    }
}