package test;

import test.TestsReviewItemFilm.Moyenne;
import avis.SocialNetwork;
import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;

public class TestsReviewOpinion {

	static class Moyenne {
		public float value;
	}

	public class Karma{
		public float value;
	}

	public static int reviewOpinionBadEntryTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String  pwd, String type, String titre, String login, float note, String idTest, String messErreur){
		// vï¿½rifie que l'ajout d'une opinion est refusï¿½e (levï¿½e de l'exception BadEntry et pas de modification de sn)
		// ne fait rien si c'est le cas
		// sinon, affiche le message d'erreur passï¿½ en paramï¿½tre
		float moy = moyenne.value;
		try{
			moy = sn.reviewOpinion(pseudo, pwd, type, titre, login, note);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien Ì©tÌ© levÌ©e mais la moyenne a Ì©tÌ© modifiÌ©");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewOpinionOKTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd,String type, String titre, String login, float note, String idTest){
		float moy = moyenne.value;	
		try{
			moy = sn.reviewOpinion(pseudo, pwd, type, titre, login, note);
			if (moyenne.value != moy)
				return 0;
			System.out.println("Test " + idTest + " : la moyenne n'a pas �t� modifi�e apr�s l'ajout d'une opinion correcte");
			return 1;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewOpinionNotMemberTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String pwd, String type, String titre, String login, float note, String idTest, String messErreur){
		float moy = moyenne.value;	
		try{
			moy = sn.reviewOpinion(pseudo, pwd, type, titre, login, note);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch(NotMember e){
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien Ì©tÌ© levÌ©e mais la moyenne a Ì©tÌ© modifiÌ©");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}


	public static int reviewOpinionNotItemTest(SocialNetwork sn, Moyenne moyenne,String pseudo, String pwd, String type, String titre,String login, float note, String idTest, String messErreur){
		float moy = moyenne.value;
		try {
			moy = sn.reviewOpinion(pseudo, pwd, type, titre, login, note);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception NotItem a bien Ì©tÌ© levÌ©e mais la moyenne a Ì©tÌ© modifiÌ©");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int reviewOpinionNotReviewTest(SocialNetwork sn, Moyenne moyenne,String pseudo, String pwd, String type, String titre,String login, float note, String idTest, String messErreur){
		float moy = moyenne.value;
		try {
			sn.reviewOpinion(pseudo, pwd, type, titre, login, note);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotReview e) {
			if (moyenne.value != moy) {
				System.out.println("Test " + idTest + " : l'exception NotReview a bien Ì©tÌ© levÌ©e mais la moyenne a Ì©tÌ© modifiÌ©");
				return 1;
			}
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}



	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests  ajouter des opinions sur des avis au rÃ©seau social  ");

		SocialNetwork sn = new SocialNetwork();

		//instanciation de quatre membres

		try {
			sn.addMember("jacques", "aaaa", "Amateur");
			sn.addMember("nico", "bbbb", "Paysan");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception inattendue levŽe lors de l'utilisation de addMember");
		}

		//Ajout de deux livres

		try{
			sn.addItemBook("jacques", "aaaa", "La biere", "V Hugo","Policier",350);
			sn.addItemBook("nico", "bbbb", "Dirac en 0", "Nico", "Autobiographie",120);
			sn.addItemFilm("jacques", "cccc", "La biere", "Policier","Jacouille", "Bruno",  350);

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception inattendue levŽe lors de l'utilisation de addItem");
		}

		//Ajout de deux livres et d'un film

		try{
			sn.reviewItemBook("jacques", "aaaa", "La biere", 4.5f, "C'est ma passion");
			sn.reviewItemBook("nico", "bbbb", "La biere", 1.0f, "Je prefere le panach�");
			sn.reviewItemBook("jacques", "aaaa", "Dirac en 0", 2.0f, "Pas terrible");
			sn.reviewItemBook("nico", "bbbb", "Dirac en 0", 4.8f, "Super film");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception inattendue levŽe lors de l'utilisation de reviewItem");
		}

		Moyenne moyenne = new Moyenne();
		moyenne.value = 0.0f;

		// <=> fiche numÃ©ro 11


		// tentative d'ajout de membres avec entrÃ©es "incorrectes"

		nbTests++;
		nbErreurs =+ reviewOpinionBadEntryTest(sn,moyenne,null, "aaaa", "La biere", "book", "nico", 1.0f, "11.1", "L'ajout d'une opinion avec un pseudo non nstanciÃ© est acceptÃ©");
		nbTests++;
		nbErreurs =+ reviewOpinionBadEntryTest(sn,moyenne," ", "aaaa",  "book","La biere", "nico", 1.0f, "11.2", "L'ajout d'une opinion avec un pseudo ne contenant pas un caracteres, autre que des espaces, est acceptÃ©");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques","book", null, "La biere",  "nico", 0.0f, "11.3", "L'ajout d'une opinion dont le password n'est pas instanciÃ© est acceptÃ©");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques","  aa  ", "book",null,  "nico", 0.0f, "11.4","L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de dÃ©but ou de fin, est acceptÃ©");
		nbTests++;
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa", "book","La biere",  null, 0.0f, "11.5", "L'ajout d'une opinion dont le titre n'est pas instanciÃ© est acceptÃ©");
		nbTests++;		
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa", "book","  ", "nico", 0.0f, "11.6", "L'ajout d'une opinion dont le titre ne contient que des espaces est acceptÃ©");
		nbTests++;		
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa",  "book","La biere ","nico", -3.0f, "11.7", "L'ajout d'une opinion dont la note est negative est acceptÃ©");
		nbTests++;		
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa", "book", "La biere ", "nico", 15.0f, "11.8", "L'ajout d'une opinion dont la note est superieure a 10 est acceptÃ©");
		nbTests++;		
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa", null, "La biere ", "nico", 5.0f, "11.9", "L'ajout d'une opinion dont le type n'est pas instanci� est acceptÃ©");
		nbTests++;		
		nbErreurs += reviewOpinionBadEntryTest ( sn, moyenne, "jacques", "aaaa", "   ", "La biere ", "nico", 5.0f, "11.10", "L'ajout d'une opinion dont le type ne contient que des espaces est acceptÃ©");

		// <=> fiche numÃ©ro 12

		// ajout de 5 opinions avec entrÃ©es "correctes"

		nbTests++;
		nbErreurs += reviewOpinionOKTest (sn, moyenne, "jacques", "aaaa","book","La biere", "nico",1.0f,"12.1");
		nbTests++;
		nbErreurs += reviewOpinionOKTest (sn, moyenne, "nico", "bbbb","book","  La BIEre","jacques",1.0f,"12.2");
		nbTests++;
		nbErreurs += reviewOpinionOKTest (sn, moyenne, "jacques", "aaaa","book","la biere","jacques",7.0f,"12.3");
		nbTests++;
		nbErreurs += reviewOpinionOKTest (sn, moyenne, "nico", "bbbb","book","Dirac en 0","jacques",2.5f,"12.4");
		nbTests++;
		nbErreurs += reviewOpinionOKTest (sn, moyenne, "jacques", "aaaa","film","La biere","nico",10.0f,"12.5");

		// tentative d'ajout d'opinion avec parametre couple login/password incohÃ©rent 

		nbTests++;
		nbErreurs += reviewOpinionNotMemberTest(sn, moyenne, "xx-jacqueslebgdu29-xx", "aaaa","book","La biere","nico",3.0f,"12.5","L'ajout d'une opinion dont le pseudo est inexistant est acceptÃ©");
		nbTests++;
		nbErreurs += reviewOpinionNotMemberTest(sn, moyenne, "jacques", "bbbazab", "book","  La BIEre","nico",1.0f,"12.6","L'ajout d'une opinion dont le couple pseudo/password est incohÃ©rent est acceptÃ©");

		// tentative d'ajout d'opinion avec titre de film inexistant

		nbTests++;
		nbErreurs += reviewOpinionNotItemTest(sn, moyenne, "jacques", "aaaa","book","Le powerpc","nico",3.0f,"12.7","L'ajout d'une opinion avec un titre de film inexistant est acceptÃ©");

		// tentative d'ajout d'opinion avec login de reviewer inexistant

		nbTests++;
		nbErreurs += reviewOpinionNotItemTest(sn, moyenne, "jacques", "aaaa","book","La biere","nicoshow",3.0f,"12.8","L'ajout d'une opinion avec un login de reviewer inexistant est acceptÃ©");


		// ce n'est pas du test, mais cela peut "rassurer"...
		nbTests++;
		System.out.println(sn);

		// bilan du test de addMember
		System.out.println("Tests Review Opinion:   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectuÃ©s");

		// ajouts au bilan en cours si le bilan est passŽ en param�tre
		if ((args != null) && (args.length == 2)) {        
			nbTests = nbTests + new Integer(args[0]);
			nbErreurs = nbErreurs + new Integer(args[1]);       
			args[0] = "" + nbTests;
			args[1] = "" + nbErreurs;
		}

	}
}

