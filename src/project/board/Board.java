package project.board;
import project.card.Card;

import java.util.LinkedList;

/**
 *  
 *  Definir des ensembles de cartes 
 *  
 *   
 */

public abstract class Board {
	/**
	 * Defausse une liste de carte 
	 */
	protected LinkedList< Card> cardSet;
	/**
	 * la table 
	 */
	protected LinkedList<Card> table;
	/**
	 * Des cartes a la main
	 */
	protected LinkedList<Card>[] handCard;
	/**
	 * Créer / initialiser des carte
	 */
	public abstract void initialisizeCard() ;

	public LinkedList<Card> getCardSet() {
		return this.cardSet;
	}
	public LinkedList<Card> getTable() {
		return  this.table;
	}
	public LinkedList<Card>[] getHandCard() {
		return this.handCard;
	}

	/**
	 * 
	 * @param nb nombre de joueurs 
	 */
	@SuppressWarnings("unchecked")
	public Board(int nb) {
		this.table=new LinkedList<Card>();
		this.handCard=new LinkedList[nb];
		for(int i=0;i<nb;i++) {
			this.handCard[i]=new LinkedList<Card>();
		}
	}
	/**
	 *  changer les position de i et j
	 * @param i position dans handcard
	 * @param j position dans handcard
	 */
	public void changeCards(int i,int j) {
		LinkedList<Card> l=handCard[i];
		handCard[i]=handCard[j];
		handCard[j]= l;
	}

	public boolean setIsEmpty() {
		return cardSet.isEmpty();
	}

}