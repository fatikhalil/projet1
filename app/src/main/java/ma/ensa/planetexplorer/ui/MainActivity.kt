package ma.ensa.planetexplorer.ui

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ma.ensa.planetexplorer.R
import ma.ensa.planetexplorer.beans.Planet
import ma.ensa.planetexplorer.databinding.ActivityMainBinding
import ma.ensa.planetexplorer.utils.FavoriteManager

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gestureDetector: GestureDetector
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var currentPlanetIndex = 0
    private var scaleFactor = 1.0f

    private val planets = listOf(
        Planet(
            "Mercure",
            "Mercure est la planète la plus proche du Soleil, située à environ 58 millions de kilomètres de celui-ci. Avec un diamètre de seulement 4 880 km, elle est la plus petite planète du système solaire. Elle tourne lentement sur elle-même, effectuant seulement 1,5 rotation pour chaque orbite autour du Soleil, ce qui provoque des températures extrêmes. La surface de Mercure est marquée par de nombreux cratères formés par des impacts de météorites. Elle n'a presque pas d'atmosphère, ce qui signifie qu'il n'y a pas de protection contre les fluctuations de température, variant de 430°C en journée à -180°C la nuit.",
            R.drawable.mercury
        ),
        Planet(
            "Vénus",
            "Vénus, la deuxième planète du système solaire, est de taille similaire à la Terre, mais son environnement est extrêmement hostile. Elle est enveloppée d'une atmosphère dense composée de dioxyde de carbone, avec des nuages d'acide sulfurique. Ces conditions créent un effet de serre intense qui fait de Vénus la planète la plus chaude, avec des températures en surface avoisinant les 470°C. La pression atmosphérique y est 92 fois celle de la Terre. Vénus est également connue pour sa rotation rétrograde, tournant dans le sens opposé des autres planètes.",
            R.drawable.venus
        ),
        Planet(
            "Terre",
            "La Terre, troisième planète à partir du Soleil, est la seule planète connue à abriter la vie. Son atmosphère est composée de 78% d'azote, 21% d'oxygène et des traces d'autres gaz, offrant des conditions idéales pour la vie. La planète est unique grâce à sa biosphère, l'abondance d'eau liquide, et son champ magnétique qui la protège du vent solaire. Elle possède un climat tempéré, une biodiversité riche, et une tectonique des plaques qui régule les températures sur de longues périodes.",
            R.drawable.earth
        ),
        Planet(
            "Saturne",
            "Saturne est la sixième planète du système solaire et est facilement reconnaissable grâce à ses anneaux spectaculaires constitués de glace et de débris rocheux. C'est une géante gazeuse, composée d'hydrogène et d'hélium. Saturne a un champ magnétique intense et de nombreuses lunes, notamment Titan, qui possède une atmosphère dense et des lacs de méthane liquide.",
            R.drawable.saturn
        ),
        Planet(
            "Uranus",
            "Uranus est la septième planète du système solaire et la première à avoir été découverte à l'aide d'un télescope. Elle a une atmosphère principalement composée de méthane, d'hélium et d'hydrogène, lui donnant une couleur bleuâtre. Uranus est unique en raison de son inclinaison extrême, qui fait qu'elle « roule » sur son orbite autour du Soleil, avec un axe de rotation presque parallèle à son plan orbital.",
            R.drawable.uranus
        ),
        Planet(
            "Mars",
            "Mars est surnommée la planète rouge en raison de la couleur de son sol, riche en oxyde de fer. Elle est la quatrième planète du système solaire et mesure environ la moitié du diamètre de la Terre. Mars possède les montagnes les plus hautes et les canyons les plus profonds du système solaire, notamment Olympus Mons, le plus grand volcan. Des traces de rivières asséchées et de formations rocheuses indiquent la présence passée d'eau liquide, ce qui alimente les recherches sur la possibilité de vie microbienne passée.",
            R.drawable.mars
        ),
        Planet(
            "Jupiter",
            "Jupiter est la plus grande planète du système solaire, avec un diamètre de 142 984 km. C'est une géante gazeuse composée principalement d'hydrogène et d'hélium. Elle est célèbre pour sa Grande Tache Rouge, une tempête persistante plus grande que la Terre. Jupiter possède un puissant champ magnétique et plus de 79 lunes, dont certaines, comme Europa, pourraient abriter des océans sous leur surface glacée.",
            R.drawable.jupiter
        ),

        Planet(
            "Neptune",
            "Neptune, la huitième et dernière planète du système solaire, est une géante gazeuse avec une couleur bleue intense due à la présence de méthane dans son atmosphère. Elle est célèbre pour ses vents violents, les plus rapides du système solaire, atteignant plus de 2 000 km/h. Neptune possède également plusieurs anneaux fins et au moins 14 lunes, la plus grande étant Triton, qui orbite dans le sens opposé de sa rotation.",
            R.drawable.neptune
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gestureDetector = GestureDetector(this, this)
        gestureDetector.setOnDoubleTapListener(this)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        // Affiche la première planète
        displayPlanet()

        binding.planetImage.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            scaleGestureDetector.onTouchEvent(event)
            true
        }

        // Configuration du bouton pour afficher les favoris
        binding.buttonShowFavorites.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayPlanet() {
        val planet = planets[currentPlanetIndex]
        binding.planetImage.setImageResource(planet.imageResId)
        binding.planetImage.scaleX = scaleFactor
        binding.planetImage.scaleY = scaleFactor
        binding.planetName.text = planet.name
    }

    private fun showPlanetInfo(planet: Planet) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(planet.name)
            .setMessage(planet.description)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }

    private fun toggleFavorite(planet: Planet) {
        FavoriteManager.toggleFavorite(planet)
        val message = if (planet.isFavorite) "${planet.name} ajoutée aux favoris" else "${planet.name} retirée des favoris"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        val selectedPlanet = planets[currentPlanetIndex]
        showPlanetInfo(selectedPlanet)
        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {
        val selectedPlanet = planets[currentPlanetIndex]
        toggleFavorite(selectedPlanet)
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        // Faire tourner la planète sur un appui long
        binding.planetImage.animate().rotationBy(360f).setDuration(1000).start()
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1 == null || e2 == null) return false

        val deltaX = e2.x - e1.x

        if (Math.abs(deltaX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (deltaX > 0) {
                showPreviousPlanet()
            } else {
                showNextPlanet()
            }
            return true
        }

        return false
    }

    private fun showNextPlanet() {
        if (currentPlanetIndex < planets.size - 1) {
            currentPlanetIndex++
            displayPlanet()
        } else {
            Toast.makeText(this, "Vous êtes déjà à la dernière planète", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPreviousPlanet() {
        if (currentPlanetIndex > 0) {
            currentPlanetIndex--
            displayPlanet()
        } else {
            Toast.makeText(this, "Vous êtes déjà à la première planète", Toast.LENGTH_SHORT).show()
        }
    }

    // Implémentations vides pour les méthodes de GestureDetector
    override fun onDown(p0: MotionEvent): Boolean = true
    override fun onShowPress(p0: MotionEvent) {}
    override fun onSingleTapUp(p0: MotionEvent): Boolean = true
    override fun onScroll(
        e1: MotionEvent?,
        p1: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean = true
    override fun onDoubleTapEvent(p0: MotionEvent): Boolean = true

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = scaleFactor.coerceIn(0.1f, 5.0f) // Limiter l'échelle entre 0.1x et 5.0x
            binding.planetImage.scaleX = scaleFactor
            binding.planetImage.scaleY = scaleFactor
            return true
        }
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100 // La distance minimale pour un swipe (en pixels)
        private const val SWIPE_VELOCITY_THRESHOLD = 100 // La vitesse minimale pour un swipe (en pixels par seconde)
    }
}
