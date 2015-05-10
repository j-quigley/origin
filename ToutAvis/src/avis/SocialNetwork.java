package avis;

import java.util.ArrayList;
import java.util.LinkedList;


import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import java.util.Collection;

/** 
 * @author J. Quigley Y. Maliszewski
 * @date mai 2015
 * @version V0.6
 */


/** 
 * <p>
 * <b>SystÃ¨me de mutualisation d'opinions portant sur des domaines
 * variÃ©s (littÃ©rature, cinÃ©ma, art, gastronomie, etc.) et non limitÃ©s.</b>
 * </p>
 * <p>
 * L'accÃ¨s aux items et aux opinions qui leurs sont associÃ©es
 * est public. La crÃ©ation d'item et le dÃ©pÃ´t d'opinion nÃ©cessite en revanche 
 * que l'utilisateur crÃ©e son profil au prÃ©alable.
 * </p>
 * <p>
 * Lorsqu'une mÃ©thode peut lever deux types d'exception, et que les conditions sont rÃ©unies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levÃ©e.
 * </p>
 * <p>
 * Dans une version avancÃ©e (version 2), une opinion peut Ã©galement
 * Ãªtre Ã©valuÃ©e. Chaque membre se voit dans cette version dÃ©cerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a Ã©mises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuÃ©e Ã  un item
 * est pondÃ©rÃ© par le karma des membres qui les Ã©mettent.
 * </p>
 */

public class SocialNetwork {



	/**
	 * @uml.property  name="members"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="socialNetwork:avis.Member"
	 */
	private LinkedList<Member> members;
	/**
	 * @uml.property  name="items"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="socialNetwork:avis.Item"
	 */
	private LinkedList<Item> items;

