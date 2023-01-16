package co.projectopal.cocktaildemo.data.models

val testCocktail = createTestCocktail()

fun createTestCocktail(
    name: String = "Mojito",
    glass: String = "Highball",
    instructions: String = "instructions",
    imageUrl: String = "imageUrl"
) = Cocktail(id = 0, name = name, glass = glass, instructions = instructions, imageUrl = imageUrl)