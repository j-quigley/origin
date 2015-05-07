package test;

public class TestSocialNetwork {

	   /**
	    * @param args
	    */
	   public static void main(String[] args) {
	      
	      String nbTests = "" + 0;
	      String nbErreurs = "" + 0;
	      String [] resultats = new String[] {"0", "0"};
	      
	   

	      
	      TestsInitialisation.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsAddMember.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsAddItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsReviewItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");
	      
	      // .... d'autres appels � des tests
	      
	      System.out.println("Bilan des Tests :   " + resultats[1] + 
	                         " erreur(s) / " +  resultats[0] + " tests effectu�s");
	      
	   }

	}
