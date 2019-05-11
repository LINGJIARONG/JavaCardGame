package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Path;

public class CardGoalGold extends CardGoal{
	public CardGoalGold() {
		super();
	}
	public CardGoalGold(int u,int d,int l,int r){
		super(u,d,l,r);
		this.setVisible(false);
	}
	
	public String toString() {
		return "Gold card";
	}
	public String getAddr() {
		if(isVisible())
			return Path.projectPath+"src/images/win"+".png";
		return Path.projectPath+"src/images/winCache"+".png";

	}
}
