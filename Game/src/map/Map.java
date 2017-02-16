package map;

import units.Combattant;
import units.Troupe;
import util.Utilitaires;

/**
 * 
 * @author Lancelot de Wouters
 * 3/1/2016
 * 
 */
public class Map {

	/*
	 * TO THINK ABOUT
	 * 
	 * le "graphisme" de la map est des String (j'avoue n'avoir pas la moindre connaissance en graphisme)
	 * je connais non plus pas d'autre moyen d'afficher qqch à l'écran que via un String (sysout est mon meilleur ami)
	 * néanmoins peut-être pourrais-je trouver un autre type que String comme attribut de ma map?
	 * 
	 * il est ennuyeux et surtout peu optimal de passer en revue toute ma table à deux dimensions de troupes(Troupe[] --> +- = à Combattant[][])
	 * pour retrouver la coordonnée d'un combattant (méthode selctionnerCoordonnee()) même si le cout en performance reste faible 
	 * vu que l'ensemble des éléments de cette table est de maximum 45 (9 troupes x 5 combattants)
	 * 
	 * ainsi peut-être que le type de ma map devrait-être des Combattant ou des Coordonnee dont j'adapterai le toString() pour l'affichage de la console...
	 * si je procède de tel façon, il sera plus simple de comparer leur coordonnées...
	 * à méditer...
	 * 
	 */


	/*
	 * TODO
	 * 
	 * 
	 */
	public static final String CASE_VIDE = "____|";
	private String[][] map;
	private int largeur;
	private int longueur;

	// augmenter la taille des cases de 1 en hauteur (ou en largeur?) pour pouvoir inscrire le numéro de la troupe

	// /!\ ne permet pas de gérer une longueur plus grande que 99 et une hauteur plus grande que 'Z' (26) 
	// (--> techniquement oui mais la numérotation sera bizarre et la classe coordonnee 
	//   ne permettra pas les débordements en dehors de ces limites)
	public Map(int largeur, int longueur){
		if(largeur <= 0 || longueur <= 0) throw new IllegalArgumentException("Paramètre invalide");

		this.largeur = largeur;
		this.longueur = longueur;
		map = new String[largeur][longueur];

		int numColonnes = 1;
		char refLignes = 'A';
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < longueur; j++) {
				if(i == 0 && j == 0) map[i][j] = "   ";
				else if(i == 0) {
					map[i][j] = " " + numColonnes + "  ";
					if(numColonnes < 10) map[i][j] += " "; 
					numColonnes++;
				} else if (j == 0){
					map[i][j] = refLignes + " |";
					refLignes++;
				}
				else map[i][j] = CASE_VIDE;
			}
		}

	}// constructeur

	public int getLargeur() {
		return largeur;
	}
	public int getLongueur() {
		return longueur;
	}

	// méthode affichant tous les composants de la map, en 2D
	public void affichage(){
		String aAfficher = "";
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				aAfficher += map[i][j];
			}
			aAfficher += "\n";
		}
		System.out.println(aAfficher);
	}

	/*
	 *  méthode inutile qui était à l'origine de l'affichage de la map
	 *  est devenu obsolète car elle ne travaille pas sur un objet "map" et est donc difficilement modifiable
	 *  cependant la difficulté éprouvée à l'implémentation ne me donne pas le coeur de l'effacer
	 *  vous pouvez appeler ça la " sensibilité du développeur "
	 *  (oui, oui, j'ai un coeur !)
	 */

