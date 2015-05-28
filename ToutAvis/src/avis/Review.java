package avis;

import java.util.LinkedList;


public class Review {

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Member"
	 */
	private Member author = null;

	/** 
	 * Getter of the property <tt>member</tt>
	 * @return  Returns the member.
	 * @uml.property  name="member"
	 */
	public Member getMember() {
		return author;
	}


	/**
	 * @uml.property  name="note"
	 */
	private float note;

	/**
	 * Getter of the property <tt>note</tt>
	 * @return  Returns the note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}

	/**
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;

	/**
	 * Getter of the property <tt>commentaire</tt>
	 * @return  Returns the commentaire.
	 * @uml.property  name="commentaire"
	 */
	public String getCommentaire() {
		return commentaire;
	}
		
		/**
		 */
	public Review(Member author_, float note_, String commentaire_){
		author = author_;
		note=note_;
		commentaire=commentaire_; 
		opinions = new LinkedList<Opinion>();
	}
	
	public String toString(){
		return "Note : "+note+" commentaire : "+commentaire+" auteur : "+author.getPseudo();
	}
	
	public static boolean testBadEntry(String pseudo, String password,String titre, float note, String commentaire){
		if(pseudo == null) return true;
		if(password == null) return true;
		if(titre == null) return true;
		if(commentaire == null) return true;
		if(pseudo.trim().length() < 1) return true;
		if(password.trim().length() < 4) return true;
		if(titre.trim().length() < 1) return true;
		if((note < 0) || (note > 5)) return true;
		return false;
	}


	/**
	 * @uml.property  name="opinions"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="review:avis.Opinion"
	 */
	private LinkedList<Opinion> opinions;

	public void addOpinion(Member author, float note){
		Opinion o1 = null;
		for(Opinion o : opinions){
			if(o.getPseudo().equals(author.getPseudo()))
				o1 = o;
		}
		if(o1 == null)
		opinions.add(new Opinion(author, note));
		else{
			opinions.remove(o1);
			opinions.add(new Opinion(author, note));
		}
	}
	

}
