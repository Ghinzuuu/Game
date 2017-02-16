package units;

import map.Coordonnee;
import util.Utilitaires;

/**
 * 
 * @author Lancelot de Wouters
 * 3/1/2016
 */
public class Combattant {
	
	/*
	 * TODO 
	 * 
	 * - toString() des familles
	 * - état (poison / auto heal / ...)
	 * 
	 */
	
	
	// le nom du combattant et le nom du pion (c-à-d le nom qu'il a sur la map)
	private String nom, nomPion;
	// ces attributs sont les statistiques du combattant. tous ces champs servent aux calculs de la classe combat
	private int pv, force, vitesse, precision, chance, vigueur, mouvement;
	// contient toutes les armes du combattant. je réfléchis à l'implémentation de potion (inexistant pour le moment - voir "class Arme")
	private Arme[] inventaire;
	// cet attribut est le champ 0 de l'inventaire. il sert aux calculs de la classe combat et est considéré comme étant "l'arme équipée"
	private Arme arme;
	// bien que déjà présent dans le code chez les combattants, j'implémenterai les talents quand j'estimerai avoir 
	// une version alpha "qui tient la route".
	// 0. heal 1. precision 2. bouclier 3. poison 4. percée 5. one-shot 
	private boolean[] talents; 
	// cet attribut sert à repérer le combattant sur la carte
	private Coordonnee coordonnee;
	// cet attribut détermine si le combattant sélectionner peut agir
	private boolean actif;
	// cet attribut permet de retrouver à qui appartient ce combattant. ainsi deux joueurs peuvent avoir le même combattant
	private int numJoueur;
	
	public Combattant(String nom, String nomPion, int pv, int force, int vitesse, int precision, int chance, int vigueur, int mouvement) {
		
		if(nom.equals("") || nom == null) throw new IllegalArgumentException("Nom invalide");
		if(nomPion.equals("") || nomPion == null || nomPion.length() > 3) throw new IllegalArgumentException("Nom du pion invalide");
		if(pv <= 0) throw new IllegalArgumentException("Points de vie invalide");
		if(force < 0) throw new IllegalArgumentException("Force invalide");
		if(vitesse < 0) throw new IllegalArgumentException("Vitesse invalide");
		if(precision < 0) throw new IllegalArgumentException("Précision invalide");
		if(chance < 0) throw new IllegalArgumentException("Chance invalide");
		if(vigueur < 0) throw new IllegalArgumentException("Vigueur invalide");
		if(mouvement < 0) throw new IllegalArgumentException("Mouvement invalide");
		
		this.nom = nom;
		this.nomPion = nomPion;
		this.pv = pv;
		this.force = force;
		this.vitesse = vitesse;
		this.precision = precision;
		this.chance = chance;
		this.vigueur = vigueur;
		this.mouvement = mouvement;
		this.inventaire = new Arme[5];
		this.arme = inventaire[0];
		this.talents = new boolean[5];
		this.coordonnee = null;
		this.actif = false;
		this.numJoueur = -1;
		
	} // constructeur

	public boolean[] getTalents() {
		return talents;
	}
	public boolean isActif() {
		return actif;
	}
	public String getNomPion() {
		return nomPion;
	}
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}
	public Arme getArme() {
		return arme;
	}
	public String getNom() {
		return nom;
	}
	public int getPv() {
		return pv;
	}
	public int getForce() {
		return force;
	}
	public int getVitesse() {
		return vitesse;
	}
	public int getPrecision() {
		return precision;
	}
	public int getChance() {
		return chance;
	}
	public int getVigueur() {
		return vigueur;
	}
	public int getMouvement() {
		return mouvement;
	}	
	public Arme[] getInventaire(){
		return inventaire;
	}
	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setNomPion(String nomPion) {
		this.nomPion = nomPion;
	}
	public void setPv(int pv) {
		if(pv<0) pv = 0;
		this.pv = pv;
	}
	public void setForce(int force) {
		this.force = force;
	}
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}
	public void setVigueur(int vigueur) {
		this.vigueur = vigueur;
	}
	public void setMouvement(int movement) {
		this.mouvement = movement;
	}
	public void setArme(Arme arme) {
		this.arme = arme;
	}
	public void setInventaire(Arme[] inventaire) {
		this.inventaire = inventaire;
	}
	public void setTalents(boolean[] talents) {
		this.talents = talents;
	}
	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

	// a modifié?
	public String toString(){
		return this.nom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + numJoueur;
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
		Combattant other = (Combattant) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numJoueur != other.numJoueur)
			return false;
		return true;
	}

	// rempli l'inventaire (/!\ non trié)
	public void mettreDansLInventaire(Arme arme){
		int emplacementDisponible = 0;
		while(inventaire[emplacementDisponible] != null && emplacementDisponible < inventaire.length) emplacementDisponible++;
		if(emplacementDisponible >= inventaire.length) System.out.println("Inventaire plein !");
		else inventaire[emplacementDisponible] = arme; 
		if (emplacementDisponible == 0) this.setArme(arme);
		
	}
	
	// permutation de deux equipements
	public void echange(Combattant voisin){
		if (this.getNumJoueur() != voisin.getNumJoueur()) {
			System.out.println("Ce combattant n'est pas votre allié");
			return;
		}
		boolean echangeTermine = false;
		while(!echangeTermine){
			System.out.println("Inventaire de " + this.getNom());
			Utilitaires.afficherTableAvecIndice(inventaire, inventaire.length);
			System.out.println("---------------------------------------------");
			System.out.println("Inventaire de " + voisin.getNom());
			Utilitaires.afficherTableAvecIndice(voisin.getInventaire(), voisin.getInventaire().length);
			
			System.out.println("\nSélectionner d'abord l'objet que vous voulez donner");
			int iADonner = -1;
			boolean msgErr = false;
			do {
				if(msgErr) System.out.println("Il n'y a pas d'arme à cette case");
				iADonner = Utilitaires.lireNombreComprisEntre(1, 5) - 1;
				msgErr = true;
			} while(this.inventaire[iADonner] == null);
			
			System.out.println("Sélectionner maintenant l'arme avec lequel vous voulez l'échanger");
			int iARecevoir = -1;
			msgErr = false;
			do{
				if(msgErr) System.out.println("Il n'y a pas d'arme à cette case");
				iARecevoir = Utilitaires.lireNombreComprisEntre(1, 5) - 1;
				msgErr = true;
			}while(voisin.inventaire[iARecevoir] == null);
			
			Arme armeADonner = this.inventaire[iADonner];
			Arme armeARecevoir = voisin.inventaire[iARecevoir];
			Arme armeADonnerCopie = new Arme(armeADonner.getNom(), armeADonner.getDegat(), armeADonner.getPrecision(), armeADonner.getCritique(), armeADonner.getType(), armeADonner.getPortee(), armeADonner.getPrix());
			Arme armeARecevoirCopie = new Arme(armeARecevoir.getNom(), armeARecevoir.getDegat(), armeARecevoir.getPrecision(), armeARecevoir.getCritique(), armeARecevoir.getType(), armeARecevoir.getPortee(), armeARecevoir.getPrix());
			this.inventaire[iADonner] = armeARecevoirCopie;
			voisin.inventaire[iARecevoir] = armeADonnerCopie;
			this.setArme(this.inventaire[0]);
			voisin.setArme(voisin.inventaire[0]);
			System.out.println("Voulez-vous continuer à échanger ? (o/n)");
			char reponse = Utilitaires.lireOouN();
			if (reponse == 'n' || reponse == 'N') echangeTermine = true; 
		}

	} // echange
			
} // class Combattant
