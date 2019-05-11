package project.card.cardSaboAndPuzzle.cardSaboteur;
/**
 * 
 * BrokenTools
 */
public class BrokenTools extends CardAction{
	protected int nature;
	public int getNature() {
		return nature;
	}

	public BrokenTools(int i) {
		this.nature=i;	
	}

	/**
	 * the nature of the card Broken Tools. there are three types of Broken Tools and we number them as 1,2,3.
	 * 1-> cartes chariot brisé   
	 * 2-> cartes pioche brisée
	 * 3-> cartes lanterne brisées 
	 */

	public String toString() {
		String s="BrokenCard :";
		switch(nature) {
		case 1: s+="broken chariot ";break;
		case 2: s+= "broken hamber ";break;
		case 3: s+= "broken lantern ";break;
		}
		return s ;
	}
	public String getAddr() {
		return super.getAddr()+""+nature+".png";
	}


}
