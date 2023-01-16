package co.projectopal.cocktaildemo.data.repositories

import co.projectopal.cocktaildemo.data.models.createTestCocktail
import co.projectopal.cocktaildemo.data.models.testCocktail
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailListResponse
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailResponse
import co.projectopal.cocktaildemo.data.source.MockCocktailApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class CocktailRepositoryImplTest {

    private val apiService = MockCocktailApiService()
    private val repo = CocktailRepositoryImpl(apiService = apiService)

    private val expectedCocktail = testCocktail
    private val expectedListOfCocktails = listOf(
        expectedCocktail,
        createTestCocktail(name = "Old Fashioned")
    )

    @Test
    fun shouldReturnListOfCocktails_whenGetCocktailsByNameIsSuccessful() = runTest {
        apiService.whenGetCocktailsByNameIsSuccessful(cocktails = expectedListOfCocktails)

        val response = repo.getCocktailsByName(name = "test") as GetCocktailListResponse.Success

        assertEquals(expectedListOfCocktails, response.cocktails)
    }

    @Test
    fun shouldReturnFailure_whenGetCocktailsByNameFails() = runTest {
        apiService.whenGetCocktailsByNameFails()

        assertTrue(repo.getCocktailsByName(name = "test") is GetCocktailListResponse.Failure)
    }

    @Test
    fun shouldReturnFailure_whenGetCocktailsByNameIsEmpty() = runTest {
        apiService.whenGetCocktailsByNameIsSuccessful(cocktails = null)

        assertTrue(repo.getCocktailsByName(name = "test") is GetCocktailListResponse.Failure)
    }

    @Test
    fun shouldReturnListOfCocktails_whenGetCocktailsByFirstLetterIsSuccessful() = runTest {
        apiService.whenGetCocktailsByFirstLetterIsSuccessful(cocktails = expectedListOfCocktails)

        val response =
            repo.getCocktailsByFirstLetter(letter = "t") as GetCocktailListResponse.Success

        assertEquals(expectedListOfCocktails, response.cocktails)
    }

    @Test
    fun shouldReturnFailure_whenGetCocktailsByFirstLetterFails() = runTest {
        apiService.whenGetCocktailsByFirstLetterFails()

        assertTrue(repo.getCocktailsByFirstLetter(letter = "t") is GetCocktailListResponse.Failure)
    }

    @Test
    fun shouldReturnFailure_whenGetCocktailsByFirstLetterIsEmpty() = runTest {
        apiService.whenGetCocktailsByFirstLetterIsSuccessful(cocktails = null)

        assertTrue(repo.getCocktailsByFirstLetter(letter = "t") is GetCocktailListResponse.Failure)
    }

    @Test
    fun shouldReturnCocktail_whenGetRandomCocktailIsSuccessful() = runTest {
        apiService.whenGetRandomCocktailIsSuccessful(cocktails = expectedListOfCocktails)

        val response = repo.getRandomCocktail() as GetCocktailResponse.Success

        assertEquals(expectedCocktail, response.cocktail)
    }

    @Test
    fun shouldReturnFailure_whenGetRandomCocktailFails() = runTest {
        apiService.whenGetRandomCocktailFails()

        assertTrue(repo.getRandomCocktail() is GetCocktailResponse.Failure)
    }
}