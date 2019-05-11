package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Path;

public class FixTool2 extends FixTool1{

	/**
	 * some Fix Tools have a second choice, here we define their second choice. 
	 */
	private int nature2;
	private int keep1;
	private int keep2;
	
	public FixTool2(int n1, int n2) {
		super(n1);
		if (n1==n2) {
			throw new IllegalArgumentException();
		}else {
			this.nature2=n2;
		}
	}	
	
	/**
	 * to see which nature does the player want to use
	 * @param choice the number of the nature that the player wants.
	 */
	public void choice(int choice) {
		if(choice!=this.nature1&&choice!=this.nature2) throw new IllegalArgumentException();
		else {
			keep1=nature1;
			keep2=nature2;
			this.nature1=choice;
			this.nature2=0;
		}
	}
	public void redo() {
		nature1=keep1;
		nature2=keep2;
	}
	public String toString() {
		String s=super.toString();
		switch(nature2) {
		case 1: s+="+ fix chariot ";break;
		case 2: s+= "+ fix hamber";break;
		case 3: s+= "+ fix lantern";break;
		}
		return s ;
	}
	public int getNature2() {
		return nature2;
	}

	public String getAddr() {
		return Path.projectPath+"src/images/F"+nature1+nature2+".png";
	}
}
