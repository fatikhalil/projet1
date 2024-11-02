
# PlanetExplorer

PlanetExplorer est une application Android conçue pour explorer les planètes de notre système solaire à travers des gestes intuitifs. L'application permet aux utilisateurs de naviguer entre les planètes, d'en apprendre davantage sur chacune, et de créer une liste de favoris pour un accès rapide.

## Table des matières
1. [Fonctionnalités](#fonctionnalités)
2. [Technologies Utilisées](#technologies-utilisées)
3. [Installation](#installation)
4. [Utilisation](#utilisation)
5. [Aperçu de l'Interface](#aperçu-de-linterface)
6. [Conception et Développement](#conception-et-développement)


## Fonctionnalités
- **Exploration des planètes** : Affiche les détails des planètes avec des descriptions et des images uniques.
- **Navigation par gestes** : Utilise des gestes tels que le swipe, le tap, le double tap et le long press pour une navigation interactive.
- **Liste de favoris** : Ajoutez des planètes aux favoris via un double tap et accédez à la liste des favoris par un triple tap.
- **Zoom sur les images** : Permet de zoomer sur les images des planètes pour mieux apprécier les détails.
- **Page de démarrage** : Une page d'accueil accueillante avec un bouton "Commencer", un message de bienvenue, et une description de l'application.

## Technologies Utilisées
- **Kotlin** : Langage principal utilisé pour le développement Android.
- **RecyclerView** : Pour afficher la liste des planètes et la liste des favoris.
- **ViewBinding** : Pour une gestion efficace des vues.
- **GestureDetector** : Pour la gestion des gestes de navigation et d'interaction avec les éléments.
- **AlertDialog** : Pour afficher des informations détaillées sur chaque planète.

## Installation
1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/fatikhalil/projet1.git
   ```
2. Ouvrez le projet dans Android Studio.
3. Exécutez l'application sur un émulateur ou un appareil physique.

## Utilisation
1. **Lancement** : Ouvrez l'application pour afficher la page de démarrage. Cliquez sur le bouton "Commencer" pour accéder à l'interface principale.
2. **Explorer les planètes** : Balayez (swipe) pour naviguer entre les planètes. Tapez sur une planète pour afficher des informations supplémentaires.
3. **Ajouter aux favoris** : Effectuez un double tap sur une planète pour l'ajouter ou la retirer de vos favoris.
4. **Accéder aux favoris** : Effectuez un triple tap pour afficher la liste des planètes ajoutées aux favoris.
5. **Zoom** : Utilisez le geste de pincement pour zoomer sur l'image d'une planète.

## Aperçu de l'Interface
### Page de démarrage
- Une image de bienvenue, un message de bienvenue, et une description.
- Un bouton "Commencer" pour accéder à l'interface principale.

### Interface principale
- **Liste des planètes** : Affiche les planètes avec leur nom et image. Permet de swiper pour naviguer.
- **Détail des planètes** : Affiche les détails lorsque vous appuyez sur une planète.
- **Zoom** : Pincez pour zoomer sur une planète.

### Liste des Favoris
- Accédez aux favoris avec un triple tap pour afficher les planètes ajoutées.

## Conception et Développement
### Structure du Projet
Le projet suit une architecture simple mais modulaire, en utilisant des activités principales pour les interactions majeures :
- **MainActivity** : Gestion des gestes, navigation entre les planètes, zoom.
- **FavoritesActivity** : Affiche la liste des favoris ajoutés.
- **PlanetAdapter** : Adapte la vue RecyclerView pour afficher la liste des planètes.
- **FavoriteManager** : Gère l'ajout et la suppression des planètes aux favoris.

### Classes et Méthodes Principales
- **Planet** : Modèle de données représentant une planète avec son nom, description, et état de favori.
- **GestureDetector** : Utilisé pour détecter et gérer les gestes spécifiques.
- **ScaleGestureDetector** : Permet le zoom des images.

