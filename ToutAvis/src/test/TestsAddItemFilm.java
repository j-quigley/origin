package test;

import java.util.LinkedList;

import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author Y. Maliszewski J. Quigley
 * @date avril 2015
 * @version V1.0
 */

public class TestsAddItemFilm {



	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste,int duree, String idTest, String messErreur){
		// v�rifie que l'ajout d'un film est refus�e (lev�e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur pass� en param�tre
		
		
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de films a �t� modifi�");
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

	public static int addItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas �t� correctement incr�ment�");
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

	public static int addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien �t� lev�e mais le nombre de films a �t� modifi�");
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

	public static int addItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree );
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien �t� lev�e mais le nombre de films a �t� modifi�");
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
		
		System.out.println("Tests  ajouter des films au r�seau social  ");


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
		
		// tests de addItemFilm
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche num�ro 3

		// tentative d'ajout de films avec entr�es "incorrectes"

		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "qsdfgh", "titre", "genre", "realisateur", "scenariste", 90, "3.1", "L'ajout d'un film avec un pseudo n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "   ", "qsdfgh", "titre", "genre", "realisateur", "scenariste", 90, "3.2", "L'ajout d'un film avec un pseudo qui ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", null, "genre", "realisateur", "scenariste", 90, "3.3", "L'ajout d'un film dont le titre n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "  ", "genre", "realisateur", "scenariste", 90, "3.4", "L'ajout d'un film dont le titre ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", null, "realisateur", "scenariste", 90, "3.5", "L'ajout d'un film dont le genre n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "  ", "realisateur", "scenariste", 90, "3.6", "L'ajout d'un film dont le genre ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", null, "scenariste",90, "3.7",  "L'ajout d'un film dont le realisateur n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "  ", "scenariste", 90,"3.8",  "L'ajout d'un film dont le realisateur ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "realisateur", null, 90,"3.9", "L'ajout d'un film dont le scenariste n'est pas instanci� est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "realisateur", "  ",  90,"3.10", "L'ajout d'un film dont le scenariste ne contient que des espaces est accept�");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "realisateur", "  ", -90, "3.11",  "L'ajout d'un film avec une dur�e n�gative est accept�");

		
		// <=> fiche num�ro 4

		// ajout de 3 films avec entrées "correctes"
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Jean-Mi", "jeanjean", "Les tracteurs", "Thriller", "Jean-Pierre", "Bertrand", 90, "4.1");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Gaetan", "azerty", "La bi�re", "Documentaire", "Herv�", "Jean-Claude", 90, "4.2");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Mick", "12345", "La maisel", "Policier", "Vincent", "Franck", 90, "4.3");

		
		
		// tentative d'ajout de film avec identifiants erron�s

		nbTests++;
		nbErreurs += addItemFilmNotMemberTest( sn, "Jean-Jacques", "jeanjean", "Les tracteurs", "Thriller", "Jean-Pierre", "Bertrand", 90, "4.4", "L'ajout d'un film avec un pseudo n'existant pas est accept�");
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest( sn, "Jean-Mi", "irefirg", "Les tracteurs 2", "Thriller", "Jean-Pierre", "Bertrand", 90, "4.5",  "L'ajout d'un film avec un couple id/pwd erron� est accept�");
		
		//tentative d'ajout de film d�j� existant
		
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest( sn, "Gaetan", "azerty", "  Les tracteurs ", "Western", "Jean-Pierre", "Bertrand",90, "4.6", "L'ajout d'un film avec le m�me nom que le premier ajout� est accept�");

		
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de livres apr�s utilisation de addItemFilm a �t� modifi�");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

		// ajouts au bilan en cours si le bilan est pass� en param�tre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
		
	}
}
