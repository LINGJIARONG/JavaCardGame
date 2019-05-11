package project.game;

import project.board.PuzzleBoard;
import project.card.cardSaboAndPuzzle.cardPuzzle.CardPuzzle;
import project.player.PlayerPuzzle;
/**
 * Faire Communiquer des classes , definir des réponse par rapport aux entrés
 * @author LING Jiarong WEI Fengyi
 * 
 *
 */
public class Puzzle extends Game{

	private PuzzleBoard pboard;
	private PlayerPuzzle pplayer;
	
	public Puzzle() {
		this.pplayer=new PlayerPuzzle();
		this.pboard=new PuzzleBoard();
	}
	
	
	public PuzzleBoard getPboard() {
		return pboard;
	}
	public PlayerPuzzle getPplayer() {
		return pplayer;
	}

	public void setPboard(PuzzleBoard pboard) {
		this.pboard = pboard;
	}

	public void setPplayer(PlayerPuzzle pplayer) {
		this.pplayer = pplayer;
	}

	public void play() {
		pplayer.scanNom();
		pboard.initialisizeCard();
		while(true) {
			System.out.println(pboard.toString());
			System.out.println(pboard.toStringHand());
			int action=pplayer.scanAction();
			if(action==1) {
				System.out.println("action = input card ");

				int card=pplayer.scanCard();
				while(card>=pboard.getHandCardP().size()||card<0) {
					System.out.println("invalide index,try again");
					card=pplayer.scanCard();
				}
				CardPuzzle carte=(CardPuzzle) pboard.getHandCardP().get(card);
				int posX=pplayer.scanPositionX();
				int posY=pplayer.scanPositionY();
				if(pboard.putCard(carte, posX, posY)) {
					if(pboard.getHandCardP().isEmpty()) {
						System.out.println(pboard.toString());
						System.out.println(pboard.toStringHand());
						System.out.println(pplayer.getName()+"YOU Win!!!!!!!!!!!!!!!So brilliant!");
						System.exit(0);
					}
				}else {
					System.out.println("invalide action.");
				}
			}else if(action==0) {
				System.out.println("action = remove card ");

				int posX=pplayer.scanPositionX();
				int posY=pplayer.scanPositionY();
				if(pboard.removeCard(posX, posY)) {
					System.out.println(pboard.toString());
					System.out.println(pboard.toStringHand());
				}else {
					System.out.println("invalide action.");
				}
			}else {
				System.out.println("invalide action.");
			}
		}
	}
	
	
}
