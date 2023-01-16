package co.projectopal.cocktaildemo.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.projectopal.cocktaildemo.data.models.Cocktail
import co.projectopal.cocktaildemo.ui.theme.CocktailDemoTheme
import co.projectopal.cocktaildemo.ui.view.components.CocktailListItem
import co.projectopal.cocktaildemo.ui.view.components.ErrorStrip
import co.projectopal.cocktaildemo.ui.view.components.LoadingSpinner
import co.projectopal.cocktaildemo.ui.view.components.SearchView

@Composable
fun SearchCocktailsScreen(viewModel: CocktailsViewModel) {
    val display by viewModel.display.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        display?.let {
            SearchCocktailsContent(
                cocktails = it.cocktails,
                loading = it.loading,
                errorMessage = it.errorMessage,
                onSearch = { term -> viewModel.search(term = term) }
            )
        }
    }
}

@Composable
private fun SearchCocktailsContent(
    cocktails: List<Cocktail>?,
    loading: Boolean,
    errorMessage: String?,
    onSearch: (term: String) -> Unit
) {
    Box() {
        LoadingSpinner(
            isDisplayed = loading,
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize()) {
            SearchView() { onSearch(it) }
            cocktails?.let {
                LazyColumn() {
                   items(cocktails, itemContent = { content ->
                       CocktailListItem(imageUrl = content.imageUrl, name = content.name)
                   })
                }
            }

        }
        AnimatedVisibility(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            visible = errorMessage != null,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            errorMessage?.let {
                ErrorStrip(message = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchCocktailsContentLoadingPreview() {
    CocktailDemoTheme() {
        SearchCocktailsContent(cocktails = null, loading = true, errorMessage = null, onSearch = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchCocktailsContentErrorPreview() {
    CocktailDemoTheme() {
        SearchCocktailsContent(
            cocktails = null,
            loading = false,
            errorMessage = "Something Went Wrong!",
            onSearch = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchCocktailsContentPopulatedPreview() {
    CocktailDemoTheme() {
        SearchCocktailsContent(
            cocktails = demoData,
            loading = false,
            errorMessage = null,
            onSearch = {})
    }
}

private val demoData = listOf(
    Cocktail(
        id = 0,
        name = "Mojito",
        glass = "Tall",
        instructions = "instructions",
        imageUrl = "imageUrl"
    ),
    Cocktail(
        id = 0,
        name = "Old Fashioned",
        glass = "Tall",
        instructions = "instructions",
        imageUrl = "imageUrl"
    ),
    Cocktail(
        id = 0,
        name = "Whiskey Sour",
        glass = "Tall",
        instructions = "instructions",
        imageUrl = "imageUrl"
    )
)