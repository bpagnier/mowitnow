MowItNow - Exercice technique
===================

**MowItNow** est un exercice de programation r�pondant � une sp�cification de besoin pr�cise.

----------

#### Environnement technique

Type     | Technologie
-------- | ---
Programmation            | Java 8
Outil de build           | Maven
Gestionnaire de sources  | Git (https://github.com/bpagnier/mowitnow)
logging                  | Logback
JUnit                    | tests unitaires
Divers                   | commons-lang (fonctionnalit�s suppl�mentaires autour de java.lang)

#### Organisation des sources

Les sources sont r�parties entre trois packages :

**model** : classes du mod�le qui contient les objets suivants :
 - Mower (tondeuse)
 - Lawn (pelouse)
 - Direction (orientation des tondeuse)
 - Instruction (ordre � destination des tondeuses)
 - Location (coordonn�es sur la pelouse, avec l'orientation)

**service** : services qui contiennent la logique de l'application :
 - InstructionService : parse un fichier texte pour cr�er les instructions correspondantes
 - LawnService : g�re les d�placement des tondeuses sur la pelouse

 **exception** : les exceptions de l'application

Il existe �galement un package contenant les tests unitaires JUnit.

#### Test de l'application

Le plus simple pour tester l'application est d�ex�cuter le test JUnit **mowersMustMoveCorrectly()** de la classe **LawnServiceTest**. La console affiche la progression des tondeuses (en fonction du niveau de log d�fini dans **logback.xml**) et le test v�rifie qu'une fois les d�placements termin�s, les tondeuses se trouvent bien aux positions attendues.
