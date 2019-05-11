package project.game;

import project.board.DominoGommettesBoard;
import project.card.cardDominoes.CardDomiClassic;
import project.card.cardDominoes.CardDomiGommettes;
import project.player.PlayerDominoClassic;

/**
 * Faire Communiquer des classes , definir des réponse par rapport aux entrés
 * @author LING Jiarong WEI Fengyi
 * 
 *
 */
public class DominoGommettes extends DominoClassic{
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
	
	private DominoGommettesBoard db;
	/**
	 * le nombre de joueur
	 */
	private int numPlayers;
	

	
	
	public DominoGommettes(){
		
	}
	
	public DominoGommettes(int n){
		numPlayers=n;
		players=new PlayerDominoClassic[numPlayers];
		db=new DominoGommettesBoard(numPlayers);
		db.initialisizeCardClassic();
		setPosition();
	}
	public PlayerDominoClassic getP() {
		return p;
	}

	@Override 
	public PlayerDominoClassic[] getPlayers() {
		return players;
	}
	@Override 
	public DominoGommettesBoard getDb() {
		return db;
	}
	
	@Override 
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * On commence par le joueur qui a une carte de max valeur.
	 * On l'implément par changer la position de joueur dans la tablequ
	 * 
	 */
	protected void setPosition() {
		int pos=0;
		CardDomiGommettes cardMax=(CardDomiGommettes) db.getHandCard()[0].get(0);
		for(int i=0;i<numPlayers;i++) {
			for(int j=0;j<db.getHandCard()[i].size();j++) {
				CardDomiGommettes card2=(CardDomiGommettes) db.getHandCard()[i].get(j);
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
	
	@Override 
	public void play() {
		p=new PlayerDominoClassic();
		System.out.println("Maximun 5 players");
		numPlayers=p.scanNumber();
		players=new PlayerDominoClassic[numPlayers];
		db=new DominoGommettesBoard(numPlayers);
		for(int i=0;i<numPlayers;i++) {
			players[i]=new PlayerDominoClassic();
			players[i].scanNom();
		}
		db.initialisizeCardClassic();
		this.setPosition();
		System.out.println("you have the biggest card; so you can begin");

		while(true) {
			for(int i=0;i<numPlayers;i++) {
				System.out.println();
				System.out.println(players[i].getName()+" : "+db.getHandCard()[i].toString());
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
