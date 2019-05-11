package project.board;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import project.card.Card;
import project.card.cardSaboAndPuzzle.cardSaboteur.BrokenTools;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardDepart;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGoal;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGoalGold;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGoalRock;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGold;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardMiner;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardPath;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardSabo;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardSaboteur;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool1;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool2;
import project.card.cardSaboAndPuzzle.cardSaboteur.Map;
import project.card.cardSaboAndPuzzle.cardSaboteur.RockFall;
/**
 * 
 * 
 * Board pour Saboteur : 
 * Définir des méthodes, des intéractions entre handCard et table ainsi que tous les actions possible 
 *
 */
public class SaboteurBoard extends PSBoard {
	/**
	 * 
	 *class interne pour définir état du joueur. Dans ce jeu, 3 états sont indispensables 
	 */
	public class State{
		/**
		 * chariot est dans un bon état ou pas  
		 */
		boolean chariot;
		/**
		 * marteau est dans un bon état ou pas  
		 */
		boolean hamber;
		/**
		 * lanterne est dans un bon état ou pas  
		 */
		boolean latern;
		public State(boolean c , boolean h,boolean l){
			chariot=c;
			hamber=h;
			latern=l;
		}
		/**
		 * 
		 * @return true si c'est dans un état parfait 
		 */
		public boolean canPutPath() {
			return chariot&&hamber&&latern;
		}
		public String toString() {
			String s=" ";
			s+=(!chariot?" broken chariot!":"");
			s+=(!hamber?" broken hamber!":"");
			s+=(!latern?" broken latern!":"");
			return s;
		}
	}
	/**
	 * les cases qui sont accessible (on peut y arriver à partir du pointe de départ)
	 */
	boolean[][] conquerer;
	/**
	 * nombre de joueur 
	 */
	int nbPlayer;
	/**
	 *  une ensemble de carte d'or
	 */
	private List<CardGold> goldSet;
	/**
	 *  une ensemble de carte d'indentité
	 */
	private List<CardSaboteur> idSet;
	/**
	 * indentité de chaque joueur 
	 */
	CardSaboteur[] idP;
	/**
	 * état de chaque joueur
	 */
	public State[] canPut;
	/**
	 * la carte d'or sont découvru 
	 */
	private boolean succeed;



