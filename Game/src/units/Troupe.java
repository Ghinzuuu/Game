package units;
/**
 * 
 * @author Lancelot de Wouters
 * 18/2/2016
 * 
 */

public class Troupe {
	
	/*
	 * TO THINK ABOUT
	 * 
	 * Peut-�tre est-ce une mauvaise id�e de nommer un attribut comme la classe ?
	 *  
	 * Cette classe est-elle n�cessaire ? 
	 * 
	 * Avoir un attribut "String nomDEquipe ? " --> l'impl�mentation dans le game fera savoir si c'est n�cessaire 
	 * 
	 */
	
	private Combattant[] listeCombattant; 
	private int nbCombattantVivant;
	
	public Troupe(int nbCombattant) {
		super();
		this.listeCombattant = new Combattant[nbCombattant];
		this.nbCombattantVivant = 0;
	}
	
	public Combattant[] getListeCombattant() {
		return listeCombattant;
	}
	
	public int getNbCombattantVivant() {
		return nbCombattantVivant;
	}
	
	public void setNbCombattant(int nbCombattant) {
		this.nbCombattantVivant = nbCombattant;
	}
	
	/*
	 * Cette m�thode rempli la troupe de combattant selon leur ordre alphab�tique
	 * /!\ ne v�rifie pas si la troupe est compl�te
	 */
	public void ajouterCombattant(Combattant combattant){
		if(combattant == null) throw new IllegalArgumentException("Guerrier invalide");
		
		for (int i = nbCombattantVivant-1; i >= 0; i--) {
			if(listeCombattant[i].getNom().compareTo(combattant.getNom()) > 0) 
				listeCombattant[i+1] = listeCombattant[i];
			else{
				listeCombattant[i+1] = combattant;
				nbCombattantVivant++;
				return;
			}
		}
		listeCombattant[0] = combattant;
		nbCombattantVivant++;
		return;
	
	} // ajouterCombattant
	/**
	 * retire de fa�on � garder l'ordre alphab�tique 
	 * /!\ ne v�rifie pas les doublons
	 * @param combattant
	 */
	public void retirerCombattant(Combattant combattant){
		if(combattant == null) throw new IllegalArgumentException("Guerrier invalide");
		int indice = 0;
		while(indice < nbCombattantVivant){
			if(combattant.getNom().equals(listeCombattant[indice].getNom())){
				for (int i = indice ; i < (nbCombattantVivant-1) ; i++) {
					listeCombattant[indice] = listeCombattant[indice+1];
				}
				listeCombattant[nbCombattantVivant-1] = null;
				nbCombattantVivant--;
				return;
			} else {
				indice++;
			}
		}
		System.out.println("erreur : le guerrier n'est pas dans la listeCombattant et ne peux �tre supprim�");
		
	} // retirerCombattant
	
	/*
	 * ces m�thodes (des)activent tous les combattants du joueur
	 */
	public void activationCombattants(){
		for (int i = 0; i < this.nbCombattantVivant; i++) {
			Combattant combattant = this.getListeCombattant()[i];
			combattant.setActif(true);
			combattant.setMouvement(3);
		}
	}
	public void desactivationCombattants(){
		for (int i = 0; i < this.nbCombattantVivant; i++) {
			this.getListeCombattant()[i].setActif(false);
		}
	}	
} // class
