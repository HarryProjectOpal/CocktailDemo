package co.projectopal.cocktaildemo.data.models

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("idDrink") val id: Long,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strGlass") val glass: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strDrinkThumb") val imageUrl: String,
    @SerializedName("strIngredient1") val firstIngredient: String? = null,
    @SerializedName("strIngredient2") val secondIngredient: String? = null,
    @SerializedName("strIngredient3") val thirdIngredient: String? = null,
    @SerializedName("strIngredient4") val fourthIngredient: String? = null,
    @SerializedName("strIngredient5") val fifthIngredient: String? = null,
    @SerializedName("strIngredient6") val sixthIngredient: String? = null,
    @SerializedName("strMeasure1") val firstMeasure: String? = null,
    @SerializedName("strMeasure2") val secondMeasure: String? = null,
    @SerializedName("strMeasure3") val thirdMeasure: String? = null,
    @SerializedName("strMeasure4") val fourthMeasure: String? = null,
    @SerializedName("strMeasure5") val fifthMeasure: String? = null,
    @SerializedName("strMeasure6") val sixthMeasure: String? = null,
)
