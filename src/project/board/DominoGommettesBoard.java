 package project.board;

import java.util.LinkedList;

import project.card.Card;
import project.card.cardDominoes.CardDomiClassic;
import project.card.cardDominoes.CardDomiGommettes;
import project.card.cardDominoes.Coulor;
/**
  * Bord pour Gommettes 
 * @author LING Jiarong 
 */
public class DominoGommettesBoard extends DominoBoard{

	public DominoGommettesBoard(int nb) {
		super(nb);
	}


	public void initialisizeCardClassic() {
		this.cardSet=new LinkedList<Card>();
		this.cardSet.add(new CardDomiGommettes(1,1,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(1,2,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(1,3,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(1,4,Coulor.BLUE,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(1,5,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(1,6,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(1,0,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(2,2,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(3,2,Coulor.GREEN,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(4,2,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(5,2,Coulor.BLUE,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(6,2,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(0,2,Coulor.BLUE,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(3,3,Coulor.GREEN,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(3,4,Coulor.RED,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(3,5,Coulor.BLUE,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(3,6,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(3,0,Coulor.BLUE,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(4,4,Coulor.RED,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(4,5,Coulor.GREEN,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(4,6,Coulor.GREEN,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(4,0,Coulor.GREEN,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(5,5,Coulor.BLUE,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(5,6,Coulor.GREEN,Coulor.BLUE));
		this.cardSet.add(new CardDomiGommettes(5,0,Coulor.RED,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(6,6,Coulor.BLUE,Coulor.RED));
		this.cardSet.add(new CardDomiGommettes(6,0,Coulor.BLUE,Coulor.GREEN));
		this.cardSet.add(new CardDomiGommettes(0,0,Coulor.GREEN,Coulor.RED));
		Tools.distributeCards(this.cardSet, handCard, 5);
	}
	
	
	/**
	 * 
	 * @param c carte a placer
	 * @return true si c peut être placé au Debut
	 */
	public boolean putCardToBegin(CardDomiClassic c) {
		CardDomiGommettes cFirst=(CardDomiGommettes) this.table.getFirst();
		//
		if(canPutCardToBegin(c)) {
			if(cFirst.upEqualsDown()) {
				int val=cFirst.getUp();
				Coulor color=cFirst.getUpColor();
				if(c.getUp()==val||((CardDomiGommettes)c).getUpColor()==color) {
					c.setUpDirection(2);
					c.topConnected();
				}
				else {
					c.setUpDirection(-2);
					c.downConnected();
				}
			}
			else if(c.canBeNextToUp(cFirst)&&cFirst.getUpDirection()==-2) {
				if(c.canBeUpToUp(cFirst)) {
					System.out.println("uptoup");
					c.turnDirection(cFirst);
					c.topConnected();

				}
				else {
					System.out.println("downtoup");
					c.sameDirection(cFirst);
					c.downConnected();

				}
				cFirst.topConnected();
				System.out.println("c.updirection"+c.getUpDirection()+"first.direction"+cFirst.getUpDirection());

			}
			else {
				if(c.canBeUpToDown(cFirst)) {
					System.out.println("uptodown");

					c.sameDirection(cFirst);
					c.topConnected();
				}
				else {
					System.out.println("downtodown");
					c.turnDirection(cFirst);
					c.downConnected();
				}
				cFirst.downConnected();
				System.out.println("c.updirection"+c.getUpDirection()+"first.direction"+cFirst.getUpDirection());

			}
			this.table.addFirst(c); 
			return true;
		}
		return false;
	}
	
	public boolean putCardToEnd(CardDomiClassic c1) {
		CardDomiGommettes cLast=(CardDomiGommettes) this.table.getLast();
		CardDomiGommettes c=(CardDomiGommettes)c1;
		if(canPutCardToEnd(c)) {
			if(cLast.upEqualsDown()) {
				if(c.canBeUpToUp(cLast)||c.canBeUpToDown(cLast)) {
					c.setUpDirection(-2);
					c.topConnected();
				}
				else {
					c.setUpDirection(2);
					c.downConnected();
				}
			}
			else if(c.canBeNextToUp(cLast)&&cLast.getUpDirection()==2) {
				if(c.canBeUpToUp(cLast)) {
					c.turnDirection(cLast);
					c.topConnected();
					System.out.println("up-up\n"+"c.updirection"+c.getUpDirection()+"last.direction"+cLast.getUpDirection());
				}
				else {
					c.sameDirection(cLast);
					c.downConnected();
					System.out.println("down-up\n"+"c.updirection"+c.getUpDirection()+"last.direction"+cLast.getUpDirection());
				}
				cLast.topConnected();
			}
			else {
				if(c.canBeUpToDown(cLast)) {
					c.sameDirection(cLast);
					c.topConnected();
					System.out.println("up-down\n"+"c.updirection"+c.getUpDirection()+"last.direction"+cLast.getUpDirection());

				}
				else {
					c.turnDirection(cLast);
					c.downConnected();
					System.out.println("down-down\n"+"c.updirection"+c.getUpDirection()+"last.direction"+cLast.getUpDirection());

				}
				cLast.downConnected();
			}

			this.table.addLast(c);
			return true;
		}
		return false;
	}

}




















