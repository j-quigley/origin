package avis;

import java.util.Collection;
import java.util.LinkedList;


public abstract class Item {

	public float note;
	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="item:avis.Review"
	 */
	protected LinkedList<Review> reviews;
	/** 
	 * @uml.property name="author"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="item:avis.Member"
	 */
	protected Member author = null;

	public Item(Member author_){
		author = author_;
		reviews = new LinkedList<Review>();
	}
	
	public float getMoyenne(){
		return note;
	}
	
	public void addReview(Review r){
		reviews.add(r);
	}
	public void removeReview(Review r){
		reviews.remove(r);
	}
	
	public boolean isReview(String login){
		for(Review r : reviews){
			if(r.getMember().getPseudo().equals(login))
				return true;
		}
		return false;
	}
	
	public Review getReview(String login){
		Review r1 = null;
		for(Review r : reviews){
			if(r.getMember().getPseudo().equals(login))
				r1 = r;
		}
		return r1;
	}
	
	public void addOpinion(Member author, float note){
		for(Review r : reviews){
			if(r.getMember().getPseudo().equals(author.getPseudo()))
				r.addOpinion(author, note);
		}		
	}
	
	public void updateNote(){
		float moy = 0;
		for(Review r : reviews){
			moy =+ r.getNote()*r.getMember().getKarma();
		}
		note = moy / reviews.size();
	}
}
