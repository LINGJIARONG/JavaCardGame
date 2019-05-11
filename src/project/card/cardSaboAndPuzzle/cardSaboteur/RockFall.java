package project.card.cardSaboAndPuzzle.cardSaboteur;

public class RockFall extends CardAction{

	/**
	 * to take one path out of the maze of the pathways.
	 * @param p the path card the player chooses.
	 */
	public void movePath(CardPath p) {
		if(!(p instanceof CardPath)) throw new IllegalArgumentException();
	}
	public String toString() {
		return "Rockfall Card";
	}
	public String getAddr() {
		return super.getAddr()+"rockFall"+".png";
	}
}
