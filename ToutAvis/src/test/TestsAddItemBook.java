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
		// v�rifie que l'ajout d'un livre est refus�e (lev�e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur pass� en param�tre
		
		
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de livres a �t� modifi�");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre,auteur, genre, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas �t� correctement incr�ment�");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
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
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien �t� lev�e mais le nombre de livres a �t� modifi�");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
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
				System.out.println("Test " + idTest + " : l'exception NotMember a bien �t� lev�e mais le nombre de livres a �t� modifi�");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}


	public static void main(String[] args) {

		int nbLivres = 0;
		int nbFilms = 0;

		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests  ajouter des livres au r�seau social  ");


		SocialNetwork sn = new SocialNetwork();

		//instanciation de quatre membres
		
		try {
			sn.addMember("jacques", "qsdfgh", "Amateur");
			sn.addMember("Jean-Mi", "jeanjean", "Paysan");
			sn.addMember("Gaetan", "azerty", "Ivrogne");
			sn.addMember("Mick", "12345", "Prof � t�l�com bretagne");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception inattendue lev�e lors de l'utilisation de addMember");
		}
		
		// tests de addItemBook
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche num�ro 5

		// tentative d'ajout de livres avec entr�es "incorrectes"

		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "qsdfgh", "titre", "auteur", "genre", 90, "5.1", "L'ajout d'un livre avec un pseudo n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "  ", "qsdfgh", "titre", "auteur", "genre", 90, "5.2", "L'ajout d'un livre avec un pseudo qui ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", null, "auteur", "genre", 90, "5.3", "L'ajout d'un livre dont le titre n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "  ", "auteur", "genre", 90, "5.4", "L'ajout d'un livre dont le titre ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "titre", null, "genre", 90, "5.5", "L'ajout d'un livre dont l'auteur n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "  abc ", "titre", "auteur", "genre", 90, "5.6",  "L'ajout d'un livre avec un password ne contenant que 4 lettres est accept�");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "auteur", "genre", -90, "5.7",  "L'ajout d'un livre avec un nombre de pages n�gatif est accept�");

		
		// <=> fiche num�ro 6

		// ajout de 3 livres avec entrées "correctes"
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Jean-Mi", "jeanjean", "Les tracteurs","Jean-Pierre", "Thriller", 90, "6.1", "Erreur lors de l'ajout d'un livre avec param�tres corrects");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Gaetan", "azerty", "La bi�re", "Herv�", "Documentaire", 90, "6.2", "Erreur lors de l'ajout d'un livre avec param�tres corrects");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Mick", "12345", "La maisel", "Vincent", "Policier", 90, "6.3", "Erreur lors de l'ajout d'un livre avec param�tres corrects");

		
		
		// tentative d'ajout de livre avec identifiants erron�s

		nbTests++;
		nbErreurs += addItemBookNotMemberTest( sn, "Jean-Jacques", "jeanjean", "Les tracteurs","Jean-Pierre", "Thriller", 90, "6.4", "L'ajout d'un livre avec un pseudo n'existant pas est accept�");
		nbTests++;
		nbErreurs += addItemBookNotMemberTest( sn, "Jean-Mi", "irefirg", "Les tracteurs 2", "Jean-Pierre", "Thriller", 90, "6.5",  "L'ajout d'un livre avec un couple id/pwd erron� est accept�");
		
		//tentative d'ajout de livre d�j� existant
		
		nbTests++;
		nbErreurs += addItemBookAlreadyExistsTest( sn, "Gaetan", "azerty", "  Les tracteurs ","Jean-Pierre", "Western", 90, "4.6", "L'ajout d'un livre avec le m�me nom que le premier ajout� est accept�");

		
		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  :  le nombre de films apr�s utilisation de addItemBook a �t� modifi�");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}
