package util;

import map.Map;
import units.Arme;
import units.Combattant;

/**
 * 
 * @author Lancelot de Wouters
 * 3/1/2016
 */
public final class Combat {

	/*
	 * TO THINK ABOUT
	 * 
	 * Je m'interroge sur l'utilit� de cette classe. 
	 * Ne vaudrait-il pas mieux tout int�grer dans la classe "Game", quitte � en faire une sous classe? 
	 * 
	 * Je m'int�rroge aussi sur le cot� "static" de toutes les m�thode. Vu que casi toutes les m�thodes appelle
	 * un Combattant attaquant, je pourrai int�grer cette classe dans la classe combattant et faire de l'attaquant l'objet courant (this)
	 * ce qui permettrait de gagner un param�tre et de retirer de toutes ces m�thodes leur cot� static.
	 * 
	 * Je m'interroge �galement sur leur cot� "public" . Je pense que toutes ces m�thodes pourrait �tre private 
	 * (� part combat(), la m�thode "m�re", qui appelle les autres (voir organigramme) ) 
	 * vu qu'il s'agit d'un "escalier de m�thodes" (une m�thode qui en appelle une autre, qui en appelle une autre.. ) .
	 * Les tests me le feront savoir.
	 * 
	 * Je laisse tout tel quel pour le moment car je trouve cette mani�re de proc�der claire.
	 * 
	 */
	
	/*
	 * Organigramme 
	 * 
	 * combat() --> attaque() --> touche() --> precision()
	 *                                     --> esquive()
	 *                                     --> armeAvantage() & armeDesavantage  
	 *           (si touch�!) --> infligerDegats() --> degats()
	 *                                             --> tauxCritique() 
	 * 
	 */

	/*
	 * TODO
	 * 
	 * Impl�mentations dans les calculs des talents : pr�cision (1) , bouclier (2) , perc�e (4) , one-shot (5)
	 * 
	 */
	
	/*
	 * A TESTER
	 * Calcul la pr�cision d'un combattant en fonction de son arme et de ses statistiques
	 * Rentre en compte pour calculer la chance de touche
	 * 
	 */
	
	private Combat(){}
	
	public static int precision(Combattant attaquant){
		return attaquant.getArme().getPrecision() + (attaquant.getPrecision()*2) + (attaquant.getChance()/2);
	}
	
	/*
	 * A TESTER
	 * Calcul l'esquive d'un combattant en fonction de ses stats
	 * Rentre en compte pour calculer la chance de touche
	 * 
	 */
	public static int esquive(Combattant defenseur){
		return (defenseur.getVitesse()*2) + defenseur.getChance();
	}
	
	/*
	 * A TESTER
	 * Les 2 m�thodes suivantes v�rifie si un des combattants � l'avantage selon l'arme qu'il poss�de
	 * Rentre en compte pour calculer la chance de touche et les d�gats inflig�s
	 * 
	 */
	public static boolean armeAvantage(Combattant attaquant, Combattant defenseur){
		return (attaquant.getArme().getType() == Arme.EPEE && defenseur.getArme().getType() == Arme.HACHE) || 
			   (attaquant.getArme().getType() == Arme.LANCE && defenseur.getArme().getType() == Arme.EPEE) ||
			   (attaquant.getArme().getType() == Arme.HACHE && defenseur.getArme().getType() == Arme.LANCE);
	}
	
	public static boolean armeDesavantage(Combattant attaquant, Combattant defenseur){
		return (attaquant.getArme().getType() == Arme.EPEE && defenseur.getArme().getType() == Arme.LANCE) || 
			   (attaquant.getArme().getType() == Arme.LANCE && defenseur.getArme().getType() == Arme.HACHE) ||
			   (attaquant.getArme().getType() == Arme.HACHE && defenseur.getArme().getType() == Arme.EPEE);
	}
	
	/*
	 * A TESTER
	 * Calcul la chance qu'un combattant a de toucher un autre
	 * /!\ v�rifier le calibrage de l'arme avantage
	 * 
	 */
	public static int touche(Combattant attaquant, Combattant defenseur){
		int precision = precision(attaquant);
		if(armeAvantage(attaquant, defenseur)) precision += 15;
		if(armeDesavantage(attaquant, defenseur)) precision -= 15;
		
		int esquive = esquive(defenseur);
		
		if (precision - esquive > 100) return 100;
		if (precision - esquive < 0 ) return 0;
		
		return precision - esquive; 
	}
	
