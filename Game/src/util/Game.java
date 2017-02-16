package util;

import map.Coordonnee;
import map.Map;
import units.Arme;
import units.Combattant;
import units.Troupe;

/**
 * 
 * @author Lancelot de Wouters
 * 3/1/2016
 * 
 */
 public class Game {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	/*
	 * 	TODO
	 * 
	 *  - Faire les listes des Combattants et des armes (regarder stats et prix)
	 *  - Faire un mode par défaut (crées des troupes équipées)
	 *  - Bosser les menus du game
	 *  - Faire une méthode mort(Combattant combattant) dans quelle classe?
	 *  - Implémentations des talents heal (0) et poison (3)
	 *  - Gérer le calibrage du prix des armes
	 *  - Comment simplifier le code du main? Quelle découpe en méthode faire?
	 *  
	 *  ---------------------------------------------------------------------------------------------------------------------------------
	 *  
	 *  DEROULEMENT
	 *  
	 *  - Demander nb de joueurs
	 *  - Demander nb de guerriers par troupe
	 *  - Création des troupes (par ordre de joueur)
	 *  - Equipements des troupes (par ordre de joueur)
	 *  - Placer les pions sur la map
	 *  - Tours de jeu (LE GAME!!!!)
	 *  - Fin de partie (écran de victoire + statistiques (combattant ayant le plus combattu/ effectué le plus de dégat / le plus tuer)
	 * 
	 */
	
	public Combattant[] listeCombattants;
	private Arme[] listeArmes;
	private int nbJoueurs;
	private int nbCombattant;
	private Troupe[] troupes;
	private Map map;
	
	
	public Game(){
		this.listeCombattants = listeCombattants();
		this.listeArmes = listeArmes();
		
	}

	/**
	 * Getters, seems pretty useless
	 * 
	 */
	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public int getNbCombattant() {
		return nbCombattant;
	}

	public Troupe[] getTroupes() {
		return troupes;
	}
	
	public Map getMap() {
		return map;
	}

	/**
	 * Setters, utile pour initialiser les attributs du game
	 * 
	 */
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public void setNbCombattant(int nbCombattant) {
		this.nbCombattant = nbCombattant;
	}

	public void setTroupes(Troupe[] troupes) {
		this.troupes = troupes;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}

	
	/**
	 * "Database" du jeu : contient de manière hardcodée des combattants et des armes avec leur statistiques 
	 * 
	 */	
	public static Combattant[] listeCombattants(){
		Combattant[] listeCombattants = new Combattant[25];
//      listeCombattants[0] = new Combattant(nom, nomPion, pv, force, vitesse, precision, chance, vigueur, mouvement);
		
		// il aura toujours un oeil sur toi
		listeCombattants[0] = new Combattant("Greg Sauron", "srn", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// je confirme, c'est une vie de merde
		listeCombattants[1] = new Combattant("Jose VDM", "vdm", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3);
		// ce qu'on se dit après une session d'examen #MarvelleVSDece
		listeCombattants[2] = new Combattant("Faire Mieux", "frn", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// que des bonnes nouvelles #phpDansNosCoeurs
		listeCombattants[3] = new Combattant("Coolinet", "zen", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		 // qui gagne toujours tout? #MarvelleVSDece
		listeCombattants[4] = new Combattant("Gros Lolo", "gro", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3);
		// elle s'occupe si bien de nous :°)
		listeCombattants[5] = new Combattant("Lehmaman", "lhm", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// ok j'avais pas d'imagination pour celui la
		listeCombattants[6] = new Combattant("Dr. Dramas", "dms", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// je dirais même plus...
		listeCombattants[7] = new Combattant("Dupont et Dupond", "dpt", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		//
		listeCombattants[8] = new Combattant("Monsieur Tada/taupe/riz", "kem", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// on sait tous que tu as tous leurs albums
		listeCombattants[9] = new Combattant("Le tout premier fan de Tokio Hotel", "ant", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// tu nous manques à tous
		listeCombattants[10] = new Combattant("Sebastien", "rld", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// les personages de jeux videos n'ont pas d'âme non plus
		listeCombattants[11] = new Combattant("Jean-Philippe, la bagareuse", "pls", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		//
		listeCombattants[12] = new Combattant("K3v1n \"C'est clair\"", "kev", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3);
		// je ne voulais pas t'appeler Hitler
		listeCombattants[13] = new Combattant("Starfullah", "sam", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// toi non plus
		listeCombattants[14] = new Combattant("Nippon Ni Soumise", "key", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// #futurFlic
		listeCombattants[15] = new Combattant("L'Alcoolique", "chr", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// il y en a un dans mon jeu aussi
		listeCombattants[16] = new Combattant("Pierre-Paul-Jaques", "ppj", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// il parait que le frère Maniet 1 est plus grand que le Frère Maniet 2
		listeCombattants[17] = new Combattant("Frère Maniet 1", "fm1", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// ça aurait été peut-être plus cool d'utilisé vos initiales pour vous différencier
		listeCombattants[18] = new Combattant("Frère Maniet 2", "fm2", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20),  3); 
		// hahaha, c'était si facile de rire de ton nom :)
		listeCombattants[19] = new Combattant("Kosmonaupopoulos", "grg", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// avec ton nom tu gagnes d'office au scrabble
		listeCombattants[20] = new Combattant("Glandus \"Vrrydt\" Maximus", "alx", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// mon premier contact avec la jeunesse d'aujourd'hui
		listeCombattants[21] = new Combattant("Wesh Afou", "wsh", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// la meilleur sauce pour les pâtes à midi
		listeCombattants[22] = new Combattant("Quatre Fromages", "4xf", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		// c'est toujours bon d'avoir un bon jambon beurre avec soit, on sait jamais ce qu'il peut se passer
		listeCombattants[23] = new Combattant("Jambon-beurre", "jbb", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		//
		listeCombattants[24] = new Combattant("Gaspard, le vieux rageux", "lnc", Utilitaires.unEntierAuHasardEntre(15, 35), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(5, 20), Utilitaires.unEntierAuHasardEntre(0, 30), Utilitaires.unEntierAuHasardEntre(5, 20), 3); 
		
		return listeCombattants;
	}
	
	public static Arme[] listeArmes(){
		Arme[] listeArmes = new Arme[9];
		//listeArmes[0] = new Arme(nom, degat, precision, critique, type, portee, prix);
		listeArmes[0] = new Arme("Epée fer", 5, 90, 0, Arme.EPEE, 1, 500);
		listeArmes[1] = new Arme("Epée acier", 8, 75, 0, Arme.EPEE, 1, 600);
		listeArmes[2] = new Arme("Epée argent", 13, 80, 0, Arme.EPEE, 1, 1500);
		listeArmes[3] = new Arme("Lance fer", 7, 80, 0, Arme.LANCE, 1, 400);
		listeArmes[4] = new Arme("Lance acier", 10, 70, 0, Arme.LANCE, 1, 500);
		listeArmes[5] = new Arme("Lance argent", 14, 75, 0, Arme.LANCE, 1, 1200);
		listeArmes[6] = new Arme("Hache fer", 8, 75, 0, Arme.HACHE, 1, 300);
		listeArmes[7] = new Arme("Hache acier", 11, 65, 0, Arme.HACHE, 1, 400);
		listeArmes[8] = new Arme("Hache argent", 15, 70, 0, Arme.HACHE, 1, 1000);
		
		return listeArmes;
	}
	
	/**
	 * Cette méthode s'occupe d'initialiser avec le joueur les différents paramètres du jeu
	 */
	public void initGame(){

		System.out.println("***********************************************");
		System.out.println("******************  - RPG - *******************");
		System.out.println("*** Une lutte à mort sur un champs de pixel ***");
		System.out.println("***********************************************");

		System.out.println("\nSalutations chers joueurs ! Combien serez-vous à vous entretuez aujourd'hui? (2 min/ 9 max)");
		setNbJoueurs(Utilitaires.lireNombreComprisEntre(2, 9));
		
		System.out.println("Combien de combattants voulez-vous avoir sous vos ordres? (2 min/ 5 max)");
		setNbCombattant(Utilitaires.lireNombreComprisEntre(2, 5));
		
		System.out.println("Taille de la carte (nous vous conseillons d'entrer les dimensions conseillées par le manuel, en fonction du nombre de joueur");
		System.out.println("Quelle largeur doit avoir la carte ? (9 min/ 25 max)");
		int largeurMap = Utilitaires.lireNombreComprisEntre(9, 25);
		System.out.println("Quelle longueur doit avoir la carte ? (9 min/ 50 max)");
		int longueurMap = Utilitaires.lireNombreComprisEntre(9, 50);
		// création de la map et placement des combattants
		setMap(new Map(largeurMap,longueurMap));
		
		// pour retrouver un combattant il faut d'abord connaitre a quel joueur il appartient 
		// pour ensuite le retrouver dans sa troupe (comme un tab à deux dimensions)
		setTroupes(new Troupe[nbJoueurs]);

	} // initGame

	/**
	 * Cette méthode permet de recruter et equiper tous les combattants
	 */
	public void initTroupes(){

		// TODO : donner le choix au joueur de faire une autocomposition (= recrutement et equipement automatique)
		
		System.out.println("\n***************************************");
		System.out.println("******** - FORMATION TROUPES - ********");
		System.out.println("***************************************");
		System.out.println("\nChacun à son tour, vous allez composer votre troupe et l'équiper.\nPour plus de détails ou de conseils, consultez le manuel (RTFMN)");
		System.out.println("_____________________________________________________________________\n");
		System.out.println("Voulez-vous équiper vos combattants de manière aléatoire? (o/n)");
		char reponse = Utilitaires.lireOouN();	
	
		for (int i = 0; i < nbJoueurs; i++) {
			System.out.println("_____________________________\n");
			System.out.println("Tour du joueur " + (i+1) + ".\n");
	
			Troupe troupe = new Troupe(nbCombattant);
			troupes[i] = Menu.creerTroupe(listeCombattants, troupe, (i+1));
			listeCombattants = listeCombattants();  // refresh liste combattants
		
			if(reponse == 'o' || reponse == 'O' ){
				for (int j = 0; j < troupe.getNbCombattantVivant() ; j++) {
					Combattant combattantActuel = troupe.getListeCombattant()[j];
					combattantActuel.mettreDansLInventaire(listeArmes[Utilitaires.unEntierAuHasardEntre(0, (listeArmes.length-1))]);
					combattantActuel.mettreDansLInventaire(listeArmes[Utilitaires.unEntierAuHasardEntre(0, (listeArmes.length-1))]);
				}
			}
			else {
				// /!\ calibrer le budget et les prix des armes pour le shop
				Menu.shop(listeArmes, troupe, (troupe.getNbCombattantVivant() * 1000));
			}
				
		}
		
		// placement des combattants
		map.placerTroupes(troupes);
		
	} // initTroupes
	
	public void play(){

		System.out.println("\n********************************");
		System.out.println("******** - GUERRE !!! - ********");
		System.out.println("********************************");
		
		// variable donnant le nombre de joueurs en lice, permettant de tester les condition de victoire
		int nbJoueursEnLice = nbJoueurs;
		// si la case est à true, c'est que le joueur correspondant à cette case n'a plus de combattants et ne peut plus jouer
		boolean[] joueursMorts = new boolean[nbJoueurs];
		// variable donnant le tour courant
		int tour = 0;
		// variable donnant le joueurActuel java (càd joueur 0 dans les tableaux est le joueur 1 en vrai)
		int joueurActuel = 0; 
		
		// Si on sort de ce while c'est que le jeu est terminé
		while(nbJoueursEnLice != 1){
			// fais le tour des joueurs
			joueurActuel = (joueurActuel % nbJoueurs);
			if (joueurActuel == 0) tour++;
			if (joueursMorts[joueurActuel]) System.out.println("\nLe joueur " + (joueurActuel+1) + " n'a plus de combattant. \n");
			else {
				System.out.println("_____________________________\n");
				System.out.println("\nTour " + tour + " du joueur " + (joueurActuel+1) + "\n");
				Troupe troupeActuelle = troupes[joueurActuel];
				troupeActuelle.activationCombattants();
				boolean finTour = false;
				while(!finTour){
					map.affichage();
					System.out.println("Choisissez votre action :");
					System.out.println("1 --> sélectionner un combattant");
					System.out.println("2 --> mettre fin à votre tour");
					int choixJoueur = Utilitaires.lireNombreComprisEntre(1, 2);
					switch(choixJoueur){
						case 1 :
							Menu.menuDActions(map.selectionnerCoordonnee(troupes), (joueurActuel+1), troupes, map);
							break;
						case 2 :
							troupeActuelle.desactivationCombattants();
							finTour = true;
							break;
						default : break;
					} // switch
				} // while tour
				
				for (int i = 0; i < troupes.length; i++) {
					if(!joueursMorts[i] && troupes[i].getNbCombattantVivant() == 0){
						joueursMorts[i] = true;
						nbJoueursEnLice--;
					}
				}
			}
			joueurActuel++;
			
		} // while guerre
		for (int i = 0; i < joueursMorts.length; i++) {
			if(!joueursMorts[i]) 
				System.out.println("Entouré des cadavres de leurs adversaires, les combattants survivants du joueur " + (i+1)
						+ " savourent la victoire. Aujourd'hui, les dieux ont choisi leur camps.");
		}
	} // play
	
	/**
	 *  Méthode centrale du jeu. Cette méthode appelera tout ce qui est implémenté :D
	 */
	public void affichageGame(){		
		initGame();
		initTroupes();
		play();
	} // affichageGame
		
	
} // class Game
