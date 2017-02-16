package units;

/**
 * 
 * @author Lancelot de Wouters
 * 3/1/2016
 */
public class Arme {
	
	
	/*
	 * TO THINK ABOUT
	 * - faire des batons de soins
	 * - faire des arcs
	 * - faire des potions
	 * - faire de la magie (Fire Emblem style)
	 * - faire des objets donnant des talents (poison, bouclier, heal, ... ) ou protégeant des talents (critique, percée...)
	 * 
	 */
	
	private String nom;
	private int degat; 
	private int precision;
	private int critique;
	// private static enum  types {EPEE, LANCE, HACHE};
	private int type;
	private int portee;
	private int prix;
	public static final int EPEE = 1;
	public static final int LANCE = 2;
	public static final int HACHE = 3;
	
	
		

	public Arme(String nom, int degat, int precision, int critique, int type, int portee, int prix) {
		super();
		if(type < EPEE && type > HACHE) throw new IllegalArgumentException("Type incorrect");
		if(degat < 0 || precision < 0 || critique < 0 || portee < 0 || prix < 0) throw new IllegalArgumentException("degat, precision, critique, portee ou prix incorrect");
		if(nom.equals("")) throw new IllegalArgumentException("nom incorrect");
		this.nom = nom;
		this.degat = degat;
		this.precision = precision;
		this.critique = critique;
		this.type = type;
		this.portee = portee;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}
	public int getDegat() {
		return degat;
	}
	public int getPrecision() {
		return precision;
	}
	public int getCritique() {
		return critique;
	}
	public int getType() {
		return type;
	}
	public int getPortee() {
		return portee;
	}
	public int getPrix() {
		return prix;
	}
	public String toString() {
		String genre = "";
		if(type == EPEE) genre = "épée";
		if(type == LANCE) genre = "lance";
		if(type == HACHE) genre = "hache";
		
		String texte = nom 
				+ "\n Dégâts : " + degat + " / Précision : " + precision + "% / Critique : " + critique
				+ "%\n Type : " + genre + " / Portée : " + portee + " / Prix : " + prix + " PO";
		return texte;
	}
	
} // class