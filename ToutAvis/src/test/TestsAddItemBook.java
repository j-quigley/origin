package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author Y. Maliszewski J. Quigley
 * @date mai 2015
 * @version V1.0
 */

public class TestsAddItemBook {



	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		// vérifie que l'ajout d'un livre est refusée (levée de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur passé en paramètre
		
		
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}


	public static void main(String[] args) {

		int nbLivres = 0;
		int nbFilms = 0;

		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des livres au réseau social  ");


		SocialNetwork sn = new SocialNetwork();

		//instanciation de quatre membres
		
		try {
			sn.addMember("jacques", "qsdfgh", "Amateur");
			sn.addMember("Jean-Mi", "jeanjean", "Paysan");
			sn.addMember("Gaetan", "azerty", "Ivrogne");
			sn.addMember("Mick", "12345", "Prof à télécom bretagne");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception inattendue levée lors de l'utilisation de addMember");
		}
		
		// tests de addItemBook
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche numéro 5

		// tentative d'ajout de livres avec entrées "incorrectes"

		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "qsdfgh", "titre", "auteur", "genre", 90, "5.1", "L'ajout d'un livre avec un pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "  ", "qsdfgh", "titre", "auteur", "genre", 90, "5.2", "L'ajout d'un livre avec un pseudo qui ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", null, "auteur", "genre", 90, "5.3", "L'ajout d'un livre dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "  ", "auteur", "genre", 90, "5.4", "L'ajout d'un livre dont le titre ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "titre", null, "genre", 90, "5.5", "L'ajout d'un livre dont l'auteur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "  abc ", "titre", "auteur", "genre", 90, "5.6",  "L'ajout d'un livre avec un password ne contenant que 4 lettres est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "auteur", "genre", -90, "5.7",  "L'ajout d'un livre avec un nombre de pages négatif est accepté");

		
		// <=> fiche numéro 6

		// ajout de 3 livres avec entr√©es "correctes"
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Jean-Mi", "jeanjean", "Les tracteurs","Jean-Pierre", "Thriller", 90, "6.1", "Erreur lors de l'ajout d'un livre avec paramètres corrects");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Gaetan", "azerty", "La bière", "Hervé", "Documentaire", 90, "6.2", "Erreur lors de l'ajout d'un livre avec paramètres corrects");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Mick", "12345", "La maisel", "Vincent", "Policier", 90, "6.3", "Erreur lors de l'ajout d'un livre avec paramètres corrects");

		
		
		// tentative d'ajout de livre avec identifiants erronés

		nbTests++;
		nbErreurs += addItemBookNotMemberTest( sn, "Jean-Jacques", "jeanjean", "Les tracteurs","Jean-Pierre", "Thriller", 90, "6.4", "L'ajout d'un livre avec un pseudo n'existant pas est accepté");
		nbTests++;
		nbErreurs += addItemBookNotMemberTest( sn, "Jean-Mi", "irefirg", "Les tracteurs 2", "Jean-Pierre", "Thriller", 90, "6.5",  "L'ajout d'un livre avec un couple id/pwd erroné est accepté");
		
		//tentative d'ajout de livre déjà existant
		
		nbTests++;
		nbErreurs += addItemBookAlreadyExistsTest( sn, "Gaetan", "azerty", "  Les tracteurs ","Jean-Pierre", "Western", 90, "4.6", "L'ajout d'un livre avec le même nom que le premier ajouté est accepté");

		
		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  :  le nombre de films après utilisation de addItemBook a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectu√©s");

	}
}
