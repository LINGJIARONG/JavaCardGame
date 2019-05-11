package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Path;

public class CardGoalRock extends CardGoal{

	public CardGoalRock() {
		super();
	}
	public CardGoalRock(int u,int d,int l,int r){
		super(u,d,l,r);
		
	}
	public String toString() {
		return "Rock!";
	}
	public String getAddr() {
		if(isVisible())
			return Path.projectPath+"src/images/rock"+up+down+left+right+".png";
		return Path.projectPath+"src/images/winCache"+".png";
	}
}
