package game;

import map.Map;
import units.Arme;
import units.Combattant;
import units.Troupe;
import util.Game;
import util.Menu;
import util.Utilitaires;

class Main {
	
	/**
	 * DISCUSSION
	 * 
	 * Pour le moment le Game n'offre pas un large choix de décisions stratégiques. De plus son ergonomie est assez déplaisante. 
	 * En fait, mon effort a été de créer le plus vite possible qqch de "jouable" sur lequel il sera plus ou moins facile d'ajouter 
	 * des fonctionnalités (voir IDEA TO IMPROVE THIS GAME), selon mon temps libre, et les gens qui auront décider de me donner 
	 * un coup de main, afin de rendre le jeu de + en + intéressant. 
	 * 
	 * J'ai bon espoir d'arriver à un jeu "chouette et amusant", l'objectif ultime étant d'arriver à avoir le fun d'un 
	 * Free For All, style Worms Armaggedon avec ce "Fire Emblem"-like. 
	 * 
	 * ----------------------------------------------------------------------------------------------------------------------------
	 * 
	 * KNOWN BUGS
	 * 
	 * 1) Les combattants adverses ne font pas mur, on peut passer au travers
	 * 2) Possibilié de sortir du shop sans avoir acheté d'arme
	 * 
	 * ----------------------------------------------------------------------------------------------------------------------------
	 * 
	 * IDEA TO IMPROVE THIS GAME
	 * 
	 * montrer les stats du guerrier des le recrutement
	 * Changer l'arme dans l'inventaire via le menu action
	 * selectionner combattant en entrant son nom pion
	 * Implémenter plus d'arme (arme létale (%crit)) 
	 * 
	 * Implémenter le système de "talents"
	 * Faire des armes avec différentes portées (arc)
	 * Faire un système de sauvegarde en SQL
	 * Implémenter la magie + soins
	 * Implémenter des dgt de zones
	 * Faire des coffres-fort, qui contiendraient différents type d'objet 
	 * 
	 * Creer une classe "Texte" qui "égaiera" notament les dialogues de la classe combats, générant de manière aléatoire du texte
	 * préécris. Ce sera l'occasion de faire de l'humour xD
	 * 
	 * ----------------------------------------------------------------------------------------------------------------------------
	 * 
	 * DEBUGGING
	 * 
	 * faire un gameDebug avec ses paramètres déjà initialisés (done)
	 * 
	 */
	
	
	public static void main(String[] args) {
	
		Game game = new Game();
 		game.affichageGame();
		
//		Troupe[] troupes = new Troupe[2];
//		for (int i = 0; i < 2; i++) {
//			System.out.println("Tour du joueur " + (i+1) + ".\n" );
//	
//			Troupe troupe = new Troupe(3);
//			troupes[i] = Menu.creerTroupe(game.listeCombattants, troupe, (i+1));
//			game.listeCombattants = Game.listeCombattants();  // refresh liste combattants
//		}
		
//		Game debugGame = debugGame(); 
//		debugGame.play();
		
	}
	
	private static Game debugGame(){
		
		// le code ci dessous sert au debugging --> il crée un jeu sans que l'utilisateur doivent initialiser ses paramètres
		// le jeu créé est une map 9x9 sur laquelle 2 joueurs ayant 2 combattants s'affrontent 
		// les combattant du joueur 1 sont mieux équipés que ceux du joueurs 2
		Game debugGame = new Game();
		// base init
		debugGame.setNbJoueurs(2);
		debugGame.setNbCombattant(2);
		debugGame.setMap(new Map(9,9));
		debugGame.setTroupes(new Troupe[2]);
		Combattant[] listeDesCombattants = Game.listeCombattants();
		Arme[] listeDesArmes = Game.listeArmes();
		Troupe troupe1 = new Troupe(2);
		Troupe troupe2 = new Troupe(2);
		debugGame.getTroupes()[0] = troupe1;
		debugGame.getTroupes()[1] = troupe2;
		Combattant c25 = listeDesCombattants[24]; // gaspard
		Combattant c24 = listeDesCombattants[23]; // jambon beurre
		Combattant c23 = listeDesCombattants[22]; // quatre fromage
		Combattant c22 = listeDesCombattants[21]; // wesh afou
		Arme eA = listeDesArmes[2]; // epee argent
		Arme hF = listeDesArmes[6]; // hache fer
		
		// init des combattants
		c25.mettreDansLInventaire(eA);
		c24.mettreDansLInventaire(eA);
		c25.mettreDansLInventaire(hF);
		c23.mettreDansLInventaire(hF);
		c22.mettreDansLInventaire(hF);
		c25.setNumJoueur(1);
		c24.setNumJoueur(1);
		c23.setNumJoueur(2);
		c22.setNumJoueur(2);
		c25.setNomPion(c25.getNomPion()+"1");
		c24.setNomPion(c24.getNomPion()+"1");
		c23.setNomPion(c23.getNomPion()+"2");
		c22.setNomPion(c22.getNomPion()+"2");
		troupe1.ajouterCombattant(c25);
		troupe1.ajouterCombattant(c24);
		troupe2.ajouterCombattant(c23);
		troupe2.ajouterCombattant(c22);
		
		debugGame.getMap().placerTroupes(debugGame.getTroupes());
		
		return debugGame();
		
	}
} 
