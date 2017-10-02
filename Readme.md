MowItNow - Exercice technique
===================

**MowItNow** est un exercice de programation répondant à une spécification de besoin précise.

----------

## Environnement technique

Type     | Technologie
-------- | ---
Programmation            | Java 8
Outil de build           | Maven
Gestionnaire de sources  | Git (https://github.com/bpagnier/mowitnow)
logging                  | Logback
JUnit                    | tests unitaires
Divers                   | commons-lang (fonctionnalités supplémentaires autour de java.lang)

## Organisation des sources

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

## Test de l'application

Le plus simple pour tester l'application est d’exécuter le test JUnit **mowersMustMoveCorrectly()** de la classe **LawnServiceTest**. La console affiche la progression des tondeuses (en fonction du niveau de log défini dans **logback.xml**) et le test vérifie qu'une fois les déplacements terminés, les tondeuses se trouvent bien aux positions attendues.

On peut également tester le parsing des fichiers d'entrée. Le format attendu est celui défini dans la spécification, à savoir :

> Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit : 
> - La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles  du coin inférieur gauche sont supposées être (0,0) 
> - La  suite  du  fichier  permet  de  piloter  toutes  les  tondeuses  qui  ont  été  déployées. Chaque tondeuse a deux lignes la concernant : 
> - la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, séparés par un espace 
> - la  seconde  ligne  est  une  série  d'instructions  ordonnant  à  la  tondeuse  d'explorer  la pelouse. Les instructions sont une suite de caractères sans espaces. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation. 