	public SocialNetwork() {
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return members.size();
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		int n = 0;
		for (Item i : items){
			if(i instanceof ItemFilm)
				n++;
		}
		return n;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		int n = 0;
		for (Item i : items){
			if(i instanceof ItemBook)
				n++;
		}
		return n;
	}


	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se dÃ©finir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanciÃ© ou a moins de 4 caractÃ¨res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instanciÃ©.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de mÃªme pseudo dÃ©jÃ  prÃ©sent dans le <i>SocialNetwork</i> (mÃªme pseudo : indiffÃ©rent Ã   la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {
		//Test BadEntry
		if(Member.testBadEntry(pseudo, password, profil)) throw new BadEntry("Saisie incorrecte");
		//Test MemberAlreadyExists
		for(Member m : members){
			if(m.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) throw new MemberAlreadyExists();	
		}
		Member m1 = new Member(pseudo,password,profil);
		members.add(m1);
	}		



	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le rÃ©alisateur
	 * @param scenariste le scÃ©nariste
	 * @param duree sa durÃ©e en minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanciÃ© ou a moins de 4 caractÃ¨res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instanciÃ©. </li>
	 *  <li>  si le rÃ©alisateur n'est pas instanciÃ©. </li>
	 *  <li>  si le scÃ©nariste n'est pas instanciÃ©. </li>
	 *  <li>  si la durÃ©e n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de mÃªme titre  dÃ©jÃ  prÃ©sent (mÃªme titre : indiffÃ©rent Ã   la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//Tests BadEntry
		if(ItemFilm.testBadEntry(pseudo, password, titre, genre, realisateur, scenariste, duree)) throw new BadEntry("Saisie incorrecte");
		//Tests NotMember
		Member m = null;
		for(Member membreTest : members ) {
			//on teste si le membre existe
			if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
				m = membreTest;
			}
		}
		//test si le membre est prŽsent dans la liste
		if (m == null) throw new NotMember("Not member : Membre non prŽsent");
		//Test du password si le membre existe 
		if(!m.isPassword(password)) throw new NotMember("Password erronŽ");
		// si le membre est prŽsent, on continue
		else {
			//Test ItemFilmAlreadyExists
			for(Item i : items){
				if(i instanceof ItemFilm){
					if(((ItemFilm) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())) throw new ItemFilmAlreadyExists();
				}
			}
			Member auteur = null;
			for(Member membreTest : members ) {
				if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
					auteur = membreTest;
				}
			}
			ItemFilm film = new ItemFilm(auteur, titre, genre, realisateur, scenariste, duree);
			items.add(film);
		}
	}


	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanciÃ© ou a moins de 4 caractÃ¨res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instanciÃ©. </li>
	 *  <li>  si l'auteur n'est pas instanciÃ©. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de mÃªme titre  dÃ©jÃ  prÃ©sent (mÃªme titre : indiffÃ©rent Ã  la casse  et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{
		//Tests BadEntry
		if(ItemBook.testBadEntry(pseudo, password, titre, genre, auteur, nbPages)) throw new BadEntry("Saisie incorrecte");
		//Tests NotMember
		Member m = null;
		for(Member membreTest : members ) {
			//on teste si le membre existe
			if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
				m = membreTest;
			}
		}
		//test si le membre est prŽsent dans la liste
		if (m == null) throw new NotMember("Not member : Membre non prŽsent"); 
		if(!m.isPassword(password)) throw new NotMember("Password erronŽ");
		// si le membre est prŽsent, on continue
		else {
			//Test ItemFilmAlreadyExists
			for(Item i : items){
				if(i instanceof ItemBook){
					if(((ItemBook) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())) throw new ItemBookAlreadyExists();
				}
			}
			Member author = null;
			for(Member membreTest : members ) {
				if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
					author = membreTest;
				}
			}
			ItemBook livre = new ItemBook(author, titre, auteur, genre, nbPages);
			items.add(livre);
		}
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le nom n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces.  </li>
	 * 
	 * @return LinkedList <String> : la liste des reprÃ©sentations de tous les items ayant ce nom 
	 * Cette reprÃ©sentation contiendra la note de l'item s'il a Ã©tÃ© notÃ©.
	 * (une liste vide si aucun item ne correspond) 
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry {
		LinkedList<String> result = new LinkedList<String>();
		//Test BadEntry
		if(nom.trim().length()<1) throw new BadEntry("Nom incorrect");
		float note=0;
		for(Item i : items){
			if(i instanceof ItemFilm){
				if(((ItemFilm) i).getTitre().trim().toLowerCase().equals(nom.trim().toLowerCase())){
					note = i.getMoyenne();
					if(note>=0)
						result.add("Film trouvŽ : Titre : "+((ItemFilm) i).getTitre()+" genre : "+((ItemFilm) i).getGenre()+" note : "+note);
					else result.add("Film trouvŽ : Titre : "+((ItemFilm) i).getTitre()+" genre : "+((ItemFilm) i).getGenre());
				}
			}
			if(i instanceof ItemBook){
				if(((ItemBook) i).getTitre().trim().toLowerCase().equals(nom.trim().toLowerCase())){
					note = i.getMoyenne();
					if(note>=0)
						result.add("Livre trouvŽ : Titre : "+((ItemBook) i).getTitre()+" genre : "+((ItemFilm) i).getGenre()+"note : "+note);
					else result.add("Livre trouvŽ : Titre : "+((ItemBook) i).getTitre()+" genre : "+((ItemFilm) i).getGenre());
				}
			}
		}
		return result;
	}



	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce film  prÃ©existe, elle est mise Ã  jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre Ã©mettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concernÃ©
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanciÃ© ou a moins de 4 caractÃ¨res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instanciÃ©. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		//Test BadEntry
		if(Review.testBadEntry(pseudo, password, titre, note, commentaire)) throw new BadEntry("Param�tres incorrects");
		//Tests NotMember
		Member m = null;
		for(Member membreTest : members ) {
			//on teste si le membre existe
			if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
				m = membreTest;
			}
		}
		//test si le membre est prŽsent dans la liste
		if (m == null) throw new NotMember("Not member : Membre non prŽsent"); 
		if(!m.isPassword(password)) throw new NotMember("Password erronŽ");
		//Test NotItem
		Item j = null;
		for(Item i : items){
			if(i instanceof ItemFilm){
				if(((ItemFilm) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())){
					j = i;
				}
			}
		}
		if(j==null) throw new NotItem("Pas d'item correspondant");
		
		//AJOUT D'UNE REVIEW
		for(Item reviewedFilm : items){//on balaye la liste des items presents dans social network
			if(reviewedFilm instanceof ItemFilm){//on ne releve que les items de type itemFilm
				if(m==((ItemFilm)reviewedFilm).author && (((ItemFilm) reviewedFilm).equals(j))){// on verifie que m est l'auteur du review du film j
					if(((ItemFilm) reviewedFilm).reviews.contains(m)){// si la liste reviews contient le film...
						int memberCommentPosition = ((ItemFilm) reviewedFilm).reviews.indexOf(m);// on recupere l'index du commentaire de m dans la liste
						((ItemFilm) reviewedFilm).reviews.set(memberCommentPosition, new Review(m,note,commentaire));// on remplace le commentaires et note de j par les nouveaux
					}
					else{					
						Review newReview = new Review(m,note,commentaire);// creation d'un nouveau review
						((ItemFilm) reviewedFilm).addReview(newReview);// ajout de la nouvelle review.
					}	
				}		
			}
		}	
		for(Item i : items){//on balaye la liste des items presents dans social network
			if(i instanceof ItemFilm){//on ne releve que les items de type itemFilm
				if(((ItemFilm) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())){//on filtre  les films dont le titre correspond
					note = i.getMoyenne();// on stock la moyenne dans note
					if(note>=0.0f)// si note inferieure...
						note = 0.0f;// note est nulle
				}
			}
		}
		return note; // retourne note
	}
	
	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  prÃ©existe, elle est mise Ã  jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre Ã©mettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concernÃ©
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instanciÃ© ou a moins de 4 caractÃ¨res autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instanciÃ© ou a moins de 1 caractÃ¨re autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instanciÃ©. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
			//Test BadEntry
			if(Review.testBadEntry(pseudo, password, titre, note, commentaire)) throw new BadEntry("Param�tres incorrects");
			//Tests NotMember
			Member m = null;
			for(Member membreTest : members ) {
				//on teste si le membre existe
				if (membreTest.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) {
					m = membreTest;
				}
			}
			//test si le membre est prŽsent dans la liste
			if (m == null) throw new NotMember("Not member : Membre non prŽsent"); 
			if(!m.isPassword(password)) throw new NotMember("Password erronŽ");
			//Test NotItem
			Item j = null;
			for(Item i : items){
				if(i instanceof ItemBook){
					if(((ItemBook) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())){
						j = i;
					}
				}
			}
			if(j==null) throw new NotItem("Pas d'item correspondant");
			
			//AJOUT D'UNE REVIEW
			for(Item reviewedBook : items){//on balaye la liste des items presents dans social network
				if(reviewedBook instanceof ItemBook){//on ne releve que les items de type itemFilm
					if(m==((ItemBook)reviewedBook).author && (((ItemBook) reviewedBook).equals(j))){// on verifie que m est l'auteur du review du film j
						if(((ItemBook) reviewedBook).reviews.contains(m)){// si la liste reviews contient le film...
							int memberCommentPosition = ((ItemBook) reviewedBook).reviews.indexOf(m);// on recupere l'index du commentaire de m dans la liste
							((ItemBook) reviewedBook).reviews.set(memberCommentPosition, new Review(m,note,commentaire));// on remplace le commentaires et note de j par les nouveaux
						}
						else{					
							Review newReview = new Review(m,note,commentaire);// creation d'un nouveau review
							((ItemFilm) reviewedBook).addReview(newReview);// ajout de la nouvelle review.
						}	
					}		
				}
			}	
			for(Item i : items){//on balaye la liste des items presents dans social network
				if(i instanceof ItemBook){//on ne releve que les items de type itemFilm
					if(((ItemBook) i).getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase())){//on filtre  les films dont le titre correspond
						note = i.getMoyenne();// on stock la moyenne dans note
						if(note>=0.0f)// si note inferieure...
							note = 0.0f;// note est nulle
					}
				}
			}
			return note; // retourne note
		}

	/**
	 * Obtenir une reprÃ©sentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaÃ®ne de caractÃ¨res reprÃ©sentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		return "";
	}


}
