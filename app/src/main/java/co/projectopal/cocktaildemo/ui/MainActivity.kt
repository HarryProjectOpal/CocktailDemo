package co.projectopal.cocktaildemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.projectopal.cocktaildemo.ui.theme.CocktailDemoTheme
import co.projectopal.cocktaildemo.ui.view.CocktailsViewModel
import co.projectopal.cocktaildemo.ui.view.SearchCocktailsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<CocktailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailDemoTheme {
                SearchCocktailsScreen(viewModel = viewModel)
            }
        }
    }
}
