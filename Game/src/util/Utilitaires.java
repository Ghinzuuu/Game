package util;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 
 * @author Lancelot de Wouters 3/1/2016
 * 
 */
public final class Utilitaires {

	/*
	 * Note du d�veloppeur
	 * 
	 * Une partie des m�thodes ci-dessous ont �t� pomp�es des cours
	 * d'algorithmiques de l'IPL (2016). Leurs cr�dits revient � l'Institut :).
	 * De plus, le vulgaire copi�-coll� que je me suis permis de faire a �t�
	 * fais b�tement : il est possible que certaines des m�thodes ci-dessous ne
	 * soient utilis�es nulle part dans mon programme. Vous pouvez appeler cela
	 * "la flemme du d�veloppeur" . J'implore la cl�mence face � ce manque de
	 * rigueur.
	 * 
	 */
	
	
	private static java.util.Random random = new Random();
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	static {
		Pattern p = Pattern.compile(System.getProperty("line.separator"));
		scanner.useDelimiter(p);
	}
	
	
	private Utilitaires() {}

	public static int entrerNombre(){
		String input;
		int nb=-1;
		while(true){
			try{
				input = scanner.next();
				nb = Integer.parseInt(input);
				return nb;
			}catch (NumberFormatException e) {
				System.out.println("Ceci n'est pas un chiffre");
			}
		}
	} // entrerNombre
	
	public static String entrerString(){
		String mot;
		while(true){
			try{
				mot = scanner.next();
				return mot;
			}catch (InputMismatchException ime) {
				System.out.println("Probl�me d'insertion de l'utilisateur.");
			}
		}
	} // entrerString
	
	/**
	 * Genere un nombre au hasard compris entre les bornes borneInf et borneSup
	 * 
	 * le "hasard" dependant du PRNG fourni par java.util.Random
	 * 
	 * cette m�thode ne fonctionne pas correctement
	 * 
	 * @param borneInf
	 * @param borneSup
	 * @return un entier au hasard entre borneInf et borneSup
	 */
	public static int unEntierAuHasardEntre2(int borneInf, int borneSup) {
		return random.nextInt(borneSup) + borneInf;
	}

	// cette m�thode donne un nombre au hasard compris entre les deux bornes
	// hypothese : valeurMinimale est plus petite que valeurMaximale
	public static int unEntierAuHasardEntre(int valeurMinimale, int valeurMaximale) {
		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;

	}
	
	// cette m�thode permet d'afficher la table indiqu�e en param�tre
	public static void afficherTable(Object[] table) {
		for (int i = 0; i < table.length; i++) {
			System.out.println(table[i]);
		}
	}

	// cette m�thode permet d'afficher la table indiqu�e en param�tre avec
	// l'indice de chaque �l�ment situ� devant
	public static void afficherTableAvecIndice(Object[] table, int tailleLogique) {
		for (int i = 1; i <= tailleLogique; i++) {
			System.out.println(i + ". " + table[i - 1]);
		}
	}

	// m�thode de suppression en O(1) dans une table en �crasant l'�l�ment
	// s�lectionn� avec le dernier �l�ment de la table
	// /!\ � ne pas utiliser dans les tables tri�es
	public static void suppression(Object[] table, int tailleLogique, int indice) {
		table[indice] = table[tailleLogique - 1];
		table[tailleLogique - 1] = null;
	}

	// m�thode qui force l'utilisateur a entrer un int compris entre les bornes
	public static int lireNombreComprisEntre(int a, int b) {
		int nombre = entrerNombre();
		while (nombre < a || nombre > b) {
			System.out.println("Le nombre doit etre compris entre " + a + " et " + b);
			nombre = entrerNombre();
		}
		return nombre;
	}

	// m�thode qui force l'utilisateur a entrer un char 'O', 'o' ou 'N', 'n'
	public static char lireOouN() {
		char reponse = Character.MAX_VALUE;
		String str = entrerString();
		if(!str.equals("")){
			reponse = str.charAt(0);
		}
		
		while (reponse != 'O' && reponse != 'o' && reponse != 'N' && reponse != 'n') {
			System.out.println("R�pondez O, o ou N, n");
			str = entrerString();
			if(!str.equals("")){
				reponse = str.charAt(0);
			}			
		}
		return reponse;
	}

	// m�thode qui donne un int compris entre 1 et 26 pour un char compris entre
	// A et Z
	public static int convertiCharEnInt(char c) {
		if (c < 'A' || c > 'Z')
			throw new IllegalArgumentException("Char invalide");
		for (int i = 1; i <= 26; i++) {
			// @ est le char juste avant A
			if (c == '@' + i)
				return i;
		}
		// ce return ne devrait jamais avoir lieu
		return -1;
	}

	// m�thode qui donne un char compris entre A et Z pour un int compris entre
	// 1 et 26
	public static char convertiIntEnChar(int i) {
		if (i < 1 || i > 26)
			throw new IllegalArgumentException("Char invalide");
		char c = 'A';
		for (int j = 1; j < i; j++, c++);
		return c;
	}

	// affiche un tableau en 2 dimesion
	public static void affichageTab2D(Object[][] tableau) {
		String aAfficher = "";
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[0].length; j++) {
				aAfficher += tableau[i][j] + " |";
			}
			aAfficher += "\n";
		}
		System.out.println(aAfficher);
	} //

	// m�thode permettant � l'utilisateur de cr�er un tableau non tri� d'entier.
	// le d�veloppeur fixe les nombres limites et la taille du tableau
	// sera peut-�tre un jour utile
//	public static int[] creerTabDEntierAvecBorne(int tailleTab, int borneMin, int borneMax) {
//		int[] tabDEntier = new int[tailleTab];
//		int i = 0;
//		char reponse = 'O';
//		while (i < tailleTab && reponse != 'N') {
//			System.out.println("Entrer un entier compris entre " + borneMin + " et " + borneMax);
//			tabDEntier[i] = lireNombreComprisEntre(borneMin, borneMax);
//			i++;
//			System.out.println("Voulez-vous continuer? O/N");
//			reponse = lireOouN();
//		}
//		// le if ci-dessous permet de garantir que la taille logique de la tab
//		// renvoy� = taille physique
//		if (i != tailleTab) {
//			int[] tabDEntierARenvoyer = new int[i];
//			for (int j = 0; j < tabDEntierARenvoyer.length; j++) {
//				tabDEntierARenvoyer[j] = tabDEntier[j];
//			}
//			return tabDEntierARenvoyer;
//		} else
//			return tabDEntier;
//	}
	
}// class