	public SaboteurBoard(int nb) {
		super(nb);
		this.tablePS=new CardSaboteur[7][11];
		conquerer=new boolean[7][11];
		line=9;
		colone=5;
		nbPlayer=nb;
		idP=new CardSaboteur[nb];
		canPut=new State[nb];
		for(int i=0;i<nb;i++) {
			canPut[i]=new State(true, true , true );
		}
	}
	/**
	 *@return true si Les Saboteur sont gagnés ( il n'y a pas de carte dans le talon ni dans les handCard)
	 */
	public boolean saboteurWin() {
		if(cardSet.isEmpty()) {
			for(LinkedList<Card> l : handCard) {
				if(!l.isEmpty()) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
	public CardSaboteur[] getIdP() {
		return idP;
	}

	@Override
	public boolean removeCard(int i, int j) {
		return false;
	}

	/**
	 * initialiser le talon (caedSet),et distribuer les cartes a chaque joueur 
	 */
	@Override
	public void initialisizeCard() {
		this.cardSet=new LinkedList<Card>();
		//cardSet.add(new CardPath(1,1,1,1)); // carte de depart 
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,0,0), 5);
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,0,1), 5);
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,1,1), 4);
		Tools.fillCardsSabo(cardSet, new CardPath(0,1,0,1), 5);
		Tools.fillCardsSabo(cardSet, new CardPath(0,1,1,0), 5);
		Tools.fillCardsSabo(cardSet, new CardPath(1,0,1,1), 5);
		Tools.fillCardsSabo(cardSet, new CardPath(0,0,1,1), 5);
		//6 bloqueurs

		Tools.fillCardsSabo(cardSet, new CardPath(0,0,1,0,true), 1);
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,1,0,true), 1);
		Tools.fillCardsSabo(cardSet, new CardPath(1,0,1,1,true), 1);

		Tools.fillCardsSabo(cardSet, new CardPath(1,1,1,1,true), 1);
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,0,1,true), 1);
		Tools.fillCardsSabo(cardSet, new CardPath(1,1,0,0,true), 1);

		for(int i=0;i<6;i++)
			cardSet.add(new RockFall());
		for(int i=0;i<3;i++) {
			cardSet.add(new BrokenTools(1));
			cardSet.add(new BrokenTools(2));
			cardSet.add(new BrokenTools(3));
		}
		cardSet.add(new FixTool2(1,2));
		cardSet.add(new FixTool2(2,3));
		cardSet.add(new FixTool2(1,3));
		cardSet.add(new FixTool1(1));
		cardSet.add(new FixTool1(1));
		cardSet.add(new FixTool1(2));
		cardSet.add(new FixTool1(2));
		cardSet.add(new FixTool1(3));
		cardSet.add(new FixTool1(3));
		cardSet.add(new Map());
		cardSet.add(new Map());
		cardSet.add(new Map());
		if(nbPlayer>=3&&nbPlayer<=5)
			Tools.distributeCards(cardSet, handCard, 6);
		else if(nbPlayer>=6&&nbPlayer<=7)
			Tools.distributeCards(cardSet, handCard, 5);
		else 
			Tools.distributeCards(cardSet, handCard, 4);

		tablePS[3][1]=new CardDepart();
		CardGoal[] finish= {new CardGoalGold(1,1,1,1),new CardGoalRock(0,1,1,0),new CardGoalRock(0,1,0,1)};
		int i=(int) (Math.random()*3);
		int j=(int) (Math.random()*3);
		while(j==i)
			j=(int) (Math.random()*3); //reRendom
		int k=3-i-j;
		tablePS[3][9]=finish[i];
		tablePS[1][9]=finish[k];
		tablePS[5][9]=finish[j];

		goldSet =new LinkedList<>();
		for(int cp=0;cp<16;cp++) {
			goldSet.add(new CardGold(1));
		}
		for(int cp=0;cp<8;cp++) {
			goldSet.add(new CardGold(2));
		}
		for(int cp=0;cp<4;cp++) {
			goldSet.add(new CardGold(3));
		}
		Collections.shuffle(goldSet);

		idSet =new LinkedList<>();
		for(int cp=0;cp<7;cp++)
			idSet.add(new CardMiner());
		for(int cp=0;cp<4;cp++)
			idSet.add(new CardSabo());
		Collections.shuffle(idSet);
		for(int pl=0;pl<nbPlayer;pl++){
			idP[pl]=idSet.remove(0);
		}
	}

	/**
	 * verifier si c'est attache a une carte , sinon on ne peut pas placer dans (ligne,col)
	 * @param ligne
	 * @param col
	 * @return true si cette position est attache a au moins une carte 
	 */
	private boolean attachedToACard(int ligne ,int col) {
		return (col>=10||tablePS[ligne][col+1]!=null)||(col<=0||tablePS[ligne][col-1]!=null)
				||(ligne>=6||tablePS[ligne+1][col]!=null)||(ligne<=0||tablePS[ligne-1][col]!=null);
	}
	/**
	 * Vérifier si on peut placer cp dans ligne-ième ligne et col-ième colonne
	 * @param cp une carte à ajouter 
	 * @param ligne une ligne de la table 
	 * @param col une colone de la table 
	 * @return true si on peut lacer cp dans tab[ligne][col]
	 */
	public boolean canAddCard(CardPath cp,int ligne,int col) {
		if(tablePS[ligne][col]!=null||!attachedToACard(ligne, col))
			return false;
		else {
			boolean u,d,r,l;
			if(ligne>=6||tablePS[ligne+1][col]==null)
				d=true;
			else 
				d=cp.canBeDownToUp(tablePS[ligne+1][col]);

			if(ligne<=0||tablePS[ligne-1][col]==null)
				u=true;
			else 
				u=cp.canBeUpToDown(tablePS[ligne-1][col]);

			if(col>=10||tablePS[ligne][col+1]==null)
				r=true;
			else 
				r=cp.canBeRightToLeft(tablePS[ligne][col+1]);

			if(col<=0||tablePS[ligne][col-1]==null)
				l=true;
			else 
				l=cp.canBeLeftToRight(tablePS[ligne][col-1]);
			return u&&r&&d&&l;

		}
	}
	/**
	 *  placer une carte du joueur dans la table 
	 * @param player numero de joueur 
	 * @param cp une carte  dans la main 
	 * @param ligne
	 * @param col colone ligne
	 * @return true si on réussi à placer une carte dans la table 
	 */
	public boolean add(int player,CardPath cp,int ligne,int col) {
		if(!canPut[player].canPutPath())
			return false;
		if(canAddCard(cp, ligne, col)) {
			handCard[player].remove(cp);
			tablePS[ligne][col]=cp;
			turn();
			return true;
		}
		else {
			CardPath cp180=cp.rotate180();
			if(canAddCard(cp180, ligne, col)) {
				handCard[player].remove(cp);
				tablePS[ligne][col]=cp180;
				return true;
			}
			return false;
		}
	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public List<CardGold> getGoldSet() {
		return goldSet;
	}

	public List<CardSaboteur> getIdSets() {
		return idSet;
	}

	public String toStringEachLine(int i) {
		String s="";
		s=s+"| ";
		for(int j=1;j<=9;j++) {
			if(this.tablePS[i][j]!=null) {
				CardPath cp=(CardPath) this.tablePS[i][j];	
				s=s+" "+((!cp.isVisible())?"*":cp.getUp())+"  | ";
			}
			else s=s+" "+" "+"  | ";
		}
		s=s+"\n";
		s=s+"| ";
		for(int j=1;j<=9;j++) {
			if(this.tablePS[i][j]!=null) {
				CardPath cp=(CardPath) this.tablePS[i][j];	
				if(!cp.isVisible())
					s+="* *"+" | ";
				else {
					if(cp.isBlocked())
						s+=this.tablePS[i][j].getLeft()+"x"+this.tablePS[i][j].getRight()+" | ";
					else 
						s+=this.tablePS[i][j].getLeft()+" "+this.tablePS[i][j].getRight()+" | ";

				}
			}
			else  s=s+" "+" "+"  | ";
		}
		s=s+"\n";
		s=s+"| ";
		for(int j=1;j<=9;j++) {
			if(this.tablePS[i][j]!=null) {
				CardPath cp=(CardPath) this.tablePS[i][j];	
				s=s+" "+((!cp.isVisible())?"*":cp.getDown())+"  | ";
			}
			else  s=s+" "+" "+"  | ";
		}
		s=s+"\n------------------------------------------------------";
		return s;
	}
	public boolean[][] getConquerer() {
		return conquerer;
	}

	public List<CardSaboteur> getIdSet() {
		return idSet;
	}

	public State[] getCanPut() {
		return canPut;
	}

	public boolean isSucceed() {
		return succeed;
	}

	@Override 
	public String toString() {

		String s="===============table==============\n------------------------------------------------------\n";
		for(int i=1;i<=5;i++) {
			s=s+this.toStringEachLine(i);
			s=s+"\n";
		}
		s=s+"==================================\n";
		return s;
	}
	/**
	 * 
	 * @return handCards a imprimer 
	 */
	public String toStringHand(int i) {
		String s="";
		for(Card cp:this.handCard[i]) {
			s=s+cp.toString()+"\n";
			//s=s+"\n----------------------\n";
		}
		return s;
	}
/**
 * RockFall Carte : retirer une carte de la table 
 * @param ligne
 * @param col
 * @param c  RockFall Carte
 * @param player numero du joueur 
 * @return true si on réussit à retirer la carte de la table 
 */
	public boolean removeCard(int ligne, int col, Card c,int player) {
		if(tablePS[ligne][col]!=null&&!(tablePS[ligne][col] instanceof CardDepart)&&
				!(tablePS[ligne][col] instanceof CardGoal)){
			System.out.println("OK");
			tablePS[ligne][col]=null;
			handCard[player].remove(c);
			return true;
		}
		return false;
	}
/**
 * Utilisation de BrokenTool. Si c'est déjà présent, ça ne marche pas
 * @param player numero du joueur ( utilisateur )
 * @param c Broken tool
 * @param i numero du joueur ( victime ) 
 * @return true si c'est bien utilisé
 */
	public boolean block(int player,Card c, int i) {
		int type=((BrokenTools)c).getNature();
		if(type==1&&canPut[i].chariot) {
			canPut[i].chariot=false;
			handCard[player].remove(c);
			return true ;
		}
		if(type==2&&canPut[i].hamber) {
			canPut[i].hamber=false;
			handCard[player].remove(c);
			return true ;
		}

		if(type==3&&canPut[i].latern) {
			canPut[i].latern=false;
			handCard[player].remove(c);
			return true ;
		}
		return false ;

	}
	/**
	 * Utilisation de FixTool. Si le problème n'est pas présent, ça ne marche pas
	 * @param player numero du joueur ( utilisateur )
	 * @param c Broken tool
	 * @param i numero du joueur ( bénéficiaire ) 
	 * @return true si c'est bien utilisé
	 */
	public boolean fix(int player, Card c, int i) {
		int type=((FixTool1)c).getNature1() ;
		if(type==1&&!canPut[i].chariot) {
			canPut[i].chariot=true;
			handCard[player].remove(c);
			return true ;
		}
		if(type==2&&!canPut[i].hamber) {
			canPut[i].hamber=true;
			handCard[player].remove(c);
			return true ;
		}

		if(type==3&&!canPut[i].latern) {
			canPut[i].latern=true;
			handCard[player].remove(c);
			return true ;
		}
		return false;
	}
/**
 * Quand c'est terminé, chacun prend une carte ; Si il n'y a plus de carte , on fait rien 
 * @param player  numéro du joueur 
 */
	public void draw (int player) {
		if(this.cardSet.isEmpty())
			return ;
		handCard[player].add(cardSet.remove(0));
	}
/**
 *  Jeter une carte 
 * @param Player numéro de joueur 
 * @param i numéro de carte à jeter
 */
	public void throwCard(int Player,int i) {
		handCard[Player].remove(i);
	}
/**
 * marquer (i,j) comme accessible , se sert à backtracking (methode suivant)
 * @param i
 * @param j
 */
	private void depart(int i,int j) {

		CardPath c=(CardPath)this.tablePS[i][j];
		conquerer[i][j]=true;
		if(c==null) 
			return;
		if(i<0||i>6||j<0||j>10)
			return;
		if(!c.isBlocked()) {
			if(j>0) {
				if(c.getLeft()==1&&!conquerer[i][j-1]) {
					depart(i,j-1);
				}
			}
			if(j<10) {
				if(c.getRight()==1&&!conquerer[i][j+1]) {
					depart(i,j+1);
				}
			}
			if(i>0) {
				if(c.getUp()==1&&!conquerer[i-1][j]) {
					depart(i-1,j);
				}
			}
			if(i<6) {
				if(c.getDown()==1&&!conquerer[i+1][j]) {
					depart(i+1,j);
				}
			}
		}
	}
/**
 * backtracking pour marquer des cases accesibles 
 */
	public void turn() {
		conquerer=new boolean[7][11];
		depart(3,1);
		if(conquerer[1][9]) {
			CardGoal cg=(CardGoal)tablePS[1][9];
			cg.setVisible(true);
			if(cg instanceof CardGoalGold) 
				succeed=true;
		}
		if(conquerer[3][9]) {
			CardGoal cg=(CardGoal)tablePS[3][9];
			cg.setVisible(true);
			if(cg instanceof CardGoalGold) 
				succeed=true;

		}
		if(conquerer[5][9]) {
			CardGoal cg=(CardGoal)tablePS[5][9];
			cg.setVisible(true);
			if(cg instanceof CardGoalGold) 
				succeed=true;

		}

	}


}