//	public void affichage2(){
//		String map ="";
//		int numColonnes = 1;
//		for (char i = '@'; i <= '@' + this.map.length; i++) { // lignes
//			map += i + " ";
//			for (int j = 0; j < this.map[0].length; j++) { // numColonnes
//				if(i == '@'){
//					map += numColonnes;
//					if(numColonnes < 10)
//						map += "  ";
//					else
//						map += " ";
//					numColonnes++;
//				}else					
//					map += "__|";
//			}
//			map += "\n";
//		}
//		System.out.println(map);
//	}

	// méthode remplaçant une case " __| " par le nom du pion d'un combattant
	public void placerPion(Coordonnee coordonnee, String nomPion){
		int x = Utilitaires.convertiCharEnInt(coordonnee.getLigne());
		int y = coordonnee.getColonne();

		this.map[x][y] = nomPion +"|";
	}

	/*
	 * Place une troupe (de 1 à 5 combattants) autour d'un point de départ selon le schéma suivant :
	 * 
	 * 		X		X
	 * 		   PdD
	 * 		X		X
	 * 
	 */
	public void placerUneTroupe(Troupe troupe, Coordonnee pointDeDepart){
		Combattant[] tabCombattant = troupe.getListeCombattant();

		for (int i = 0; i < troupe.getNbCombattantVivant(); i++) {
			Combattant combattant = tabCombattant[i];
			char x = pointDeDepart.getLigne();
			int y = pointDeDepart.getColonne();
			switch(i){
				case 0 :
					Coordonnee coordonnee = new Coordonnee(x, y); 
					combattant.setCoordonnee(coordonnee);
					placerPion(coordonnee, combattant.getNomPion());
					break;
				case 1 :
					x++;
					y++;
					Coordonnee coordonnee1 = new Coordonnee(x, y); 
					combattant.setCoordonnee(coordonnee1);
					placerPion(coordonnee1, combattant.getNomPion());
					break;
				case 2 :
					x++;
					y--;
					Coordonnee coordonnee2 = new Coordonnee(x, y); 
					combattant.setCoordonnee(coordonnee2);
					placerPion(coordonnee2, combattant.getNomPion());
					break;
				case 3 :
					x--;
					y++;
					Coordonnee coordonnee3 = new Coordonnee(x, y); 
					combattant.setCoordonnee(coordonnee3);
					placerPion(coordonnee3, combattant.getNomPion());
					break;
				case 4 :
					x--;
					y--;
					Coordonnee coordonnee4 = new Coordonnee(x, y); 
					combattant.setCoordonnee(coordonnee4);
					placerPion(coordonnee4, combattant.getNomPion());
					break;		
			} // switch
		} // for
	} // placerTroupe


	// méthode permettant de déplacer un combattant sur la map
	// vérifie si le déplacement est légal et que la case est inoccupée

	/*
	 * A TESTER
	 * 
	 * place les troupes en fonction du nombre de joueurs
	 * 8 cas possibles (2-->9 joueurs)
	 * oxooxooxo
	 * x1xx2xx3x   chacun des numéros représentent un point de départ (pDD) possible pour une équipe
	 * oxooxooxo   les o représentent ou peuvent se développer les autres membres de la troupe
	 * oxooxooxo
	 * x4xx5xx6x
	 * oxooxooxo
	 * oxooxooxo
	 * x7xx8xx9x 
	 * oxooxooxo
	 */
	public void placerTroupes(Troupe[] troupes){

		// voici les 9 points de départs possibles
		Coordonnee pDD1 = new Coordonnee('B', 2);
		Coordonnee pDD2 = new Coordonnee('B', longueur/2);
		Coordonnee pDD3 = new Coordonnee('B', longueur-2);
		char c = Utilitaires.convertiIntEnChar(largeur/2);
		Coordonnee pDD4 = new Coordonnee(c, 2);
		Coordonnee pDD5 = new Coordonnee(c, longueur/2);
		Coordonnee pDD6 = new Coordonnee(c, longueur-2);
		char d = Utilitaires.convertiIntEnChar(largeur-2);
		Coordonnee pDD7 = new Coordonnee(d, 2);
		Coordonnee pDD8 = new Coordonnee(d, longueur/2);
		Coordonnee pDD9 = new Coordonnee(d, longueur-2);

		switch(troupes.length){

		case 2 :
			placerUneTroupe(troupes[0], pDD2);
			placerUneTroupe(troupes[1], pDD8);
			break;
		case 3 :
			placerUneTroupe(troupes[0], pDD2);
			placerUneTroupe(troupes[1], pDD7);
			placerUneTroupe(troupes[2], pDD9);
			break;
		case 4 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD3);
			placerUneTroupe(troupes[2], pDD7);
			placerUneTroupe(troupes[3], pDD9);
			break;
		case 5 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD3);
			placerUneTroupe(troupes[2], pDD5);
			placerUneTroupe(troupes[3], pDD7);
			placerUneTroupe(troupes[4], pDD9);
			break;
		case 6 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD2);
			placerUneTroupe(troupes[2], pDD3);
			placerUneTroupe(troupes[3], pDD7);
			placerUneTroupe(troupes[4], pDD8);
			placerUneTroupe(troupes[5], pDD9);
			break;
		case 7 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD2);
			placerUneTroupe(troupes[2], pDD3);
			placerUneTroupe(troupes[3], pDD5);
			placerUneTroupe(troupes[4], pDD7);
			placerUneTroupe(troupes[5], pDD8);
			placerUneTroupe(troupes[6], pDD9);
			break;
		case 8 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD2);
			placerUneTroupe(troupes[2], pDD3);
			placerUneTroupe(troupes[3], pDD4);
			placerUneTroupe(troupes[4], pDD6);
			placerUneTroupe(troupes[5], pDD7);
			placerUneTroupe(troupes[6], pDD8);
			placerUneTroupe(troupes[7], pDD9);
			break;
		case 9 :
			placerUneTroupe(troupes[0], pDD1);
			placerUneTroupe(troupes[1], pDD2);
			placerUneTroupe(troupes[2], pDD3);
			placerUneTroupe(troupes[3], pDD4);
			placerUneTroupe(troupes[4], pDD5);
			placerUneTroupe(troupes[5], pDD6);
			placerUneTroupe(troupes[6], pDD7);
			placerUneTroupe(troupes[7], pDD8);
			placerUneTroupe(troupes[8], pDD9);
			break;
		default : break;
		}

	} // placerProtagonistes

	// tester les encerclements, les collisions, le terrain, etc...

	public void deplacement(Combattant combattant){

//		if (!combattant.isActif()){
//			System.out.println("Ce combattant n'est pas actif, il ne peut pas se déplacer");
//			return;
//		}

		Coordonnee depart = combattant.getCoordonnee();
		Coordonnee destination = null;
		int mouvement = combattant.getMouvement(); 
		int nbDeplacements = Integer.MAX_VALUE ; 
		int x = -1; 
		int y = -1;

		do{
			destination = Coordonnee.demanderCoordonnee(this);
			// cette ligne vérifie que la case de destination est légale
			nbDeplacements = Math.abs(depart.getColonne() - destination.getColonne()) + Math.abs(depart.getLigne() - destination.getLigne());

			x = Utilitaires.convertiCharEnInt(destination.getLigne());
			y = destination.getColonne();
			if(nbDeplacements > mouvement)
				System.out.println("Vous ne pouvez pas vous déplacer sur cette case car vous n'avez pas assez de mouvement");
			else if(!map[x][y].equals(CASE_VIDE))
				System.out.println("Vous ne pouvez pas vous déplacer sur cette case car elle est occupée");
			System.out.println("Voulez vous annuler le déplacement et arreter cette action? (o/n)");
			char reponse = Utilitaires.lireOouN();
			if (reponse == 'o' || reponse == 'O') return;
		} while (nbDeplacements > mouvement || !map[x][y].equals(CASE_VIDE));

		placerPion(depart, "____");
		placerPion(destination, combattant.getNomPion());
		combattant.setCoordonnee(destination);
		combattant.setMouvement(combattant.getMouvement() - nbDeplacements);
	} // deplacement

	// renvoie ce qu'il y a sur une case
	public Combattant selectionnerCoordonnee(Troupe[] troupes){

		Coordonnee coordonnee = Coordonnee.demanderCoordonnee(this);
		int x = Utilitaires.convertiCharEnInt(coordonnee.getLigne());
		int y = coordonnee.getColonne();
		if(map[x][y].equals(CASE_VIDE)) 
			return null;
		else {
			int numJoueur =  Integer.parseInt(map[x][y].charAt(3) + "");
			for (int i = 0; i < troupes[numJoueur-1].getNbCombattantVivant(); i++) {
				if(map[x][y].equals(troupes[numJoueur-1].getListeCombattant()[i].getNomPion()+"|")) return troupes[numJoueur-1].getListeCombattant()[i];   
			}
			System.out.println("Erreur : combattant introuvable dans les troupes");
			return null;
		}
	} // selectionnerCoordonnee

	public Combattant selectionnerUnVoisin(Combattant combattantSelectionne, Troupe[] troupes){
		boolean msgErr = false;
		Combattant voisin = null;
		do {
			if (msgErr) System.out.println("Il n'y pas de combattant à cette coordonnée/cette coordonnée n'est pas adjacente ");
			voisin = selectionnerCoordonnee(troupes);
			msgErr = true;
		} while(voisin == null || !Coordonnee.sontAdjacent(combattantSelectionne.getCoordonnee(), voisin.getCoordonnee()));

		return voisin;
	} // selectionnerUnVoisin

}// class Map
