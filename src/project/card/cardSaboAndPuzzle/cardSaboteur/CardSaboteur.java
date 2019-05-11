package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Path;
import project.card.cardSaboAndPuzzle.CardPS;
/**

 * We define the nature and  properties of a Saboteur card
 *  (Totally, there are 44 Path Cards, 27 Action Cards, 28 Gold Nugget Cards, 7 Gold Miners and 4 Saboteurs).
 *   @author LING Jiarong and WEI Fengyi
 */

public class CardSaboteur extends CardPS{


	public CardSaboteur() {
		super();
	}
	public CardSaboteur(int up,int down,int left,int right) {
		super(up,down,left,right);
	}
	public String getAddr() {
		return Path.projectPath+"src/images/";
	}


	




}
