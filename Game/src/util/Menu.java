package util;

import map.Coordonnee;
import map.Map;
import units.Arme;
import units.Combattant;
import units.Troupe;

/**
 * 
 * @author Lancelot de Wouters
 * 10/1/2016
 * 
 */

 public final class Menu {
	
	/*
	 * TO THINK ABOUT
	 * 
	 * Utilité de la classe? L'intégrer dans le game (comme une sous classe)?
	 *  
	 * 
	 */
	
	
	
	/*
	 *  TODO
	 *  
	 */
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	private Menu(){}
	
	/*
	 *  Méthode qui permet de creer une troupe pour les joueurs au début du jeu
	 *  A TESTER !
	 */
	public static Troupe creerTroupe(Combattant[] listeCombattants, Troupe troupe, int numJoueur){
		int nbTourBoucle = 0;
		System.out.println("Voici les combattants : ");

		while(troupe.getListeCombattant().length > nbTourBoucle){
			
			Utilitaires.afficherTableAvecIndice(listeCombattants, listeCombattants.length-nbTourBoucle);
			System.out.println("Choisissez un combattant en tapant le numéro qui lui est associé : ");
			int choix = Utilitaires.lireNombreComprisEntre(1, listeCombattants.length-nbTourBoucle) -1;
			Combattant combattant = listeCombattants[choix];
			combattant.setNumJoueur(numJoueur);
			combattant.setNomPion("" + combattant.getNomPion() + numJoueur);
			troupe.ajouterCombattant(combattant);
			Utilitaires.suppression(listeCombattants, listeCombattants.length-nbTourBoucle, choix);
			nbTourBoucle++;
		}
		return troupe;
	}
	
	/*
	 *  méthode qui permet de remplir l'inventaire des combattants au debut du jeu)
	 *  
	 *  A TESTER !
	 *  
	 *  vérifier que tout le monde ait une arme à la fin des achats
	 */
	public static void shop(Arme[] listeArmes, Troupe troupe, int piecesDOr){
		Combattant[] tabCombattant = troupe.getListeCombattant();
		System.out.println("Bienvenue dans le magasin d'armes !"); 
		System.out.println("Faisons des affaires ! \n");
		Utilitaires.afficherTableAvecIndice(listeArmes, listeArmes.length);
		boolean resterDansLeMagasin = true;
		
		while(resterDansLeMagasin){
			System.out.println("\nVous avez " + piecesDOr + " PO.\n");
			
			System.out.println("Quel combattant voulez-vous équiper? (écrivez son numéro) \n");
			Utilitaires.afficherTableAvecIndice(tabCombattant, tabCombattant.length);
			int choixCombattant = (Utilitaires.lireNombreComprisEntre(1, tabCombattant.length) - 1);
			Combattant combattant = tabCombattant[choixCombattant];
			
			if(combattant.getInventaire()[combattant.getInventaire().length-1] != null) 
				System.out.println("L'inventaire de ce guerrier est plein");
			else {
				System.out.println("Quelle arme voulez-vous acheter? (écrivez le numéro de l'arme)");
				int choixArme = (Utilitaires.lireNombreComprisEntre(1, listeArmes.length) - 1);
				Arme armeVendue = listeArmes[choixArme];
				
				if (armeVendue.getPrix() > piecesDOr) 
					System.out.println("Vous n'avez pas assez d'or pour acheter cette arme");
				else{
					piecesDOr -= armeVendue.getPrix();
					combattant.mettreDansLInventaire(armeVendue);
				}
			}
			System.out.println("Voulez-vous sortir du magasin ? (Répondez O ou N) \n (pas de retour possible dans le magasin)");
			char reponse = Utilitaires.lireOouN();
			if (reponse == 'O' || reponse == 'o') 
				resterDansLeMagasin = false;
			
		}
	} // shop
	
	/*
	 * cette méthode sert à donner le détail et les probabilités d'un combat
	 */
	public static void menuAttaque(Combattant attaquant, Combattant defenseur) {
		String[][] menuAttaque = new String[7][2];
		// 0 --> nom / 1 --> pv / 2 --> degat / 3 --> precision / 4 --> vitesse / 5 --> critique
		// 	6 --> arme	
		
		Combattant[] combattants = {attaquant, defenseur};
		for (int i = 0; i < menuAttaque.length; i++) {
			for (int j = 0; j < 2; j++) {
				int k = (j+1)%2; // k représente l'"autre" combattant --> le différent de j
				if(i == 0) {
					menuAttaque[i][j] = combattants[j].getNom();
					int nbDeBlanc = 17 - combattants[j].getNom().length();
					for (int l = 0; l < nbDeBlanc ; l++) {
						menuAttaque[i][j] += " ";
					}
				} 
				else if(i == 1) {
					int pv = combattants[j].getPv();
					menuAttaque[i][j] = "Point de vie : " + pv;
					if (pv < 10)
						menuAttaque[i][j] += " ";
				}
				else if(i == 2) {
					int degats = Combat.degats(combattants[j], combattants[k]);
					menuAttaque[i][j] = "Dégats : " + degats + "      ";
					if (degats < 10) 
						menuAttaque[i][j] += " ";
				}
				else if(i == 3) {
					int touche = Combat.touche(combattants[j], combattants[k]);
					menuAttaque[i][j] = "Précision : " + touche + "   ";	
					if (touche < 10) 
						menuAttaque[i][j] += " ";
				}
				else if(i == 4){
					menuAttaque[i][j] = "Nb coups : x1    ";
					if(attaquant.getVitesse() - 4 >= defenseur.getVitesse()) menuAttaque[4][0] = "Nb coups : x2    ";
					else if(defenseur.getVitesse() - 4 >= attaquant.getVitesse()) menuAttaque[4][1] = "Nb coups : x2    ";
				} 
				else if(i == 5) {
					int critique = Combat.tauxCritique(combattants[j], combattants[k]);
					menuAttaque[i][j] = "Critique : " + critique + "    ";
					if (critique < 10) 
						menuAttaque[i][j] += " ";
				}
				else if(i == 6){
					menuAttaque[i][j] = combattants[j].getArme().getNom();
					int nbDeBlanc = 17 - combattants[j].getArme().getNom().length();
					for (int m = 0; m < nbDeBlanc; m++) {
						menuAttaque[i][j] += " ";
					}
				}
			} //for
		}//for
		
		Utilitaires.affichageTab2D(menuAttaque);
	}
	
	/*
	 * cette méthode permet de faire un tableau affichant les statistiques d'un combattant
	 */
	public static void stats(Combattant combattant){
		String stats = combattant.getNom() +
				"\n  Point de vie : " + combattant.getPv() +
				"\n  Force : " + combattant.getForce() +
				"\n  Vitesse : " + combattant.getVitesse() +
				"\n  Precision : " + combattant.getPrecision() +
				"\n  Chance : " + combattant.getChance() +
				"\n  Vigueur : " + combattant.getVigueur() +
				"\n  Mouvement : " + combattant.getMouvement() ;
		System.out.println(stats);
	}
	
	public static void inventaire(Combattant combattant){
		System.out.println("Inventaire de " + combattant.getNom() + "\n");
		Utilitaires.afficherTable(combattant.getInventaire());
	}
	
	private static void parametrageCustom(boolean[] custom, Combattant combattantSelectionne, Troupe[] troupes, int numJoueurActuel){
		custom[0] = true;
		Combattant[] champsDActions = Coordonnee.champDAction(combattantSelectionne, troupes);
		for (int i = 0; i < champsDActions.length; i++) {
			Combattant combattantPotentiel = champsDActions[i];
			if(combattantPotentiel != null){
				if(combattantPotentiel.getNumJoueur() != numJoueurActuel && combattantSelectionne.isActif()) custom[1] = true;
				else if (combattantPotentiel.getNumJoueur() == numJoueurActuel) custom[2] = true;
			}
		}
	} // parametrage du menu custom 
	
	
	private static void actionCombat(Map map, Combattant combattantSelectionne, Troupe[] troupes){
		Combattant voisin = map.selectionnerUnVoisin(combattantSelectionne, troupes);
		if (combattantSelectionne.getNumJoueur() != voisin.getNumJoueur()){
			menuAttaque(combattantSelectionne, voisin);
			System.out.println("Valider l'attaque ? (o/n)");
			char reponse = Utilitaires.lireOouN();
			if(reponse == 'o' || reponse == 'O'){
				Combattant combattantMort = Combat.combat(combattantSelectionne, voisin);
				if(combattantMort != null){
					Troupe troupePerdante = troupes[combattantMort.getNumJoueur()-1];
					map.placerPion(combattantMort.getCoordonnee(), "____");
					combattantMort.setCoordonnee(null);
					troupePerdante.retirerCombattant(combattantMort);
				}
				combattantSelectionne.setActif(false);
				return;
			}
		}
		System.out.println("Vous ne pouvez plus/pas attaquer");
	}
	
	private static void menuDActionsCustom(Combattant combattantSelectionne, boolean[] custom, Troupe[] troupes, Map map){
		boolean rester = true;
		
		while(rester){	
			System.out.println("0 --> sortir de ce menu");
			System.out.println("1 --> consulter statistiques du combattant");
			System.out.println("2 --> consulter inventaire du combattant");
			if (custom[0]) System.out.println("3 --> se déplacer sur la map (" + combattantSelectionne.getMouvement() + " mouvement(s) disponible)");
			if (custom[1]) System.out.println("4 --> attaquer un adversaire");
			if (custom[2]) System.out.println("5 --> échanger de l'équipement avec un voisin allié");
			int choixMenu = Utilitaires.entrerNombre();
			switch(choixMenu){
				case 0 : 
					rester = false;
					break;
				case 1 :
					stats(combattantSelectionne);
					break;
				case 2 :
					inventaire(combattantSelectionne);
					break;
				case 3 : // deplacement
					if (custom[0]){
						map.deplacement(combattantSelectionne);
						parametrageCustom(custom, combattantSelectionne, troupes, combattantSelectionne.getNumJoueur());
					}
					break;
				case 4 : // attaque
					if (custom[1]){
						actionCombat(map, combattantSelectionne, troupes);
						return;
					}
					break;
				case 5 : // echange
					if (custom[2]){
						Combattant voisin = map.selectionnerUnVoisin(combattantSelectionne, troupes);
						combattantSelectionne.echange(voisin);
					}
					break;
				default : break;
			} // switch
		} // while

	} // menuDActionsCustom
		
	/*
	 * Menu d'action : 
	 * 3 cas : 1) case vide
	 * 		   2) combattant adverse --> menuConsultation
	 * 		   3) combattant personnel --> si rien autour de lui
	 * 
	 */
	public static void menuDActions(Combattant combattantSelectionne, int numJoueurActuel, Troupe[] troupes, Map map){
		boolean[] custom = new boolean[3];
		if (combattantSelectionne == null) {
			System.out.println("Il n'y a rien à cette case");
		} else if (combattantSelectionne.getNumJoueur() != numJoueurActuel){
			System.out.println(combattantSelectionne.getNom() + " est un combattant du joueur " + combattantSelectionne.getNumJoueur());
			menuDActionsCustom(combattantSelectionne, custom, null, null);
		} else {
			parametrageCustom(custom, combattantSelectionne, troupes, numJoueurActuel);
			menuDActionsCustom(combattantSelectionne, custom, troupes, map);
		}
	} // menuDActions
		
} // class Menu
