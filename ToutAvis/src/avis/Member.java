package avis;

import exception.BadEntry;
import java.util.Collection;


public class Member {

	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;

	/**
	 * @uml.property  name="password"
	 */
	private String password;

	/**
	 * @uml.property  name="profil"
	 */
	private String profil;
	
	/**
	 * Getter of the property <tt>pseudo</tt>
	 * @return  Returns the pseudo.
	 * @uml.property  name="pseudo"
	 */
	public String getPseudo() {
		return pseudo;
	}

		
		/**
		 */
	public Member(String pseudo_, String password_, String profil_){
		pseudo = pseudo_;
		password = password_;
		profil = profil_; 
	}
	
	public boolean isPassword(String pwd){
		return password == pwd;
	}

	public String toString(){
		return "Pseudo : "+pseudo+ " profil : "+profil;
	}

			
		/**
		 */
	public static boolean testBadEntry(String pseudo, String password, String profil){
		if(pseudo == null) return true;
		boolean pseudoOK = false;
		for(int i = 0;i<pseudo.length();i++){
			if(pseudo.charAt(i)!=' '){ 
				pseudoOK = true;
				break;	
			}	
		}
		if(!pseudoOK) return true;
		if(password == null) return true;
		String pwd = password.trim();
		if(pwd.length() < 4) return true;
		if(profil == null) return true;
		return false;	
	}


		/**
		 * @uml.property  name="reviews"
		 * @uml.associationEnd  multiplicity="(0 -1)" inverse="member:avis.Review"
		 */
		private Collection<Review> reviews;


		/**
		 * Mesure la qualité des reviews émises par le membre
		 * @uml.property  name="karma"
		 */
		private float karma;
		
		public void updateKarma(){
			float moy = 0;
			for(Review r : reviews){
				moy =+ r.getNote();
			}
			moy = moy / reviews.size();
			karma = moy / 5;
		}
		
		public float getKarma(){
			return karma;
		}

			
			

}
