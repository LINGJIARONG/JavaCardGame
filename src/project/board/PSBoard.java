package project.board;

import project.card.cardSaboAndPuzzle.CardPS;
/**
 * Classe abstraire 
 * Board poue saboteur et Puzzle 
 * @author LING Jiarong  ET WEI Fengyi
 */
public abstract class PSBoard extends Board{

	/**
	 * la table 
	 */
	protected CardPS[][] tablePS;
	/**
	 * la ligne de la table 
	 */
	protected int line;
	/**
	 * la colonne de la table 
	 */
	protected int colone;
	
	
	public PSBoard(int nb) {
		super(nb);
		this.tablePS=null;
		this.line=0;
		this.colone=0;
	}
	
	public PSBoard(int nb, int l, int c) {
		super(nb);
		this.tablePS=new CardPS[l][c];
		this.line=l;
		this.colone=c;	
	}

	public CardPS[][] getTablePS() {
		return tablePS;
	}

	public int getLine() {
		return line;
	}

	public int getColone() {
		return colone;
	}
	/**
	 *  supprimer une carte de la table 
	 * @param i Ligne
	 * @param j Colonne 
	 * @return
	 */
	public abstract boolean removeCard(int i, int j);
	
}


























