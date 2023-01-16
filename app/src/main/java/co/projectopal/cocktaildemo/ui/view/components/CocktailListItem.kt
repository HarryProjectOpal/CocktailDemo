package co.projectopal.cocktaildemo.ui.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.projectopal.cocktaildemo.R
import co.projectopal.cocktaildemo.ui.theme.CocktailDemoTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CocktailListItem(imageUrl: String, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier.size(40.dp),
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            previewPlaceholder = R.drawable.glide_preview_placeholder
        )
        Text(modifier = Modifier.padding(start = 4.dp), text = name, fontSize = 16.sp, color = Color.Black)
    }
}

@Preview(showBackground = true, name = "List Item")
@Composable
private fun CocktailListItemPreview() {
    CocktailDemoTheme() {
        CocktailListItem(imageUrl = "", name = "Mojito")
    }
}
