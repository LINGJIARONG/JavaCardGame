package project.board;

import java.util.Collections;
import java.util.List;

import project.card.Card;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardPath;

public class Tools {
	/**
	 * 
	 * @param list
	 * @param handCard
	 * @param nbCard
	 */
	@SuppressWarnings("unchecked")
	public static void distributeCards(List<? extends Card> list, @SuppressWarnings("rawtypes") List[] handCard,int nbCard) {
		Collections.shuffle(list);
		for(int i=0;i<handCard.length;i++) {
			for(int j=0;j<nbCard;j++) {
				Card e=(Card) list.remove(0);
				handCard[i].add(e);
			}
		}
	}
	/**
	 *  to duplicate a card for nb times for convience
	 * @param list Cardset
	 * @param c  CardPath to be  duplicated 
	 * @param nb times to be duplicated 
	 */
	public static void fillCardsSabo(List< Card> list , CardPath c,int nb ) {
		list.add(c);
		for(int i=1;i<nb;i++) {
			list.add(new CardPath(c));
		}
	}
}
