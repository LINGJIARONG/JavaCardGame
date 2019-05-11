package project.card.cardSaboAndPuzzle.cardSaboteur;

public class CardGold extends CardSaboteur{

	/**
	 * the number of the gold on the gold card.
	 */
	int nbgold;
	
	public CardGold(int nb) {
		super();
		this.nbgold=nb;
	}
	public String getAddr() {
		return super.getAddr()+"gold.png";
	}
}
