package project.card.cardSaboAndPuzzle.cardPuzzle;

import project.card.Card;
import project.card.ConnectableSaboAndPuzzle;
import project.card.Path;
import project.card.cardSaboAndPuzzle.CardPS;

public  class CardPuzzle extends CardPS implements ConnectableSaboAndPuzzle{
	
	
	public CardPuzzle(){
		super();
	}	
	public CardPuzzle(int up,int down,int left,int right){
		super(up,down,left,right);
	}
	
	
	@Override
	public boolean canBeUpToUp(Card c) {
		return this.up+c.getUp()==0&&c.isUpIsAvailable();
	}
	@Override
	public boolean canBeDownToUp(Card c) {
		return this.down+c.getUp()==0&&c.isUpIsAvailable();
	}
	@Override
	public boolean canBeDownToDown(Card c) {
		return this.down+c.getDown()==0&&c.isDownIsAvailable();
	}
	@Override
	public boolean canBeUpToDown(Card c) {
		return this.up+c.getDown()==0&&c.isDownIsAvailable();
	}
	@Override
	public boolean canBeRightToRight(CardPS c) {
		return c.isRightIsAvailable()&&c.getRight()+this.right==0;
	}
	@Override
	public boolean canBeRightToLeft(CardPS c) {
		return c.isLeftIsAvailable()&&c.getLeft()+this.right==0;
	}
	@Override
	public boolean canBeLeftToLeft(CardPS c) {
		return  c.isLeftIsAvailable()&&c.getLeft()+this.left==0;
	}
	@Override
	public boolean canBeLeftToRight(CardPS c) {
		return c.isRightIsAvailable()&&c.getRight()+this.left==0;
	}

	
	public boolean canBeUpToRight(CardPS c) {
		return c.isRightIsAvailable()&&c.getRight()+this.up==0;
	}
	@Override
	public boolean canBeRightToUp(CardPS c) {
		return c.isUpIsAvailable()&&c.getUp()+this.right==0;
	}
	@Override
	public boolean canBeUpToLeft(CardPS c) {
		return c.isLeftIsAvailable()&&this.up+c.getLeft()==0;
	}
	@Override
	public boolean canBeLeftToUp(CardPS c) {
		return c.isUpIsAvailable()&&this.left+c.getUp()==0;
	}
	@Override
	public boolean canBeDownToRight(CardPS c) {
		return c.isRightIsAvailable()&&this.down+c.getRight()==0;
	}
	@Override
	public boolean canBeRightToDown(CardPS c) {
		return c.isDownIsAvailable()&&this.right+c.getDown()==0;
	}
	@Override
	public boolean canBeDownToLeft(CardPS c) {
		return c.isLeftIsAvailable()&&this.down+c.getLeft()==0;
	}
	@Override
	public boolean canBeLeftToDown(CardPS c) {
		return c.isDownIsAvailable()&&this.left+c.getDown()==0;
	}

	
	public String toString() {
		String s="";
		if(this.upDirection==1) {
			s="*\t"+this.up+"\t*\n"+this.left+"\t\t"+this.right+"\n*\t"+this.down+"\t*\n";
		}else if(this.upDirection==2) {
			s="*\t"+this.left+"\t*\n"+this.down+"\t\t"+this.up+"\n*\t"+this.right+"\t*\n";
		}else if(this.upDirection==-1) {
			s="*\t"+this.down+"\t*\n"+this.right+"\t\t"+this.left+"\n*\t"+this.up+"\t*\n";
		}else{
			s="*\t"+this.right+"\t*\n"+this.up+"\t\t"+this.down+"\n*\t"+this.left+"\t*\n";
		}
		return s;
	}
	
	@SuppressWarnings("unused")
	public String getAdresse() {
		if(this!=null) return Path.projectPath+"src/images/pz"+up+"_"+down+"_"+left+"_"+right+".png";
		else return Path.projectPath+"src/images/pz0.png";
	}
	

	
}


















