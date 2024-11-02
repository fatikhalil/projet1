package ma.ensa.planetexplorer.utils

import ma.ensa.planetexplorer.beans.Planet

object FavoriteManager {
    private val favoritePlanets = mutableListOf<Planet>()

    fun toggleFavorite(planet: Planet) {
        if (favoritePlanets.contains(planet)) {
            favoritePlanets.remove(planet)
            planet.isFavorite = false
        } else {
            favoritePlanets.add(planet)
            planet.isFavorite = true
        }
    }

    fun getFavorites(): List<Planet> {
        return favoritePlanets
    }
}
