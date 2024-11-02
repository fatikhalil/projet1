package ma.ensa.planetexplorer.beans
data class Planet(
    val name: String,
    val description: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)
