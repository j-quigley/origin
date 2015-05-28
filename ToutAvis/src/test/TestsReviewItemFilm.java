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

public class TestsReviewItemFilm {

	static class Moyenne {
		public float value;
	}

	public static int reviewItemFilmBadEntryTest (SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		// v�rifie que l'ajout d'un film est refus�e (lev�e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur pass� en param�tre
		float moy = moyenne.value;		
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien ̩t̩ lev̩e mais la moyenne a ̩t̩ modifi̩");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}



	public static int reviewItemFilmOKTest (SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd, String titre, float note, String comment,String idTest){
		float moy = moyenne.value;	
		try{
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien ̩t̩ lev̩e mais la moyenne n'a pas ̩t̩ modifi̩");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewItemNotMemberTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		float moy = moyenne.value;
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien ̩t̩ lev̩e mais la moyenne a ̩t̩ modifi̩");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewItemNotItemTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		float moy = moyenne.value;
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception NotItem a bien ̩t̩ lev̩e mais la moyenne a ̩t̩ modifi̩");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}




	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests  ajouter des avis au réseau social  ");

		SocialNetwork sn = new SocialNetwork();

		//instanciation de quatre membres

		try {
			sn.addMember("jacques", "aaaa", "Amateur");
			sn.addMember("Jean-Mi", "bbbb", "Paysan");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception inattendue lev�e lors de l'utilisation de addMember");
		}
		
		//Ajout de deux livres
		
		try{
			sn.addItemFilm("jacques", "aaaa", "La biere", "Policier","Jacouille", "Bruno",  350);
			sn.addItemFilm("Jean-Mi", "bbbb", "Dirac en 0", "Com�die", "Nico", "Tanguy", 120);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception inattendue lev�e lors de l'utilisation de addItem");
		}

		Moyenne moyenne = new Moyenne();
		moyenne.value = 0.0f;
		
		// <=> fiche numéro 9


		// tentative d'ajout de membres avec entrées "incorrectes"

		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, null, "aaaa","La biere" ,4.0f," Pas terrible ce truc", "7.1", "L'ajout d'une opinion avec un pseudo non nstancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn,moyenne, " ", "aaaa", "La biere",0.6f,"Oh no","7.2", "L'ajout d'une opinion ne contenant pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques", null, "La biere",0.0f,"", "7.3", "L'ajout d'une opinion dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques"," aaa  ", null,0.0f,"Oh no", "7.4","L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques", "aaaa", null,0.0f,"Oh no", "7.5", "L'ajout d'une opinion dont le titre n'est pas instancié est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques", "aaaa", " ",0.0f,"Oh no", "7.6", "L'ajout d'une opinion dont le titre ne contient que des espaces est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques", "aaaa", "La biere ",-3.0f,"Oh no", "7.7", "L'ajout d'une opinion dont la note est negative est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, moyenne, "jacques", "aaaa", "La biere ",7.0f,"Oh no", "7.8", "L'ajout d'une opinion dont la note est superieure a 5 est accepté");


		// <=> fiche numéro 10

		// ajout de 3 opinions avec entrées "correctes"

		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, moyenne, "jacques", "aaaa","La biere",3.0f,"J'aime les binchs","8.1");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, moyenne, "Jean-Mi", "bbbb","  La BIEre",1.0f,"Super film","8.2");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, moyenne, "jacques", "aaaa","la biere",4.5f,"Un incontournable","8.3");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, moyenne, "Jean-Mi", "bbbb","Dirac en 0",2.5f,"Bof","8.4");

		// tentative d'ajout d'opinion avec parametre couple login/password incohérent 

		nbTests++;
		nbErreurs += reviewItemNotMemberTest(sn, moyenne, "xx-jacqueslebgdu29-xx", "aaaa","La biere",3.0f,"J'aime les binchs","8.5","L'ajout d'une opinion dont le pseudo est inexistant est accepté");
		nbTests++;
		nbErreurs += reviewItemNotMemberTest(sn, moyenne, "Jean-Mi", "bbbazab","  La BIEre",1.0f,"Super film","8.6","L'ajout d'une opinion dont le couple pseudo/password est incohérent est accepté");
		
		// tentative d'ajout d'opinion avec titre de film inexistant
		
		nbTests++;
		nbErreurs += reviewItemNotItemTest(sn, moyenne, "jacques", "aaaa","Le powerpc",3.0f,"J'aime les testarossas","8.7","L'ajout d'une opinion avec un titre de film inexistant est accepté");
		


		// ce n'est pas du test, mais cela peut "rassurer"...
		nbTests++;
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsReviewItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

		// ajouts au bilan en cours si le bilan est passŽ en param�tre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
		
	}
}

