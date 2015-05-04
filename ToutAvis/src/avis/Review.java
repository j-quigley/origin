package avis;


public class Review {

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="review:avis.Member"
	 */
	private Member member = null;

	/** 
	 * Getter of the property <tt>member</tt>
	 * @return  Returns the member.
	 * @uml.property  name="member"
	 */
	public Member getMember() {
		return member;
	}

	/** 
	 * Setter of the property <tt>member</tt>
	 * @param member  The member to set.
	 * @uml.property  name="member"
	 */
	public void setMember(Member member) {
		this.member = member;
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
	 * Setter of the property <tt>note</tt>
	 * @param note  The note to set.
	 * @uml.property  name="note"
	 */
	public void setNote(float note) {
		this.note = note;
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
	 * Setter of the property <tt>commentaire</tt>
	 * @param commentaire  The commentaire to set.
	 * @uml.property  name="commentaire"
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

		
		/**
		 */
		public Review(float note_, String commentaire_){
		note=note_;
		commentaire=commentaire_; }

}
