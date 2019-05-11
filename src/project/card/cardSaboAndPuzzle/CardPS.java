package project.card.cardSaboAndPuzzle;

import project.card.Card;

/**
 * 
 * @author LING Jiarong & WEI Fengyi
 * cards for Puzzle and Saboteur
 *
 */
public abstract class CardPS extends Card {
	/**
	 *  
	 *  for Puzzle and Saboteur: 0 means block and 1 means acceptable
	 */
	protected int left;
	/**
	 *  
	 *  for Puzzle and Saboteur: 0 means block and 1 means acceptable
	 */
	protected int right;
	
	protected boolean leftIsAvailable=true;
	protected boolean rightIsAvailable=true;
/**
 *  creer une carte avec des valeurs de up/down/left/right et avec *IsAvailble=true et 
 *  upDirection =1 
 * @param up 
 * @param down
 * @param left
 * @param right
 */
	public CardPS(int up,int down,int left,int right){
		super(up,down);
		this.left=left;
		this.right=right;
		this.upDirection=1;
		
	}
	public CardPS(){
		super();
		this.left=0;
		this.right=0;
		this.upDirection=1;
	}
	
	
	
	
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public boolean isLeftIsAvailable() {
		return leftIsAvailable;
	}
	public boolean isRightIsAvailable() {
		return rightIsAvailable;
	}

	public void setLeftIsAvailable(boolean leftIsAvailable) {
		this.leftIsAvailable = leftIsAvailable;
	}
	public void setRightIsAvailable(boolean rightIsAvailable) {
		this.rightIsAvailable = rightIsAvailable;
	}
	/**
	 * connect the left side of the card 
	 */
	public void leftConnected() {
		leftIsAvailable=false;
	}
	/**
	 * connect the right side of the card  
	 */
	public void rightConnected() {
		rightIsAvailable=false;
	}

	
}
