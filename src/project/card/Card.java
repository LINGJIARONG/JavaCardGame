package project.card;

/**
 * @author LING Jiarong& WEI Fengyi
 * A card for these games
 */
public abstract class Card implements Spinnable{
	/**
	 * the initial up side of the card ( however, the up side is not always towards to the direction of North 
	 * ,if we rotate the card, it might be facing West for example.)
	 *
	 *  a number for game Domino's from 0--6
	 *   Puzzle : -1 means border, 0 means concave, 1 means raised
	 *   Saboteur: 0 means block and 1 means acceptable
	 */
	protected int up;
	/**
	 * the initial down side of the card ( however, the up side is not always towards to the direction of South 
	 * ,if we rotate the card, it might be facing West for example.)
	 *  a number for game Domino's from 0--6
	 *  for Puzzle and Saboteur: 0 means block and 1 means acceptable
	 */
	protected int down;
	
	/**
	 * the direction of the current up side 
	 * 1 means facing North
	 * 2 means facing East
	 * -1 means facing South
	 * -2 means facing West
	 */
	protected int upDirection=2;
	protected boolean isVisible=false;
	protected boolean upIsAvailable=true;
	protected boolean downIsAvailable=true;

	
	public Card(int up,int down){
		this.up=up;
		this.down=down;
	}
	public Card(){
		this.up=0;
		this.down=0;
	}
	
	
	
	public int getUp() {
		return up;
	}
	public int getDown() {
		return down;
	}
	public int getUpDirection() {
		return upDirection;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public boolean isUpIsAvailable() {
		return upIsAvailable;
	}
	public boolean isDownIsAvailable() {
		return downIsAvailable;
	}
	
	public void setUpDirection(int upDirection) {
		this.upDirection = upDirection;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public void setUpIsAvailable(boolean upIsAvailable) {
		this.upIsAvailable = upIsAvailable;
	}
	public void setDownIsAvailable(boolean downIsAvailable) {
		this.downIsAvailable = downIsAvailable;
	}
	
	
	/**
	 * turn the card to visible
	 */
	public void toVisible() {
		isVisible=true;
	}
	
	/**
	 * connect the up side of the card 
	 */
	public void topConnected() {
		upIsAvailable=false;
	}
	/**
	 * connect the down side of the card  
	 */
	public void downConnected() {
		downIsAvailable=false;
	}
	
	/**
	 * @param c a card next to which you want to put this card
	 * @return true if can be put next to card c
	 */
	

}
