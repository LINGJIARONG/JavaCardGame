package project.card.cardSaboAndPuzzle.cardSaboteur;

public class FixTool1 extends CardAction{

	/**
	 * the nature of the Fix Tools. We number them as 1,2,3.
	 * In this case, we decide that Fix Tool n°1 can fix Broken Tool n°1, Fix Tool n°2 can fix Broken Tool n°2, etc
	 */
	int nature1;
	public int getNature1() {
		return nature1;
	}
	public FixTool1(int n) {
		this.nature1=n;
	}
	
	
	/**
	 * to see if we can fix the path. 
	 * @param bt the broken tool that blocks the path
	 * @return true if the nature of the fix tool is the same as the broken tool.
	 * 		   false if not.
	 */
	public boolean canFix(BrokenTools bt) {
		if (this.nature1==bt.nature) return true;
		else return false;
	}
	
	public String toString() {
		String s="FixTool :";
		switch(nature1) {
		case 1: s+="fix chariot ";break;
		case 2: s+= "fix hamber";break;
		case 3: s+= "fix lantern";break;
		}
		return s ;
	}
	public String getAddr() {
		return super.getAddr()+"f"+nature1+".png";
	}
	
}
