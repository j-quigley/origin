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



	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste){
		// v�rifie que l'ajout d'un film est refus�e (lev�e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur pass� en param�tre
		
		
		int nbFilms = sn.nbFilms();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbMembers() != nbMembres) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de membres a été modifié");
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

	public static int addMemberOKTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest){
		int nbMembres = sn.nbMembers();
		try{
			sn.addMember (pseudo, pwd, profil);
			if (sn.nbMembers() != nbMembres+1) {
				System.out.println("Test " + idTest + " :  le nombre de membres n'a pas été correctement incrémenté");
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

	public static int addMemberAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (MemberAlreadyExists e) {
			if (sn.nbMembers() != nbMembres) {
				System.out.println("Test " + idTest + " : l'exception MemberAlreadyExists a bien été levée mais le nombre de membres a été modifié");
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
		
		System.out.println("Tests  ajouter des membres au réseau social  ");


		SocialNetwork sn = new SocialNetwork();

		// tests de addMember
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche numéro 1

		// tentative d'ajout de membres avec entrées "incorrectes"

		nbTests++;
		nbErreurs += addMemberBadEntryTest ( sn, null, "qsdfgh", "", "1.1", "L'ajout d'un membre dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addMemberBadEntryTest ( sn, " ", "qsdfgh", "", "1.2", "L'ajout d'un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addMemberBadEntryTest ( sn, "B", null, "", "1.3", "L'ajout d'un membre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addMemberBadEntryTest ( sn, "B", "   qwd ", "", "1.4", "L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addMemberBadEntryTest ( sn, "BBBB", "bbbb", null, "1.5", "L'ajout d'un membre dont le profil n'est pas instancié est accepté");



		// <=> fiche numéro 2

		// ajout de 3 membres avec entrées "correctes"

		nbTests++;
		nbErreurs += addMemberOKTest (sn, "Paul", "paul", "lecteur impulsif","2.1a");
		nbTests++;
		nbErreurs += addMemberOKTest (sn, "Antoine", "antoine", "grand amoureux de la littérature","2.1b");
		nbTests++;
		nbErreurs += addMemberOKTest (sn, "Alice", "alice", "20 ans, sexy","2.1c");

		// tentative d'ajout de membre "existant"

		nbTests++;
		nbErreurs += addMemberAlreadyExistsTest(sn, new String("Paul"), "abcdefghij", "", "2.2", "L'ajout d'un membre avec le pseudo du premier membre ajouté est accepté");
		nbTests++;
		nbErreurs += addMemberAlreadyExistsTest(sn, new String("Alice"), "abcdefghij", "", "2.3", "L'ajout d'un membre avec le pseudo du dernier membre ajouté est accepté");
		nbTests++;
		nbErreurs += addMemberAlreadyExistsTest(sn, new String("anToine"), "abcdefghij", "", "2.4", "L'ajout d'un membre avec un pseudo existant (avec casse différente) est accepté");
		nbTests++;
		nbErreurs += addMemberAlreadyExistsTest(sn, new String(" Antoine "), "abcdefghij", "", "2.5", "L'ajout d'un membre avec un pseudo existant (avec leading et trailing blanks) est accepté");		


		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  :  le nombre de films après utilisation de addMember a été modifié");
			nbErreurs++;
		}
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de livres après utilisation de addMember a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddMember :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}
