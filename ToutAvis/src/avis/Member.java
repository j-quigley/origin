package avis;

import java.util.LinkedList;


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
		karma = 1;
		reviews = new LinkedList<Review>();
	}
	
	public boolean isPassword(String pwd){
		return password.equalsIgnoreCase(pwd);
	}

	public String toString(){
		return "Pseudo : "+pseudo+ " profil : "+profil + " karma : "+karma;
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
		private LinkedList<Review> reviews;

		public void addReview(Review r){
			reviews.add(r);
		}
		
		public void removeReview(Review r){
			reviews.remove(r);
		}
		
		/**
		 * Mesure la qualit� des reviews �mises par le membre
		 * @uml.property  name="karma"
		 */
		private float karma;
		
		public void updateKarma(){
			float moy = 0;
			for(Review r : reviews){
				moy = moy + r.getMoyenneOpinions();
			}
			moy = moy / reviews.size();
			karma = moy / 5;
		}
		
		public float getKarma(){
			return karma;
		}

			
			

}
