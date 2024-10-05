# Yoga App - Guide d'utilisation des tests

## Description
Ce projet est une application de gestion de sessions de yoga, développée avec **Angular** pour le frontend, et **Spring Boot** pour le backend. Nous avons implémenté différents types de tests pour assurer la qualité du code :
- **Tests unitaires** avec **Jest** pour le frontend (Angular).
- **Tests E2E (End-to-End)** avec **Cypress** pour tester l'application dans son ensemble.
- **Tests unitaires backend** avec **JUnit** pour assurer la validité de la logique métier et des services backend.

## Prérequis
Avant de lancer les tests, assurez-vous d'avoir les outils suivants installés sur votre machine :
- [Node.js](https://nodejs.org/) (v18+)
- [npm](https://www.npmjs.com/) (v8+)
- [Angular CLI](https://angular.io/cli) (v15+)
- [Maven](https://maven.apache.org/) (v3.8+)
- [Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) (JDK 17)

## Installation

### Cloner le dépôt
```bash
git clone https://github.com/your-username/yoga-app.git
cd yoga-app
```

### Installer les dépendances frontend
Naviguez dans le répertoire `frontend/` et installez les dépendances npm :
```bash
cd frontend/
npm install
```

### Installer les dépendances backend
Naviguez dans le répertoire `backend/` et installez les dépendances Maven :
```bash
cd backend/
mvn clean install
```

## Exécution des tests

### 1. Tests unitaires du frontend avec Jest
Pour lancer les tests unitaires du frontend avec Jest :
```bash
cd frontend/
npm run test
```
Les résultats des tests unitaires s'afficheront dans la console, et un rapport de couverture sera généré dans le dossier `coverage/`. Pour visualiser le rapport :
```bash
open coverage/lcov-report/index.html
```

### 2. Tests E2E (End-to-End) avec Cypress
Pour lancer les tests E2E avec Cypress, vous devez d'abord vous assurer que l'application frontend est en cours d'exécution. Exécutez la commande suivante pour démarrer l'application Angular :
```bash
npm start
```
Puis, dans un autre terminal, lancez Cypress avec la commande suivante :
```bash
npm run e2e
```
Cela ouvrira l'interface Cypress. Sélectionnez le test souhaité et lancez-le.

### 3. Tests backend avec JUnit
Pour lancer les tests backend avec JUnit, assurez-vous que vous êtes dans le répertoire `backend/` et exécutez les tests avec Maven :
```bash
cd backend/
mvn clean test
```
Un rapport de couverture sera généré avec **JaCoCo** dans le dossier `target/site/jacoco`. Pour ouvrir le rapport de couverture, exécutez :
```bash
open target/site/jacoco/index.html
```

## Rapports de couverture

### Frontend (Jest)
Les rapports de couverture des tests unitaires Angular se trouvent dans :
```
frontend/coverage/lcov-report/index.html
```

### Backend (JUnit + JaCoCo)
Les rapports de couverture des tests backend se trouvent dans :
```
backend/target/site/jacoco/index.html
```

## Soumission du rapport
Après avoir exécuté les tests et obtenu les rapports de couverture, merci de me les partager en envoyant un lien vers votre dépôt GitHub et en incluant le fichier de rapport de couverture.

---

## Lancement de l'application

### Frontend
Pour démarrer le frontend localement, exécutez la commande suivante dans le répertoire `frontend/` :
```bash
npm start
```
L'application sera accessible sur `http://localhost:4200`.

### Backend
Pour démarrer le backend Spring Boot, exécutez la commande suivante dans le répertoire `backend/` :
```bash
mvn spring-boot:run
```
Le backend sera accessible sur `http://localhost:8080`.

---

N’hésitez pas à me contacter pour toute question ou assistance supplémentaire !

---

**Note:** Assurez-vous d'avoir bien configuré vos bases de données et fichiers de configuration pour le backend avant de lancer les tests ou l'application.
