package project.card.cardDominoes;

import project.card.Card;
import project.card.Path;

/**
 * 
 * @author LING Jiarong & WEI Fengyi
 * a card for games Domino's 
 *
 */
public class CardDomiClassic extends Card {
	
	public CardDomiClassic(int up,int down) {
		super(up,down);
	}
	
	public boolean upEqualsDown() {
		return this.up==this.down;
	}
	
	public boolean canBeUpToUp(CardDomiClassic c) {
		return c.upIsAvailable&&c.up==this.up;
	}
	
	public boolean canBeDownToUp(CardDomiClassic c) {
		return c.upIsAvailable&&c.up==this.down;
	}
	
	
	public boolean canBeNextToUp(CardDomiClassic c) {
		return canBeUpToUp(c)||canBeDownToUp(c);
	}
	
	public boolean canBeDownToDown(CardDomiClassic c) {
		return c.downIsAvailable&&c.down==this.down;
	}
	
	public boolean canBeUpToDown(CardDomiClassic c) {
		return c.downIsAvailable&&c.down==this.up;
	}
	
	public boolean canBeNextToDown(CardDomiClassic c) {
		return canBeDownToDown(c)||canBeUpToDown(c);
	}
	
	
	public boolean canBeNextTo(CardDomiClassic c) {
		return canBeNextToUp(c)||canBeNextToDown(c);
	}
	
	/**
	 * changer la direction comme l'opposition de c 
	 * @param c
	 */
	public void turnDirection(CardDomiClassic c) {
		if(c.upEqualsDown()!=this.upEqualsDown()) {
			this.upDirection=c.upDirection;
			spinClockwise90(this);
		}
		else {
			this.upDirection=c.upDirection;
			spinClockwise180(this);
		}
	}
	

	/**
	 * changer la direction comme c 
	 * @param c
	 */
	public void sameDirection(CardDomiClassic c) {
		if(c.upEqualsDown()!=this.upEqualsDown()) {
			this.upDirection=c.upDirection;
			spinClockwise90(this);
		}
		else {
			this.upDirection=c.upDirection;
		}
	}
	
	
	public String toString() {
		switch(this.upDirection) {
		case 1:return this.up+"|"+this.down;
		case 2:return this.down+"-"+this.up;
		case -1:return this.down+"|"+this.up;
		case -2:return this.up+"-"+this.down;
		default : return "";
		}
		
	}
	/**
	 * 
	 * @return adress d'image 
	 */
	public String getAdresse() {
		return Path.projectPath+"src/images/peca"+up+""+down+".png";
	}

	

}
