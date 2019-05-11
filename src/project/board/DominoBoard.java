package project.board;

import java.util.LinkedList;

import project.card.Card;
import project.card.cardDominoes.CardDomiClassic;
/**
 * 
 * Le bord de premier jeu ,<br />
 * Définir des règles
 * 
 * @author LING Jiarong WEI Fengyi
 * 
 *
 */
public class DominoBoard extends Board{

	public DominoBoard(int nb) {
		super(nb);
	}

	/**
	 * initialiser le talon (caedSet),et distribuer les cartes a chaque joueur 
	 */
	public void initialisizeCard() {
		this.cardSet=new LinkedList<Card>();
		for(int i=0;i<=6;i++) {
			for(int j=0;j<=i;j++) {
				this.cardSet.add(new CardDomiClassic(j,i));
			}
		}
		Tools.distributeCards(this.cardSet, handCard, 5);
	}

	/**
	 * 
	 * @param c carte a placer 
	 * @return true s'il peut être déplacer au début
	 */

	public boolean canPutCardToBegin(CardDomiClassic c) {
		if(this.table.isEmpty())
			return true;
		else if(this.table.size()==1) {
			CardDomiClassic cFirst=(CardDomiClassic) this.table.getFirst();
			return (c.canBeNextToUp(cFirst)&&(cFirst.upEqualsDown()||cFirst.getUpDirection()==-2))
					||(c.canBeNextToDown(cFirst)&&(cFirst.upEqualsDown()||cFirst.getUpDirection()==2));
		}
		else {
			CardDomiClassic cFirst=(CardDomiClassic) this.table.getFirst();
			return c.canBeNextTo(cFirst);
		}

	}
	/**
	 * 
	 * @param c carte a placer 
	 * @return true s'il peut être déplacer q lq fin
	 */
	public boolean canPutCardToEnd(CardDomiClassic c) {
		if(this.table.isEmpty())
			return true;
		else if(this.table.size()==1) {
			CardDomiClassic cLast=(CardDomiClassic) this.table.getLast();
			return (cLast.upEqualsDown()&&c.canBeNextTo(cLast))
					||(c.canBeNextToUp(cLast)&&cLast.getUpDirection()==2)
					|| (c.canBeNextToDown(cLast)&&cLast.getUpDirection()==-2);
		}
		else {
			CardDomiClassic cLast=(CardDomiClassic) this.table.getLast();
			return c.canBeNextTo(cLast);
		} 
	}
	/**
	 * 
	 * @param c carte a placer au Debut 
	 * @return true si on reussit a placer c au debut 
	 */
	public boolean putCardToBegin(CardDomiClassic c) {
		CardDomiClassic cFirst=(CardDomiClassic) this.table.getFirst();
		if(canPutCardToBegin(c)) {
			if(cFirst.upEqualsDown()) {
				int val=cFirst.getUp();
				if(c.getUp()==val) {
					c.setUpDirection(2);
					c.topConnected();
				}
				else {
					c.setUpDirection(-2);
					c.downConnected();
				}
			}
			else if(c.canBeNextToUp(cFirst)) {
				if(c.canBeUpToUp(cFirst)) {
					c.turnDirection(cFirst);
					c.topConnected();
				}
				else {
					c.sameDirection(cFirst);
					c.downConnected();
				}
				cFirst.topConnected();
			}
			else {
				if(c.canBeUpToDown(cFirst)) {
					c.sameDirection(cFirst);
					c.topConnected();
				}
				else {
					c.turnDirection(cFirst);
					c.downConnected();
				}
				cFirst.downConnected();
			}
			this.table.addFirst(c); 
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param c carte à placer à la fin 
	 * @return true si on reussit a placer c à la fin 
	 */
	public boolean putCardToEnd(CardDomiClassic c) {
		CardDomiClassic cLast=(CardDomiClassic) this.table.getLast();
		if(canPutCardToEnd(c)) {
			if(cLast.upEqualsDown()) {
				int val=cLast.getUp();
				if(c.getUp()==val) {
					c.setUpDirection(-2);
					c.topConnected();
				}
				else {
					c.setUpDirection(2);
					c.downConnected();
				}
			}

			else if(c.canBeNextToUp(cLast)) {
				if(c.canBeUpToUp(cLast)) {
					c.turnDirection(cLast);
					c.topConnected();}
				else {
					c.sameDirection(cLast);
					c.downConnected();
				}
				cLast.topConnected();
			}
			else {
				if(c.canBeUpToDown(cLast)) {
					c.sameDirection(cLast);
					c.topConnected();
				}
				else {
					c.turnDirection(cLast);
					c.downConnected();
				}
				cLast.downConnected();
			}

			this.table.addLast(c);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param nPlayer joueur (type int  )
	 * @param c la carte 
	 * @param position 0->au debut 1-> a la fin
	 * @return true si on a depacé sans probleme
	 */
	public boolean putCards(int nPlayer,CardDomiClassic c,int position) {
		if(this.table.isEmpty()) {
			if(c.upEqualsDown())
				c.setUpDirection(1);
			this.table.add(c);
			this.handCard[nPlayer].remove(c);
			return true;
		}
		if(position==0) {
			if(putCardToBegin(c)) {
				this.handCard[nPlayer].remove(c);
				return true;
			}
			else
				return false;
		}
		else {
			if(putCardToEnd(c)) {
				this.handCard[nPlayer].remove(c);
				return true;
			}
			else
				return false;
		}

	}
	/**
	 * `
	 * @param nPlayer numero de joueur 
	 * @return true s'il est bloqué
	 */
	public boolean block(int nPlayer) {
		for(Card c:handCard[nPlayer]) {
			if(canPutCardToBegin((CardDomiClassic) c)||canPutCardToEnd((CardDomiClassic) c))
				return false;
		}
		return true;
	}

	/**
	 * prendre une carte de defausse 
	 * @param Player numero de joueur 
	 * @return true si on  'draw'
	 */
	public boolean draw(int Player) {
		if(block(Player)&&(!this.cardSet.isEmpty())) {
			CardDomiClassic e=(CardDomiClassic) this.cardSet.remove(0);
			this.handCard[Player].add(e);
			return true;
		}
		else 
			return false;
	}
	/**
	 * skip , on on fait rien 
	 * @param Player numero de joueur 
	 */
	public void skip(int Player) {
	}








}