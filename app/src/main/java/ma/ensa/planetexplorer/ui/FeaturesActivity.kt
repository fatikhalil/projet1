// FeaturesActivity.kt
package ma.ensa.planetexplorer.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ma.ensa.planetexplorer.databinding.ActivityFeaturesBinding

class FeaturesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeaturesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bouton pour passer à MainActivity
        binding.suivantButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
