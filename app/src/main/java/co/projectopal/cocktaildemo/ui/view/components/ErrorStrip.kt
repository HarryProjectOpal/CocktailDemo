package co.projectopal.cocktaildemo.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.projectopal.cocktaildemo.ui.theme.CocktailDemoTheme

@Composable
fun ErrorStrip(modifier: Modifier = Modifier, message: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
            text = message,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true,  name = "Error Strip")
@Composable
private fun ErrorStripPreview() {
    CocktailDemoTheme() {
        ErrorStrip(message = "Something went wrong!")
    }
}