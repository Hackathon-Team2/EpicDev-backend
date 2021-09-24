# EpicDev-backend
# Bien démarrer

### Prérequis :
- Installer Java 8 
- Installer Maven : https://maven.apache.org/download.cgi
- Installer IntelliJ : https://www.jetbrains.com/fr-fr/idea/download/#section=windows
  (Ajouter Java et Maven dans les variables d'environnement).

### Clone du repo
~~~~
git clone https://github.com/Hackathon-Team2/EpicDev-backend.git
~~~~

### Instalation des dépendances
~~~~
mvn install
~~~~

### Supervison
Pour s'assurer que l'API s'est bien lancée, sur un navigateur lancer : http://127.0.0.1:8090/actuator/health/
- Status : Up => l'API s'est lancée sans soucis.
- Status : Down => vérifier sur IntelliJ pourquoi l'API ne lance pas.

Controleur test : http://localhost:8090/TestApi

Swagger : http://127.0.0.1:8090/swagger-ui.html

# Build et test