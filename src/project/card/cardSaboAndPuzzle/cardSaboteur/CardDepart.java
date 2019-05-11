package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Path;
/**
 *  Carte de depart
 *
 */
public class CardDepart extends CardPath {
	public CardDepart() {
		super(1,1,1,1);
	}
	public String getAddr() {
		return Path.projectPath+"src/images/departure"+".png";
	}

}
