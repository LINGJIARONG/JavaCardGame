package project.game;

import project.board.SaboteurBoard;
import project.card.Card;
import project.card.cardSaboAndPuzzle.cardSaboteur.BrokenTools;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardDepart;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGoal;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardPath;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardSaboteur;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool1;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool2;
import project.card.cardSaboAndPuzzle.cardSaboteur.Map;
import project.card.cardSaboAndPuzzle.cardSaboteur.RockFall;
import project.player.PlayerSaboteur;
/**
 * Faire Communiquer des classes , definir des réponse par rapport aux entrés
 * @author LING Jiarong WEI Fengyi
 * 
 *
 */
public class Saboteur extends Game{
	public SaboteurBoard getSb() {
		return sb;
	}
	public PlayerSaboteur[] getPs() {
		return ps;
	}
	public PlayerSaboteur getP() {
		return p;
	}
	public String[] getNames() {
		return names;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
	private SaboteurBoard sb;
	private PlayerSaboteur[] ps;
	private PlayerSaboteur p;
	private String[] names ;
	/**
	 * le nombre de joueur
	 */
	private int numPlayers;
	public Saboteur() {
	}
	public Saboteur(int n) {
		sb=new SaboteurBoard(n);
		ps=new PlayerSaboteur[n];
		numPlayers=n;
		for(int i=0;i<numPlayers;i++) {
			ps[i]=new PlayerSaboteur();
		}
	}
	public void setPosition() {
		int ageMin=Integer.MAX_VALUE;
		int youngest=0;
		for(int i=0;i<numPlayers;i++) {
			int myAge=ps[i].getAge();
			if(ageMin>myAge) {
				ageMin=myAge;
				youngest=i;
			}
		}
		sb.changeCards(0, youngest);
		p=ps[youngest];
		ps[youngest]=ps[0];
		ps[0]=p;
		CardSaboteur cardId=sb.getIdP()[0];
		sb.getIdP()[0]=sb.getIdP()[youngest];
		sb.getIdP()[youngest]=cardId;
	}
	private void setNames(int n) {
		names = new String[n];
		for(int i=0;i<n;i++) {
			names[i]=ps[i].getName();
		}
	}
	public void play() {
		p=new PlayerSaboteur();
		numPlayers=p.scanNumber();
		while(numPlayers<0||numPlayers>10) {
			System.out.println("min 0 player ,max 10 player!");
			numPlayers=p.scanNumber();
		}
		sb=new SaboteurBoard(numPlayers);
		ps=new PlayerSaboteur[numPlayers];
		for(int i=0;i<numPlayers;i++) {
			ps[i]=new PlayerSaboteur();
			ps[i].scanNom();
			ps[i].scanAge();
		}
		sb.initialisizeCard();
		setPosition();
		setNames(numPlayers);
		System.out.println(ps[0].getName()+" you are the youngest, so you can begin");


		while(true) {

			for(int i=0;i<numPlayers;i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();

				System.out.println(sb);
				System.out.println(ps[i].getName()+",your id: "+
						sb.getIdP()[i]+"\n"+sb.toStringHand(i));
				if(sb.getCanPut()[i].toString().equals(" "))
					System.out.println("state : everything OK");
				else
					System.out.println("state : "+sb.getCanPut()[i]);
				if(!(sb.getCanPut()[i].canPutPath()))
					System.out.println("you can't put path cards ");
				System.out.println();
				System.out.println("");

				int action=ps[i].scanAction();
				if(action ==1) {//putCard
					int card=ps[i].scanCard();

					while(card>=sb.getHandCard()[i].size()||card<0) {
						System.out.println("false index ");
						card=ps[i].scanCard();
					}

					if(!(sb.getCanPut()[i].canPutPath())) {
						System.out.println("you can't put path cards ");
						while(card>=sb.getHandCard()[i].size()||card<0||(sb.getHandCard()[i].get(card) instanceof CardPath)) {
							System.out.println("invalide card ");
							card=ps[i].scanCard();
						}
					}
					Card c=sb.getHandCard()[i].get(card);
					if(c instanceof CardPath) {	
						boolean ok ;
						do {

							int ligne=ps[i].scanLigne();
							int col=ps[i].scanCol();
							ok=sb.add(i, (CardPath) c, ligne, col);
							if(!ok)
								System.out.println("invalide action! ");
						}while(!ok);
						sb.turn();
						if(sb.isSucceed()) {
							System.out.println(names[i]+" you win ");

							break;
						}

					}
					else if (c instanceof RockFall) {
						boolean ok;
						do {
							int ligne=ps[i].scanLigne();
							int col=ps[i].scanCol();
							if(sb.getTablePS()[ligne][col]==null||sb.getTablePS()[ligne][col] instanceof CardDepart) {
								ok=false;
								System.out.println("invalide action ");
							}else {
								ok=true;
								sb.removeCard(ligne, col,c,i);
							}
						}while(!ok);

					}
					else if(c instanceof BrokenTools) {
						boolean ok;
						do{
							ok=bloque(i,c);
						}while(!ok);
					}
					else if(c instanceof FixTool1) {
						if(c instanceof FixTool2) {
							System.out.println("FixTool 2 "+c+" you can choose one to fix ");
							System.out.println("1-> fix chariot \n2-> fix hamber \n 3->fix latern");
							int fix=ps[i].scanInt();
							((FixTool2)c).choice(fix);
						}
						boolean ok;
						do{
							ok=	fix( i,c);
						}while(!ok);
					}
					else if(c instanceof Map) {
						boolean ok;
						do {
							int ligne=ps[i].scanLigne();
							int col=ps[i].scanCol();
							if((sb.getTablePS()[ligne][col] instanceof CardGoal)) {
								ok=true;
								System.out.println(sb.getTablePS()[ligne][col]);
							}else {
								System.out.println("invalide action ");
								ok=false;
							}
						}while(!ok);
					}
				}
				else if(action ==0 ) {//skip and 
					int card=ps[i].scanCard();
					Card c=sb.getHandCard()[i].get(card);
					sb.getHandCard()[i].remove(c);
				}
				sb.draw(i);
			}				
		}
	}
	/**
	 * utilisation de broken tool
	 * @param i
	 * @param c
	 * @return
	 */
	private boolean bloque(int i,Card c) {
		String s = ps[i].scanActionCard();
		int cpt=0;
		boolean found=false;
		if(s.equals("me")) {
			sb.block(i,c,i);
			return true;
		}
		for(String name:names ) {
			if(name.equals(s)) {
				boolean ok=sb.block(i,c,cpt);
				if(!ok) {
					System.out.println("not ok , he is already suffering this stuff ");
					return false ;
				}	
				found=true;
				break;
			}	
			cpt++;
		}
		String allNames="";
		for(String oo :names )
			allNames+=oo+",";
		if(!found) {
			System.out.println("not found! choose one from "+ allNames);
			return false;
		}
		return true;
	}

	/**
	 * utilisation de fix tool
	 * @param i
	 * @param c
	 * @return
	 */
	private boolean fix(int i,Card c) {
		String s = ps[i].scanActionCard();
		if(s.equals("me")) {
			sb.fix(i,c,i);
			return true;
		}
		int cpt=0;
		boolean found=false;
		for(String name:names ) {
			if(name.equals(s)) {
				boolean ok=sb.fix(i,c,cpt);
				if(!ok) {
					System.out.println("not ok , nothing to fix ");
					return false ;
				}	
				found=true;
				break;
			}	
			cpt++;
		}
		String allNames="";
		for(String oo :names )
			allNames+=oo+",";
		if(!found) {
			System.out.println("not found! choose one from "+ allNames);
			return false;
		}
		return true;
	}

}
