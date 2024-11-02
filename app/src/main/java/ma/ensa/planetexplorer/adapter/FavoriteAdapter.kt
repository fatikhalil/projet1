package ma.ensa.planetexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ma.ensa.planetexplorer.R
import ma.ensa.planetexplorer.beans.Planet
import ma.ensa.planetexplorer.databinding.FavoriteItemBinding
import ma.ensa.planetexplorer.utils.FavoriteManager

class FavoriteAdapter(
    private val favoritePlanets: MutableList<Planet>, // Utiliser MutableList pour permettre la modification
    private val onFavoriteRemoved: (Planet) -> Unit // Fonction de rappel pour retirer le favori
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(private val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet) {
            binding.planetName.text = planet.name
            binding.planetImage.setImageResource(planet.imageResId)
            binding.favoriteIcon.setImageResource(R.drawable.ic_favorite) // Icône de favoris

            // Gérer le clic sur l'icône de favoris
            binding.favoriteIcon.setOnClickListener {
                // Retirer la planète des favoris
                FavoriteManager.toggleFavorite(planet)
                onFavoriteRemoved(planet) // Appeler la fonction de rappel
                notifyItemRemoved(adapterPosition) // Mettre à jour l'affichage
                Toast.makeText(binding.root.context, "${planet.name} retirée des favoris", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoritePlanets[position])
    }

    override fun getItemCount(): Int = favoritePlanets.size
}
