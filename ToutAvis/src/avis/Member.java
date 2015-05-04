package avis;


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

}
