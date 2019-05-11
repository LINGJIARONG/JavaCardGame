package project.board;

import java.util.Collections;
import java.util.LinkedList;
import project.card.cardSaboAndPuzzle.cardPuzzle.CardPuzzle;
/**
 * Board de Puzzle 
 * @author LINGJiarong
 *
 */
public class PuzzleBoard extends PSBoard{
	/**
	 * handCard 
	 */
	protected LinkedList<CardPuzzle> handCard;
	public PuzzleBoard(int nb, int l, int c) {
		super(nb, l, c);
	}

	public PuzzleBoard() {
		super(1,5,5);
		this.handCard =new LinkedList<CardPuzzle>();
	}


	public LinkedList<CardPuzzle> getHandCardP(){
		return this.handCard;
	}

	public void setHandCard(LinkedList<CardPuzzle> handCard) {
		this.handCard = handCard;
	}

	@Override
	public void initialisizeCard() {
		for(int i=1;i<4;i++) {
			this.tablePS[0][i]=new CardPuzzle(0,-9,0,0);
			this.tablePS[i][0]=new CardPuzzle(0,0,0,-9);
			this.tablePS[i][4]=new CardPuzzle(0,0,-9,0);
			this.tablePS[4][i]=new CardPuzzle(-9,0,0,0);
		}
		LinkedList<CardPuzzle> l=new LinkedList<CardPuzzle>();
		l.add(new CardPuzzle(9,-1,9,-1));
		l.add(new CardPuzzle(9,1,1,1));
		l.add(new CardPuzzle(9,-1,-1,9));
		l.add(new CardPuzzle(1,1,9,1));
		l.add(new CardPuzzle(-1,1,-1,1));
		l.add(new CardPuzzle(1,1,-1,9));
		l.add(new CardPuzzle(-1,9,9,-1));
		l.add(new CardPuzzle(-1,9,1,-1));
		l.add(new CardPuzzle(-1,9,1,9));
		Collections.shuffle(l);
		this.handCard=l;
	}

	@Override
	public boolean removeCard(int i, int j) {
		if (this.tablePS[i][j]==null) return false;
		if(i==0||i==4||j==0||j==4) return false;
		CardPuzzle cp=new CardPuzzle();
		cp=(CardPuzzle) this.tablePS[i][j];
		this.handCard.add(cp);
		this.tablePS[i][j]=null;
		return true;
	}



