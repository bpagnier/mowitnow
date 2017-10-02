MowItNow - Exercice technique
===================

**MowItNow** est un exercice de programation répondant à une spécification de besoin précise.

----------

#### Environnement technique

Type     | Technologie
-------- | ---
Programmation            | Java 8
Outil de build           | Maven
Gestionnaire de sources  | Git (https://github.com/bpagnier/mowitnow)
logging                  | Logback
JUnit                    | tests unitaires
Divers                   | commons-lang (fonctionnalités supplémentaires autour de java.lang)

#### Organisation des sources

Les sources sont réparties entre trois packages :

**model** : classes du modèle qui contient les objets suivants :
 - Mower (tondeuse)
 - Lawn (pelouse)
 - Direction (orientation des tondeuse)
 - Instruction (ordre à destination des tondeuses)
 - Location (coordonnées sur la pelouse, avec l'orientation)

**service** : services qui contiennent la logique de l'application :
 - InstructionService : parse un fichier texte pour créer les instructions correspondantes
 - LawnService : gère les déplacement des tondeuses sur la pelouse

 **exception** : les exceptions de l'application

Il existe également un package contenant les tests unitaires JUnit.

#### Test de l'application

Le plus simple pour tester l'application est d’exécuter le test JUnit **mowersMustMoveCorrectly()** de la classe **LawnServiceTest**. La console affiche la progression des tondeuses (en fonction du niveau de log défini dans **logback.xml**) et le test vérifie qu'une fois les déplacements terminés, les tondeuses se trouvent bien aux positions attendues.