	/*
	 * A TESTER
	 * Calcul la chance qu'a un combattant d'infliger un coup critique (3x les d�gats)
	 * Rentre en compte pour calculer les d�gats inflig�s
	 * 
	 */
	public static int tauxCritique(Combattant attaquant, Combattant defenseur){
		int critique = attaquant.getArme().getCritique() + (attaquant.getPrecision()/2) ;
		int esquive = defenseur.getChance();
		int tauxCritique = critique - esquive;
		if(tauxCritique < 0) tauxCritique = 0;
		return tauxCritique; 
	}
	
	/*
	 * A TESTER
	 * Calcul les d�gats qu'inflige une touche selon force, vigueur et arme des combattants
	 * Ne prends pas en compte les critiques
	 * 
	 */
	public static int degats(Combattant attaquant, Combattant defenseur){
		int degats = attaquant.getArme().getDegat() + attaquant.getForce() - defenseur.getVigueur();	
		if(armeAvantage(attaquant, defenseur)) degats += 3;		
		if(armeDesavantage(attaquant, defenseur)) degats -= 3;
		if(degats < 0) degats = 0;
		
		return degats;	
	}
	
	/*
	 * A TESTER
	 * Inflige les d�gats subis selon les m�thodes pr�c�dentes
	 * 
	 */
	public static void infligerDegats(Combattant attaquant, Combattant defenseur){
		int degats = degats(attaquant, defenseur);
		
		if (Utilitaires.unEntierAuHasardEntre(1, 100) <= tauxCritique(attaquant, defenseur)){
			System.out.println(attaquant.getNom() + " trouve la faille et frappe ! COUP CRITIQUE !!!");
			degats *= 3;
		}
		System.out.println(attaquant.getNom() + " inflige " + degats + " d�gats.");
		defenseur.setPv(defenseur.getPv()-degats);	
	}
	
	/*
	 * A TESTER
	 * Cette m�thode v�rifie si un combattant touche() un autre. 
	 * Appelle la m�thode "infligerDegat()" le cas �ch�ant
	 * 
	 */
	public static void attaque(Combattant attaquant, Combattant defenseur){
		System.out.println(attaquant.getNom() + " met en garde " + defenseur.getNom());
		if( Utilitaires.unEntierAuHasardEntre(1, 100) <= touche(attaquant, defenseur) ){
			System.out.println(attaquant.getNom() + " trouve une ouverture et touche !");
			infligerDegats(attaquant, defenseur);
		} else {
			System.out.println(defenseur.getNom() + " pare le coup avec maitrise !");
		}
	}
	
	/*
	 * A TESTER
	 * Cette m�thode g�re un tour de combat. 
	 * Elle appelle la m�thode attaque 2 � 3 fois (selon la vitesse des combattant)
	 * Si un combattant meurs, elle le renvoie, sinon elle renvoie null
	 */
	public static Combattant combat(Combattant attaquant, Combattant defenseur){
		
		if (attaquant.getNumJoueur() == defenseur.getNumJoueur()){
			System.out.println("Ce combattant est votre alli�, vous ne pouvez pas l'attaquer");
			return null;
		}
		
		attaque(attaquant, defenseur);
		if(mourir(defenseur)) return defenseur;
		
		System.out.println(defenseur.getNom() + " contre attaque !");
		attaque(defenseur, attaquant);
		if(mourir(attaquant)) return attaquant;
		if(attaquant.getPv() > 0 && defenseur.getPv() > 0){ // s'ils sont tous les deux en vie  le plus rapide frappe encore une fois
			if(attaquant.getVitesse() - 4 >= defenseur.getVitesse()){
				System.out.println(attaquant.getNom() + " prends de vitesse " + defenseur.getNom() + " !");
				attaque(attaquant, defenseur);
				if(mourir(defenseur)) return defenseur;
			} 
			else if(defenseur.getVitesse() - 4 >= attaquant.getVitesse()){
				System.out.println(defenseur.getNom() + " prends de vitesse " + attaquant.getNom() + " !");
				attaque(defenseur, attaquant);
				if(mourir(attaquant)) return attaquant;
			} 
		}		
		return null;
	}
	
	private static boolean mourir(Combattant combattant){
		if (combattant.getPv() <= 0) {
			System.out.println(combattant.getNom() + " tombe par terre, vaincu. RIP");
			return true;
		}
		return false;
	}
}// class Combat
