package project.game;

import project.board.DominoBoard;
import project.card.cardDominoes.CardDomiClassic;
import project.player.PlayerDominoClassic;
/**
 * Faire Communiquer des classes , definir des réponse par rapport aux entrés
 * @author LING Jiarong WEI Fengyi
 * 
 *
 */
public class DominoClassic extends Game {
	public PlayerDominoClassic[] getPlayers() {
		return players;
	}
	public DominoBoard getDb() {
		return db;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
	/**
	 * pour scanner le nombre de joueurs
	 */
	private PlayerDominoClassic p;
	/**
	 * tableau de joueurs
	 */
	private PlayerDominoClassic[] players; 
	/**
	 * le Bord de ce jeu
	 */
	private DominoBoard db;
	/**
	 * le nombre de joueur
	 */
	private int numPlayers;

	public DominoClassic(){
		
	}
	
	public DominoClassic(int n){
		p=new PlayerDominoClassic();
		numPlayers=n;
		players=new PlayerDominoClassic[numPlayers];
		db=new DominoBoard(numPlayers);
		db.initialisizeCard();
		setPosition();

	}
	/**
	 * On commence par le joueur qui a une carte de max valeur.
	 * On l'implément par changer la position de joueur dans la tablequ
	 * 
	 */
	private void setPosition() {
		int pos=0;
		CardDomiClassic cardMax=(CardDomiClassic) db.getHandCard()[0].get(0);
		for(int i=0;i<numPlayers;i++) {
			for(int j=0;j<db.getHandCard()[i].size();j++) {
				CardDomiClassic card2=(CardDomiClassic) db.getHandCard()[i].get(j);
				if(cardMax.getUp()+cardMax.getDown()<card2.getDown()+card2.getUp()) {
					cardMax=card2;
					pos=i;
				}
			}  
		}
		PlayerDominoClassic ptem=new PlayerDominoClassic();
		ptem=players[0];
		players[0]=players[pos];
		players[pos]=ptem;
		db.changeCards(0, pos);
	}
	
	public void playGame() {
		db.initialisizeCard();
		setPosition();
	}
	/**
	 * On lance le jeu >> dans le console
	 */
	public void play() {
		p=new PlayerDominoClassic();
		System.out.println("Maximun 5 players");
		numPlayers=p.scanNumber();
		players=new PlayerDominoClassic[numPlayers];
		db=new DominoBoard(numPlayers);
		for(int i=0;i<numPlayers;i++) {
			players[i]=new PlayerDominoClassic();
			players[i].scanNom();
		}
		db.initialisizeCard();
		setPosition();
		System.out.println("you have the biggest card; so you can begin");

		while(true) {
			for(int i=0;i<numPlayers;i++) {
				System.out.println();
				System.out.println(players[i].getName()+" : "+db.getHandCard()[i]);
				System.out.println("table:"+db.getTable());
				//Not blocked
				if(!db.block(i)) {
					int card=players[i].scanCard();
					while(card>=db.getHandCard()[i].size()||card<0) {
						System.out.println("invalide index,try again");
						card=players[i].scanCard();
					}
					CardDomiClassic carte=(CardDomiClassic) db.getHandCard()[i].get(card);
					int pos=players[i].scanPosition();
					if(db.putCards(i,carte, pos)) {
						System.out.println(players[i].getName()+" : "+db.getHandCard()[i]);
						System.out.println("table:"+db.getTable());
						if(db.getHandCard()[i].isEmpty()) {//gagné!
							System.out.println(players[i].getName()+"YOU Win!!!!!!!!!!!!!!!So brilliant!");
							System.exit(0);
						}
					}else {
						System.out.println("invalide action,choose your card again");
						i--;
					}
				}
				// blocked
				else {
					if(db.setIsEmpty()) {
						String s=p.scanActionSkip();
						if(s.equals("s")) {
							db.skip(i);
						}
					}
					else {
						String s=p.scanActionDraw();
						if (s.equals("d")) {
							boolean b=db.draw(i);
							if(b) {
								i--;
							}
							else
								System.out.println("No cards any more, pls skip");
						}
					}
				}
			} 
		}
	}
}