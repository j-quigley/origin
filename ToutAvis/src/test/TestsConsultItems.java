package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;

public class TestsConsultItems {

	public static int consultItemBadEntryTest(SocialNetwork sn, String item, String idTest, String messErreur){
		LinkedList<String> liste = new LinkedList<String>();
		try {
			liste = sn.consultItems(item);
			System.out.println("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch(BadEntry e) {
			if(liste.size() != 0){ 
				// Si la liste n'est pas nulle c'est que des choses on été ajouté
				System.out.println("Test " + idTest + " : l'execption BadEntry a bien été relevée mais la liste retournée n'est pas nulle");
				return 1;
			}
			else { // Sinon tout est OK 
				return 0;
			}
		}
		catch(Exception e){
			// Une execption autre que BadEntry ... 
			System.out.println("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			return 1;
		}

	}

	public static int consultItemOKTest(SocialNetwork sn, String item, String idTest) {
		LinkedList<String> liste = new LinkedList<String>();
		try {
			liste = sn.consultItems(item);
			if(liste.size() == 0) {
				System.out.println("Test " + idTest + " : la recherche s'est bien effectué mais il n'y a rien dans la liste ");
				return 1;
			}
			else {
				return 0;
			}
		}
		catch(Exception e) { 
			System.out.println("Test " + idTest + " : exeption non prévue. " + e);
			e.printStackTrace();
			return 1;
		}		
	}



	public static void main(String[] args) {
		int nbMember = 0;
		int nbLivres = 0;
		int nbFilms = 0;

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests consultItem");


		SocialNetwork sn = new SocialNetwork();

		/* On prend nos prérequis pour test les reviews :
		 * Il nous faut un livre, un film et un membre au minimun 
		 * Si On arrive pas à les ajouter, ça ne sert a rien de continuer ... 
		 */

		try {
			sn.addMember("Jacques", "aaaa", "Sexy girl !");
		}
		catch(Exception e) {
			System.out.println("Erreur sur la création d'un membre ... ");
			System.out.println(sn.toString());
			System.out.println("0 tests effectué ...");
			System.exit(1); // On quite ... 
		}

		try {
			sn.addItemBook("Jacques", "aaaa", "Star Wars 8", "Science fiction", "J. J. Abrams", 400);
		}
		catch(Exception e) {
			System.out.println("Erreur sur la création d'un item Book ... ");
			System.out.println(sn.toString());
			System.out.println("0 tests effectué ...");
			System.exit(1); // On quite ... 
		}

		try {
			sn.addItemFilm("Jacques", "aaaa", "Titre", "genre", "realisateur", "scenariste", 2);
		}
		catch(Exception e) {
			System.out.println("Erreur sur la création d'un item Film ... ");
			System.out.println(sn.toString());
			System.out.println("0 tests effectué ...");
			System.exit(1); // On quite ... 
		}

		// tests de reviewItemBook
		nbLivres = sn.nbBooks();
		nbMember = sn.nbMembers();
		nbFilms = sn.nbFilms();

		// <=> fiche numéro 1

		// tentative de consulatation avec entrées "incorrectes"

		nbTests++;
		nbErreurs += consultItemBadEntryTest(sn, null, "1.1", "La consultation avec un titre non instancié est autorisé");
		nbTests++;
		nbErreurs += consultItemBadEntryTest(sn, "      ", "1.2", "La consultation avec un titre avec que des espaces est autorisé");
		// <=> fiche numéro 2


		//Consulter avec des titres existant

		nbTests++;
		nbErreurs += consultItemOKTest(sn, "Star Wars 8", "2.1");
		nbTests++;
		nbErreurs += consultItemOKTest(sn, "Titre", "2.2");

		nbTests++;
		if (nbMember != sn.nbMembers()) {
			System.out.println("Erreur  :  le nombre de membres après utilisation de consultItem a été modifié");
			nbErreurs++;
		}
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de livres après utilisation de consultItem a été modifié");	
			nbErreurs++;
		}
		nbTests++;
		if(nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  :  le nombre de films après utilisation de consultItem a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addItemFilm
		System.out.println("Test consultItem :   " + nbErreurs + 
				" erreur(s) / " +  nbTests + " tests effectués");

		// ajouts au bilan en cours si le bilan est passé en paramètre
		if ((args != null) && (args.length == 2)) {        
			nbTests = nbTests + new Integer(args[0]);
			nbErreurs = nbErreurs + new Integer(args[1]);       
			args[0] = "" + nbTests;
			args[1] = "" + nbErreurs;
		}
	}

}