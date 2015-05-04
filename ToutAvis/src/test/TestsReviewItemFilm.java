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



	public static int reviewItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		// v�rifie que l'ajout d'un film est refus�e (lev�e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur pass� en param�tre
				
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée ");
				return 1;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String comment,String idTest){
		try{
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewItemNotMemberTest(SocialNetwork sn, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 0;
		}
		catch (NotMember e) {
			System.out.println ("Test " + idTest + " le pseudo est inexistant ou password et pseudo incorrects.");
			return 1;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int reviewItemNotItem(SocialNetwork sn, String pseudo, String pwd, String titre, float note, String comment,String idTest, String messErreur){
		try {
			sn.reviewItemFilm(pseudo, pwd, titre, note, comment);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 0;
		}
		catch (NotItem e) {
			System.out.println ("Test " + idTest + " Le titre de film saisi n'existe pas");
			return 1;
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
		
		System.out.println("Tests  ajouter des opinions au réseau social  ");

		SocialNetwork sn = new SocialNetwork();

		
		// <=> fiche numéro 1
		

		// tentative d'ajout de membres avec entrées "incorrectes"

		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, null, "qsdfgh","tttttt" ,4.0f," Pas terrible ce truc", "1.1", "L'ajout d'une opinion avec un pseudo non nstancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, " ", "qsdfgh", "aaaaaa",0.6f,"Oh no","1.2", "L'ajout d'une opinion ne contenant pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "B",null, "hhhhhhh",0.0f,"", "1.3", "L'ajout d'une opinion dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "B"," qqf", null,0.0f,"Oh no", "1.4","L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "BBBB", "bbbb", null,0.0f,"Oh no", "1.5", "L'ajout d'une opinion dont le titre n'est pas instancié est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "BBBB", "bbbb", " ",0.0f,"Oh no", "1.6", "L'ajout d'une opinion dont le titre ne contient que des espaces est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "BBBB", "bbbb", "cccc ",-3.0f,"Oh no", "1.7", "L'ajout d'une opinion dont la note est negative est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "BBBB", "bbbb", "gggg ",7.0f,"Oh no", "1.8", "L'ajout d'une opinion dont la note est superieure a 5 est accepté");
		nbTests++;		
		nbErreurs += reviewItemFilmBadEntryTest ( sn, "BBBB", "bbbb", " gggg",0.0f,"Oh no", "1.6", null);


		// <=> fiche numéro 2

		// ajout de 3 opinions avec entrées "correctes"

		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, "Paul", "paul","Zaza in wonderland",3.0f,"C'est quoi ce truc","2.1a");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, "Antoine", "antoine","Robin des Bois",1.0f,"Vraiment mauvais","2.1b");
		nbTests++;
		nbErreurs += reviewItemFilmOKTest (sn, "Alice", "alice","Fifty shades of grey",4.5f,"Encore Merci","2.1c");

		// tentative d'ajout d'opinion avec parametre couple login/password incohérent + nom de film inexistant

		nbTests++;
		nbErreurs += reviewItemNotMemberTest(sn, "JohnDoe", "alice","Fifty shades of grey",4.5f,"Encore Merci","2.2","L'ajout d'une opinion dont le pseudo est inexistant est accepté");
		nbTests++;
		nbErreurs += reviewItemNotMemberTest(sn, "Antoine", "alice","Fifty shades of grey",4.5f,"Encore Merci","2.3","L'ajout d'une opinion dont le couple pseudo/password est incohérent est accepté");
		nbTests++;
		nbErreurs += reviewItemNotMemberTest(sn, "Antoine", "antoine","Robin Hood",1.0f,"Vraiment mauvais","2.4","L'ajout d'une opinion avec un titre de film inexistant est accepté");
		nbTests++;


		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("TestsAddMember :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");

	}
}

