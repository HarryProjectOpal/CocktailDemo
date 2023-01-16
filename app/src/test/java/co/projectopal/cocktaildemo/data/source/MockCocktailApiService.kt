package co.projectopal.cocktaildemo.data.source

import co.projectopal.cocktaildemo.data.models.Cocktail
import okhttp3.ResponseBody.Companion.toResponseBody

class MockCocktailApiService: CocktailApiService {

    private var getCocktailsByNameResponse: CocktailResponse? = null
    private var getCocktailsByFirstLetterResponse: CocktailResponse? = null
    private var getRandomCocktailResponse: CocktailResponse? = null

    override suspend fun getCocktailsByName(name: String) = getCocktailsByNameResponse!!

    override suspend fun getCocktailsByFirstLetter(firstLetter: String) = getCocktailsByFirstLetterResponse!!

    override suspend fun getRandomCocktail() = getRandomCocktailResponse!!

    fun whenGetCocktailsByNameIsSuccessful(cocktails: List<Cocktail>?) {
        getCocktailsByNameResponse = CocktailResponse.success(DrinksResponse(cocktails))
    }

    fun whenGetCocktailsByNameFails() {
        getCocktailsByNameResponse = CocktailResponse.error(400, "".toResponseBody())
    }

    fun whenGetCocktailsByFirstLetterIsSuccessful(cocktails: List<Cocktail>?) {
        getCocktailsByFirstLetterResponse = CocktailResponse.success(DrinksResponse(cocktails))
    }

    fun whenGetCocktailsByFirstLetterFails() {
        getCocktailsByFirstLetterResponse = CocktailResponse.error(400, "".toResponseBody())
    }

    fun whenGetRandomCocktailIsSuccessful(cocktails: List<Cocktail>?) {
        getRandomCocktailResponse = CocktailResponse.success(DrinksResponse(cocktails))
    }

    fun whenGetRandomCocktailFails() {
        getRandomCocktailResponse = CocktailResponse.error(400, "".toResponseBody())
    }
}