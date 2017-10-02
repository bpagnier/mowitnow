MowItNow - Exercice technique
===================

**MowItNow** est un exercice de programation r�pondant � une sp�cification de besoin pr�cise.

----------

## Environnement technique

Type     | Technologie
-------- | ---
Programmation            | Java 8
Outil de build           | Maven
Gestionnaire de sources  | Git (https://github.com/bpagnier/mowitnow)
logging                  | Logback
JUnit                    | tests unitaires
Divers                   | commons-lang (fonctionnalit�s suppl�mentaires autour de java.lang)

## Organisation des sources

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

## Test de l'application

Le plus simple pour tester l'application est d�ex�cuter le test JUnit **mowersMustMoveCorrectly()** de la classe **LawnServiceTest**. La console affiche la progression des tondeuses (en fonction du niveau de log d�fini dans **logback.xml**) et le test v�rifie qu'une fois les d�placements termin�s, les tondeuses se trouvent bien aux positions attendues.

On peut �galement tester le parsing des fichiers d'entr�e. Le format attendu est celui d�fini dans la sp�cification, � savoir :

> Pour programmer la tondeuse, on lui fournit un fichier d'entr�e construit comme suit : 
> - La premi�re ligne correspond aux coordonn�es du coin sup�rieur droit de la pelouse, celles  du coin inf�rieur gauche sont suppos�es �tre (0,0) 
> - La  suite  du  fichier  permet  de  piloter  toutes  les  tondeuses  qui  ont  �t�  d�ploy�es. Chaque tondeuse a deux lignes la concernant : 
> - la premi�re ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, s�par�s par un espace 
> - la  seconde  ligne  est  une  s�rie  d'instructions  ordonnant  �  la  tondeuse  d'explorer  la pelouse. Les instructions sont une suite de caract�res sans espaces. Les lettres possibles sont � D �, � G � et � A �. � D � et � G � font pivoter la tondeuse de 90� � droite ou � gauche respectivement, sans la d�placer. � A � signifie que l'on avance la tondeuse d'une case dans la direction � laquelle elle fait face, et sans modifier son orientation. 

