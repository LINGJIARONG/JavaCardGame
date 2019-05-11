package project.card.cardSaboAndPuzzle.cardSaboteur;

public class Map extends CardAction{

	public String toString () {
		return "Map Card";
	}
	public String getAddr() {
		return super.getAddr()+"map"+".png";
	}
}
