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



	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, String idTest, String messErreur){
		// vérifie que l'ajout d'un film est refusée (levée de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur passé en paramètre
		
		
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, nbFilms);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
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

	public static int addItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, nbFilms);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
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

	public static int addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, nbFilms);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
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

	public static int addItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur,String scenariste, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, nbFilms);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
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
		
		System.out.println("Tests  ajouter des films au réseau social  ");


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
		
		// tests de addItemFilm
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche numéro 3

		// tentative d'ajout de films avec entrées "incorrectes"

		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "qsdfgh", "titre", "genre", "realisateur", "scenariste", "3.1", "L'ajout d'un film avec un pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "   ", "qsdfgh", "titre", "genre", "realisateur", "scenariste", "3.2", "L'ajout d'un film avec un pseudo qui ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", null, "genre", "realisateur", "scenariste", "3.3", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "  ", "genre", "realisateur", "scenariste", "3.4", "L'ajout d'un film dont le titre ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", null, "realisateur", "scenariste", "3.5", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "  ", "realisateur", "scenariste", "3.6", "L'ajout d'un film dont le genre ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", null, "scenariste", "3.7", "L'ajout d'un film dont le realisateur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "  ", "scenariste", "3.8", "L'ajout d'un film dont le realisateur ne contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "realisateur", null, "3.9", "L'ajout d'un film dont le scenariste n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "jacques", "qsdfgh", "titre", "genre", "realisateur", "  ", "3.10", "L'ajout d'un film dont le scenariste ne contient que des espaces est accepté");

		
		// <=> fiche numéro 4

		// ajout de 3 membres avec entr√©es "correctes"

		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Jean-Mi", "jeanjean", "Les tracteurs", "Thriller", "Jean-Pierre", "Bertrand", "4.1");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Gaetan", "azerty", "La bière", "Documentaire", "Hervé", "Jean-Claude", "4.2");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Mick", "12345", "La maisel", "Policier", "Vincent", "Franck", "4.3");

		
		
		// tentative d'ajout de film avec identifiants erronés

		nbTests++;
		nbErreurs += addItemFilmNotMemberTest( sn, "Jean-Jacques", "jeanjean", "Les tracteurs", "Thriller", "Jean-Pierre", "Bertrand", "4.4", "L'ajout d'un film avec un pseudo n'existant pas est accepté");
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest( sn, "Jean-Mi", "irefirg", "Les tracteurs 2", "Thriller", "Jean-Pierre", "Bertrand", "4.5", "L'ajout d'un film avec un couple id/pwd erroné est accepté");
		
		//tentative d'ajout de film déjà existant
		
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest( sn, "Gaetan", "azerty", "  Les tracteurs ", "Western", "Jean-Pierre", "Bertrand", "4.6", "L'ajout d'un film avec le même nom que le premier ajouté est accepté");

		
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de livres après utilisation de addItemFilm a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectu√©s");

	}
}
