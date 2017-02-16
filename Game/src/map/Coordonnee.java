package map;

import units.Combattant;
import units.Troupe;

/**
 * 
 * @author Lancelot de Wouters
 * 10/1/2016
 * 
 */

public class Coordonnee {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	private char ligne;
	private int colonne;

	public Coordonnee(char ligne, int colonne) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public char getLigne() {
		return ligne;
	}
	public void setLigne(char ligne) {
		this.ligne = ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colonne;
		result = prime * result + ligne;
		return result;
	}
	@Override
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnee other = (Coordonnee) obj;
		if (colonne != other.colonne)
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coordonnée ligne : " + ligne + ", colonne : " + colonne;
	}
	
	// permet à l'utilisateur d'entrée une coordonée de la map
	// quand j'ai écris cette méthode, seul dieu et moi savions ce que je faisais. maintenant seul dieu le sait --'
	// je suis persuadé qu'il est possible de simplifier cette méthode mais je n'ai aucune idée de comment :s
	public static Coordonnee demanderCoordonnee(Map map){
		
		char x = '@';
		int y = 0;
		String coordEntree = "";
		int nbEntree = 0;
		boolean longueurCoordonnee = true, charX = true, intY = true;
		
		while(longueurCoordonnee || charX || intY){	
			if(nbEntree > 0){ // message d'erreur
				System.out.println("Vous avez mal introduit votre coordonnée :");
				System.out.println("Une coordonnée est composée d'une lettre majuscules comprise entre A et Z ");
				System.out.println("et d'un nombre compris entre 1 et 50.");
				longueurCoordonnee = true; 
				charX = true; 
				intY = true;
			}
			System.out.println("Entrez les coordonnées : ");
			coordEntree = scanner.nextLine();
			nbEntree++;
			if(coordEntree.length() == 2 || coordEntree.length() == 3 ){
				longueurCoordonnee = false;
				char[] coordExploitable = coordEntree.toCharArray();
				x = coordExploitable[0];
				if (Character.isLowerCase(x)) x = Character.toUpperCase(x); 
				for (char i = 'A'; i <= 'A' + map.getLargeur() ; i++) {
					if(x == i) charX = false;
				}
				y = coordExploitable[1]-48; // pq ce -48??? essayer avec -'0'
				if(coordExploitable.length == 3){
					String nb = coordExploitable[1] +""+ coordExploitable[2];
					try {
						y = Integer.parseInt(nb); // converti String en int;
					
					} catch (NumberFormatException nfe){
						y = 0;
					}
				}
				if(y >= 1 && y <= map.getLongueur() && coordExploitable[1]-48 < 10) intY = false;
			}
		} //fin while
		
		return new Coordonnee(x, y);
		
	}
	
	// vérifie si deux case sont adjacentes. deux cases ne sont pas adjacentes si elles se touchent en diagonal
	public static boolean sontAdjacent(Coordonnee a, Coordonnee b) {
		if(a.getLigne() == b.getLigne()) return a.getColonne() == b.getColonne() + 1 || a.getColonne() == b.getColonne() - 1; 
		if(a.getColonne() == b.getColonne()) return a.getLigne() == b.getLigne() + 1 || a.getLigne() == b.getLigne() - 1; 
		return false;
	}
	
	/*
	 * A TESTER
	 * 
	 *  Place un combattant ou null (selon l'adjacence) dans la case correspondante au schéma suivant
	 *   
	 * 		0 
	 *	1	C	2
	 *		3 
	 * 
	 */
	public static Combattant[] champDAction(Combattant combattantSelectionne, Troupe[] troupes){
		Combattant[] combattantAdjacent = new Combattant[4];
		
		for (int i = 0; i < troupes.length; i++) {
			for (int j = 0; j < troupes[i].getNbCombattantVivant(); j++) {
				
				Coordonnee coordCombSel = combattantSelectionne.getCoordonnee();
				Combattant combVerif = troupes[i].getListeCombattant()[j];
				Coordonnee coordCombVerif = combVerif.getCoordonnee();
				
				if(coordCombSel.getColonne() == coordCombVerif.getColonne() && coordCombSel.getLigne() == coordCombVerif.getLigne() + 1) 
					combattantAdjacent[0] = combVerif;
				else if(coordCombSel.getColonne() == coordCombVerif.getColonne() && coordCombSel.getLigne() == coordCombVerif.getLigne() - 1) 
					combattantAdjacent[3] = combVerif;
				else if(coordCombSel.getColonne() == coordCombVerif.getColonne() - 1 && coordCombSel.getLigne() == coordCombVerif.getLigne()) 
					combattantAdjacent[1] = combVerif;
				else if(coordCombSel.getColonne() == coordCombVerif.getColonne() + 1 && coordCombSel.getLigne() == coordCombVerif.getLigne()) 
					combattantAdjacent[2] = combVerif;
			}
		}
		
		return combattantAdjacent;
	} // champDAction
	
	
} // Coordonnee
