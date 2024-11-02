package ma.ensa.planetexplorer.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ma.ensa.planetexplorer.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Message de bienvenue et description
        binding.welcomeMessage.text = "Bienvenue sur PlanetExplorer ！Explorez les planètes et découvrez leurs mystères."

        // Configurer le bouton pour lancer l'application
        binding.startButton.setOnClickListener {
            val intent = Intent(this, FeaturesActivity::class.java)
            startActivity(intent)
            finish() // Ferme WelcomeActivity
        }
    }
}
