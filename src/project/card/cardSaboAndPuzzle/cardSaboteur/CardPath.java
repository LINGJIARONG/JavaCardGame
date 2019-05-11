package project.card.cardSaboAndPuzzle.cardSaboteur;

import project.card.Card;
import project.card.ConnectableSaboAndPuzzle;
import project.card.Spinnable;
import project.card.cardSaboAndPuzzle.CardPS;

public class CardPath extends CardSaboteur implements Spinnable,ConnectableSaboAndPuzzle{

	public CardPath(){
		
	}

	private boolean blocked=false;
	public boolean isBlocked() {
		return blocked;
	}
	
	public CardPath(CardPath cp) {
		super(cp.getUp(),cp.getDown(),cp.getLeft(),cp.getRight());
		this.setVisible(true);
	}
	public CardPath(int up, int down, int left, int right) {
		super(up,down,left,right);
		this.setVisible(true);
	}
	public CardPath(int up, int down, int left, int right,boolean blocked) {
		super(up,down,left,right);
		this.blocked=blocked;
		this.setVisible(true);

	}
	
	public CardPath rotate180() {
		return new CardPath(this.down,this.up,this.right,this.left);
	}
	
	@Override
	public boolean canBeUpToUp(Card c) {
		return c.isUpIsAvailable()&&this.upIsAvailable&&this.up==c.getUp();
	}

	@Override
	public boolean canBeDownToUp(Card c) {
		return c.isUpIsAvailable()&&this.isDownIsAvailable()&&this.down==c.getUp();
	}

	@Override
	public boolean canBeDownToDown(Card c) {
		return  c.isDownIsAvailable()&&this.isDownIsAvailable()&&this.down==c.getDown();
	}

	@Override
	public boolean canBeUpToDown(Card c) {
		return  c.isDownIsAvailable()&&this.isUpIsAvailable()&&this.up==c.getDown();

	}

	@Override
	public boolean canBeRightToRight(CardPS c) {
		return  c.isRightIsAvailable()&&this.isRightIsAvailable()&&this.right==c.getRight();

	}

	@Override
	public boolean canBeRightToLeft(CardPS c) {
		return  c.isLeftIsAvailable()&&this.isRightIsAvailable()&&this.right==c.getLeft();

	}

	@Override
	public boolean canBeLeftToLeft(CardPS c) {
		return  c.isLeftIsAvailable()&&this.isLeftIsAvailable()&&this.left==c.getLeft();
	}

	@Override
	public boolean canBeLeftToRight(CardPS c) {
		return  c.isRightIsAvailable()&&this.isLeftIsAvailable()&&this.left==c.getRight();

	}

	@Override
	public boolean canBeUpToRight(CardPS c) {
		return  c.isRightIsAvailable()&&this.isUpIsAvailable()&&this.up==c.getRight();

	}

	@Override
	public boolean canBeRightToUp(CardPS c) {
		return  c.isUpIsAvailable()&&this.isRightIsAvailable()&&this.right==c.getUp();

	}

	@Override
	public boolean canBeUpToLeft(CardPS c) {
		return  c.isLeftIsAvailable()&&this.isUpIsAvailable()&&this.up==c.getLeft();

	}

	@Override
	public boolean canBeLeftToUp(CardPS c) {
		return  c.isUpIsAvailable()&&this.isLeftIsAvailable()&&this.left==c.getUp();
	}

	@Override
	public boolean canBeDownToRight(CardPS c) {
		return  c.isRightIsAvailable()&&this.isDownIsAvailable()&&this.down==c.getRight();
	}

	@Override
	public boolean canBeRightToDown(CardPS c) {
		return  c.isDownIsAvailable()&&this.isRightIsAvailable()&&this.right==c.getDown();
	}

	@Override
	public boolean canBeDownToLeft(CardPS c) {
		return  c.isLeftIsAvailable()&&this.isDownIsAvailable()&&this.down==c.getLeft();
	}

	@Override
	public boolean canBeLeftToDown(CardPS c) {
		return  c.isDownIsAvailable()&&this.isLeftIsAvailable()&&this.left==c.getDown();
	}
	public String toString() {
		if(!blocked)
			return "*\t"+this.up+"\t*\n"+this.left+"\t\t"+this.right+"\n*\t"+this.down+"\t*\n";
		return "*\t"+this.up+"\t*\n"+this.left+"       x       "+this.right+"\n*\t"+this.down+"\t*\n";
	}
	public String getAddr() {
		String s= super.getAddr()+up+""+down+""+left+""+right;
		if(!blocked)
			return s+".png";
		return s+"b.png";
		
	}
}
