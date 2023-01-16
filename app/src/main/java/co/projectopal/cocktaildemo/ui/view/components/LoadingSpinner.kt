package co.projectopal.cocktaildemo.ui.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.projectopal.cocktaildemo.ui.theme.CocktailDemoTheme

@Composable
fun LoadingSpinner(isDisplayed: Boolean, color: Color, modifier: Modifier) {
    if (isDisplayed) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(color = color, strokeWidth = 3.dp)
        }
    }
}

@Preview(showBackground = true, name = "Loading Spinner", widthDp = 320)
@Composable
fun LoadingSpinnerPreview() {
    CocktailDemoTheme() {
        LoadingSpinner(
            isDisplayed = true,
            color = Color.Magenta,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(50.dp)
        )
    }
}