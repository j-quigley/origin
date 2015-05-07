package avis;

import java.util.Collection;


public abstract class Item {


	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="item:avis.Review"
	 */
	private Collection<Review> reviews;
	/** 
	 * @uml.property name="author"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="item:avis.Member"
	 */
	protected Member author = null;

	public Item(Member author_){
		author = author_;
	}
}
