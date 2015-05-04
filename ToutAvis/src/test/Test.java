package test;

public class Test {

	   /**
	    * @param args
	    */
	   public static void main(String[] args) {
	      
	      String nbTests = "" + 0;
	      String nbErreurs = "" + 0;
	      String [] resultats = new String[] {"0", "0"};
	      
	   // bilan du test de addMember
	         System.out.println("TestsAddMember :   " + nbErreurs + 
	                            " erreur(s) / " +  nbTests + " tests effectus");
	      
	      // ajouts au bilan en cours si le bilan est pass en paramtre
	         if ((args != null) && (args.length == 2)) {        
	            nbTests = nbTests + new Integer(args[0]);
	            nbErreurs = nbErreurs + new Integer(args[1]);       
	            args[0] = "" + nbTests;
	            args[1] = "" + nbErreurs;
	         }

	      
	      TestsInitialisation.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsAddMember.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsAddItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");

	      TestsReviewItemFilm.main(resultats);
	      
	      System.out.println("\n\n ***************************************\n");
	      
	      // .... d'autres appels ˆ des tests
	      
	      System.out.println("Bilan des Tests :   " + resultats[1] + 
	                         " erreur(s) / " +  resultats[0] + " tests effectus");
	      
	   }

	}