	/**
	 *  placer une carte dans la table 
	 * @param ps carte a ajouter dans la table 
	 * @param i ligne de table 
	 * @param j colone  de table 
	 * @return true si on reussit a placer dans la table 
	 */
	private boolean addCard(CardPuzzle ps, int i, int j) {
		if (this.tablePS[i][j]!=null) return false;
		if(i==0||i==4||j==0||j==4) return false;
		if (!handCard.contains(ps)) return false;
		boolean u=false;
		boolean d=false;
		boolean l=false;
		boolean r=false;

		if(ps.getUpDirection()==1) {
			if(tablePS[i-1][j]==null) {
				u=true;
			}else {
				u=ps.canBeUpToDown(tablePS[i-1][j]);
			}
			if(tablePS[i][j-1]==null) {
				l=true;
			}else {
				l=ps.canBeLeftToRight(tablePS[i][j-1]);
			}
			if(tablePS[i+1][j]==null) {
				d=true;
			}else {
				d=ps.canBeDownToUp(tablePS[i+1][j]);
			}
			if(tablePS[i][j+1]==null) {
				r=true;
			}else {
				r=ps.canBeRightToLeft(tablePS[i][j+1]);
			}
			if(u&&d&&r&&l) {
				this.tablePS[i][j]=ps;
				this.handCard.remove(ps);
				return true;
			}

		}else if(ps.getUpDirection()==2) {
			if(tablePS[i-1][j]==null) {
				u=true;
			}else {
				u=ps.canBeLeftToDown(tablePS[i-1][j]);
			}
			if(tablePS[i][j-1]==null) {
				l=true;
			}else {
				l=ps.canBeDownToRight(tablePS[i][j-1]);
			}
			if(tablePS[i+1][j]==null) {
				d=true;
			}else {
				d=ps.canBeRightToUp(tablePS[i+1][j]);
			}
			if(tablePS[i][j+1]==null) {
				r=true;
			}else {
				r=ps.canBeUpToLeft(tablePS[i][j+1]);
			}
			if(u&&d&&r&&l) {
				CardPuzzle c=new CardPuzzle(ps.getLeft(),ps.getRight(),ps.getDown(),ps.getUp());
				this.tablePS[i][j]=c;
				this.handCard.remove(ps);
				return true;
			}
		}else if(ps.getUpDirection()==-1) {
			if(tablePS[i-1][j]==null) {
				u=true;
			}else {
				u=ps.canBeDownToDown(tablePS[i-1][j]);
			}
			if(tablePS[i][j-1]==null) {
				l=true;
			}else {
				l=ps.canBeRightToRight(tablePS[i][j-1]);
			}
			if(tablePS[i+1][j]==null) {
				d=true;
			}else {
				d=ps.canBeUpToUp(tablePS[i+1][j]);
			}
			if(tablePS[i][j+1]==null) {
				r=true;
			}else {
				r=ps.canBeLeftToLeft(tablePS[i][j+1]);
			}
			if(u&&d&&r&&l) {
				CardPuzzle c=new CardPuzzle(ps.getDown(),ps.getUp(),ps.getRight(),ps.getLeft());
				this.tablePS[i][j]=c;
				this.handCard.remove(ps);
				return true;
			}
		}else {
			if(tablePS[i-1][j]==null) {
				u=true;
			}else {
				u=ps.canBeRightToDown(tablePS[i-1][j]);
			}
			if(tablePS[i][j-1]==null) {
				l=true;
			}else {
				l=ps.canBeUpToRight(tablePS[i][j-1]);
			}
			if(tablePS[i+1][j]==null) {
				d=true;
			}else {
				d=ps.canBeLeftToUp(tablePS[i+1][j]);
			}
			if(tablePS[i][j+1]==null) {
				r=true;
			}else {
				r=ps.canBeDownToLeft(tablePS[i][j+1]);
			}
			if(u&&d&&r&&l) {
				CardPuzzle c=new CardPuzzle(ps.getRight(),ps.getLeft(),ps.getUp(),ps.getDown());
				this.tablePS[i][j]=c;
				this.handCard.remove(ps);
				return true;
			}
		}
		return false;
	}

	/**
	 *  placer une carte dans la table en essayant toutes les directions possibles
	 * @param ps carte à placer dans la table	 
	 * @param i ligne de la table 
	 * @param j colone de la table 
	 * @return true si c'est bien placé
	 */
	public boolean putCard(CardPuzzle ps, int i, int j) {
		int a=0;
		boolean b=this.addCard(ps, i, j);
		while(a<4) {
			if(b) return true;
			CardPuzzle p=new CardPuzzle();
			p=ps;
			p.spinClockwise90(p);
			b=this.addCard(p, i, j);
			a++;

		}

		return false;
	}
	/**
	 * imprimer une ligne 
	 * @param i la ligne à imprimer 
	 * @return Une chaine qui contient la ligne 
	 */
	public String toStringEachLine(int i) {
		String s="";
		s=s+"| ";
		for(int j=1;j<=3;j++) {
			if(this.tablePS[i][j]!=null) s=s+" "+this.tablePS[i][j].getUp()+"  | ";
			else s=s+" "+" "+"  | ";
		}
		s=s+"\n";
		s=s+"| ";
		for(int j=1;j<=3;j++) {
			if(this.tablePS[i][j]!=null) s=s+this.tablePS[i][j].getLeft()+" "+this.tablePS[i][j].getRight()+" | ";
			else  s=s+" "+" "+"  | ";
		}
		s=s+"\n";
		s=s+"| ";
		for(int j=1;j<=3;j++) {
			if(this.tablePS[i][j]!=null) s=s+" "+this.tablePS[i][j].getDown()+"  | ";
			else  s=s+" "+" "+"  | ";
		}
		s=s+"\n--------------------";
		return s;
	}

	public String toString() {

		String s="===============table==============\n--------------------\n";
		for(int i=1;i<=3;i++) {
			s=s+this.toStringEachLine(i);
			s=s+"\n";
		}
		s=s+"==================================\n";
		return s;
	}
/**
 * imprimer les carte à la main
 * @return une chaine qui contient les carte à la main 
 */
	public String toStringHand() {
		String s="";
		for(CardPuzzle cp:this.handCard) {
			s=s+cp.toString();
			s=s+"\n----------------------\n";
		}
		return s;
	}


}
