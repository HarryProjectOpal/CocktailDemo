package co.projectopal.cocktaildemo.ui.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.projectopal.cocktaildemo.data.models.createTestCocktail
import co.projectopal.cocktaildemo.data.models.testCocktail
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailListResponse
import co.projectopal.cocktaildemo.data.repositories.MockCocktailRepository
import co.projectopal.cocktaildemo.ui.view.CocktailsViewModel
import co.projectopal.cocktaildemo.ui.view.CocktailsViewModel.Display
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CocktailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repo = MockCocktailRepository()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: CocktailsViewModel
    private var latestDisplay: Display? = null

    private val expectedCocktails = listOf(testCocktail, createTestCocktail(name = "Old Fashioned"))

    @Test
    fun shouldEmmitInitialDisplay() {
        givenAViewModel()
        thenInitialDisplayIsEmitted()
    }

    @Test
    fun shouldEmmitCorrectErrorMessage_whenSearchIsPerformedWithLessThanOneCharacter() {
        givenAViewModel()
        whenSearchViewModel("")
        thenEmptySearchTermErrorIsEmitted()
    }

    @Test
    fun shouldEmmitCorrectErrorMessage_whenSearchByNameFails() = runTest(dispatcher) {
        givenAViewModel()
        repo.whenGetCocktailsByNameResponds(GetCocktailListResponse.Failure)
        whenSearchViewModel("Mojito")
        advanceUntilIdle()
        thenFailedSearchErrorIsEmitted()
    }

    @Test
    fun shouldEmmitCocktails_whenSearchByNameIsSuccessful() = runTest(dispatcher) {
        givenAViewModel()
        repo.whenGetCocktailsByNameResponds(GetCocktailListResponse.Success(expectedCocktails))
        whenSearchViewModel("Mojito")
        advanceUntilIdle()
        thenCocktailsAreEmitted()
    }

    @Test
    fun shouldEmmitCorrectErrorMessage_whenSearchByFirstLetterFails() = runTest(dispatcher) {
        givenAViewModel()
        repo.whenGetCocktailsByFirstLetterResponds(GetCocktailListResponse.Failure)
        whenSearchViewModel("M")
        advanceUntilIdle()
        thenFailedSearchErrorIsEmitted()
    }

    @Test
    fun shouldEmmitCocktails_whenSearchByFirstLetterIsSuccessful() = runTest(dispatcher) {
        givenAViewModel()
        repo.whenGetCocktailsByFirstLetterResponds(GetCocktailListResponse.Success(expectedCocktails))
        whenSearchViewModel("A")
        advanceUntilIdle()
        thenCocktailsAreEmitted()
    }

    private fun givenAViewModel() {
        viewModel = CocktailsViewModel(repository = repo, ioDispatcher = dispatcher)
        viewModel.display.observeForever {
            latestDisplay = it
        }
    }

    private fun whenSearchViewModel(term: String) {
        viewModel.search(term)
    }

    private fun thenInitialDisplayIsEmitted() {
        assertFalse(latestDisplay!!.loading)
        assertNull(latestDisplay!!.errorMessage)
        assertNull(latestDisplay!!.cocktails)
    }

    private fun thenEmptySearchTermErrorIsEmitted() {
        assertFalse(latestDisplay!!.loading)
        assertEquals("Please enter a search term!", latestDisplay!!.errorMessage)
        assertNull(latestDisplay!!.cocktails)
    }

    private fun thenFailedSearchErrorIsEmitted() {
        assertFalse(latestDisplay!!.loading)
        assertEquals("Something went wrong!", latestDisplay!!.errorMessage)
        assertNull(latestDisplay!!.cocktails)
    }

    private fun thenCocktailsAreEmitted() {
        assertFalse(latestDisplay!!.loading)
        assertNull(latestDisplay!!.errorMessage)
        assertEquals(expectedCocktails, latestDisplay!!.cocktails)
    }
}