package ma.ensa.planetexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ma.ensa.planetexplorer.beans.Planet
import ma.ensa.planetexplorer.databinding.PlanetItemBinding

class PlanetAdapter(
    private val planets: List<Planet>,
    private val onPlanetClick: (Planet) -> Unit,
    private val onPlanetDoubleTap: (Planet) -> Unit
) : RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    inner class PlanetViewHolder(private val binding: PlanetItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: Planet) {
            binding.planetName.text = planet.name
            binding.planetImage.setImageResource(planet.imageResId)

            binding.root.setOnClickListener { onPlanetClick(planet) }
            binding.root.setOnLongClickListener {
                onPlanetDoubleTap(planet)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val binding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount(): Int = planets.size
}
