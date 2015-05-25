package avis;


public class Opinion {

	/**
	 * @uml.property  name="note"
	 */
	private float note;
	
	public Opinion(Member author_, float note_){
		author=author_;
		note=note_;
	}

	public static boolean testBadEntry(String pseudo, String password,String type,String titre, String login, float note){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(login == null) return true;
		if(type == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(login.trim().length() < 1) return true;
		if(titre.trim().length() < 1) return true;
		if((note <= 0) || (note > 10)) return true;
		if((!type.trim().toLowerCase().equals("film"))&&(!type.trim().toLowerCase().equals("book"))) return true;
		return false;
	}

	/**
	 * @uml.property  name="author"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="opinion:avis.Member"
	 */
	private Member author = null;
	
	public String getPseudo(){
		return author.getPseudo();
	}
	
	public float getNote(){
		return note;
	}
}
