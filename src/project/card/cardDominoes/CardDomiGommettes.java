package project.card.cardDominoes;

import project.card.Path;

public class CardDomiGommettes extends CardDomiClassic {
	Coulor upColor;
	Coulor downColor;
	
	/**
	 * constructeur 
	 * @param u 0-6, chaque nombre represente un charactère
	 * @param d 0-6, chaque nombre represente un charactère
	 * @param cu la couleur de top
	 * @param cd la couleur de buttom
	 */
	public CardDomiGommettes (int u, int d, Coulor cu, Coulor cd) {
		super(u,d);
		this.upColor=cu;
		this.downColor=cd;
	}

	public Coulor getUpColor() {
		return upColor;
	}

	public Coulor getDownColor() {
		return downColor;
	}
	

	public void setUpColor(Coulor upColor) {
		this.upColor = upColor;
	}

	public void setDownColor(Coulor downColor) {
		this.downColor = downColor;
	}
	
	public boolean upEqualsDown() {
		return this.up==this.down&&this.upColor==this.downColor;
	}
	
	public boolean canBeUpToUp(CardDomiClassic c) {
			return super.canBeUpToUp(c)||
					(c.isUpIsAvailable()&&((CardDomiGommettes)c).getUpColor()==this.getUpColor());}
	
	public boolean canBeDownToUp(CardDomiClassic c) {
			return super.canBeDownToUp(c)||
					(c.isUpIsAvailable()&&((CardDomiGommettes)c).getUpColor()==this.getDownColor());}
	
	public boolean canBeDownToDown(CardDomiClassic c) {
			return super.canBeDownToDown(c)||
					(c.isDownIsAvailable()&&((CardDomiGommettes)c).getDownColor()==this.getDownColor());}
	
	public boolean canBeUpToDown(CardDomiClassic c) {
			return super.canBeUpToDown(c)||
					(c.isDownIsAvailable()&&((CardDomiGommettes)c).getDownColor()==this.getUpColor());}
		
	

	private char changeToChar(int i) {
		if(i==0) return 'o';
		else if (i==1) return '*';
		else if(i==2) return '^';
		else if(i==3) return '§';
		else if(i==4) return '&';
		else if(i==5) return '£';
		else return '!';
	}
	
	
	public String toString() {
		switch(this.upDirection) {
		case 1:return changeToChar(this.up)+" "+this.upColor+"|"+changeToChar(this.down)+" "+this.downColor;
		case 2:return changeToChar(this.down)+" "+this.downColor+"-"+changeToChar(this.up)+" "+this.upColor;
		case -1:return changeToChar(this.down)+" "+this.downColor+"|"+changeToChar(this.up)+" "+this.upColor;
		case -2:return changeToChar(this.up)+" "+this.upColor+"-"+changeToChar(this.down)+" "+this.downColor;
		default : return "";
		}
	}

	public String getAdresse() {
		String s=Path.projectPath+"src/images/gmt"+up+""+down;
		s+="";
		return s+".png";
	}
}
