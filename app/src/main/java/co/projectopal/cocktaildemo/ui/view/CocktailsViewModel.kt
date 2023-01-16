package co.projectopal.cocktaildemo.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.projectopal.cocktaildemo.data.models.Cocktail
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository
import co.projectopal.cocktaildemo.data.repositories.CocktailRepository.GetCocktailListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CocktailsViewModel(
    private val repository: CocktailRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    data class Display(
        val loading: Boolean = false,
        val errorMessage: String? = null,
        val cocktails: List<Cocktail>? = null
    )

    private val _display = MutableLiveData(Display())
    val display: LiveData<Display> = _display

    private val emptyTermError = "Please enter a search term!"
    private val searchFailedError = "Something went wrong!"

    fun search(term: String) {
        if (term.length == 1) {
            searchByLetter(term)
        } else if (term.length > 1) {
            searchByName(term)
        } else {
            mapDisplay(errorMessage = emptyTermError)
        }
    }

    private fun searchByName(name: String) {
        viewModelScope.launch(ioDispatcher) {
            mapDisplay(loading = true)
            when (val response = repository.getCocktailsByName(name = name)) {
                GetCocktailListResponse.Failure -> mapDisplay(errorMessage = searchFailedError)
                is GetCocktailListResponse.Success -> mapDisplay(cocktails = response.cocktails)
            }
        }
    }

    private fun searchByLetter(letter: String) {
        viewModelScope.launch(ioDispatcher) {
            mapDisplay(loading = true)
            when (val response = repository.getCocktailsByFirstLetter(letter = letter)) {
                GetCocktailListResponse.Failure -> mapDisplay(errorMessage = searchFailedError)
                is GetCocktailListResponse.Success -> mapDisplay(cocktails = response.cocktails)
            }
        }
    }

    private fun mapDisplay(
        loading: Boolean = false,
        errorMessage: String? = null,
        cocktails: List<Cocktail>? = null
    ) {
        _display.postValue(
            Display(loading = loading, errorMessage = errorMessage, cocktails = cocktails)
        )
    }
}