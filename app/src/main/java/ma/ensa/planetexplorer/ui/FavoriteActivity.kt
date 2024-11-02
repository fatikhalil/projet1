package ma.ensa.planetexplorer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ma.ensa.planetexplorer.adapter.FavoriteAdapter
import ma.ensa.planetexplorer.databinding.ActivityFavoritesBinding
import ma.ensa.planetexplorer.utils.FavoriteManager

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var adapter: FavoriteAdapter // Déclarez une variable pour l'adaptateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupérer la liste des planètes favorites
        val favoritePlanets = FavoriteManager.getFavorites().toMutableList() // Convertir en MutableList

        // Initialiser le RecyclerView avec les favoris
        adapter = FavoriteAdapter(favoritePlanets) { planet ->
            favoritePlanets.remove(planet) // Retirer la planète de la liste
            adapter.notifyDataSetChanged() // Mettre à jour l'affichage sur l'adaptateur
        }

        binding.recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = this@FavoriteActivity.adapter // Utilisez l'adaptateur que vous avez créé
        }
    }
}
