package project.card;

import project.card.cardSaboAndPuzzle.CardPS;
/**
 *  Si on peut connecter deux carte pour des cartes qui ont seulement 4 face 
 * (.i.e Puzzle,Saboteur )  
 */
public interface ConnectableSaboAndPuzzle extends Connectable{
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.right peut connecter avec c.right 
	 */
	boolean canBeRightToRight(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.right peut connecter avec c.left 
	 */
	boolean canBeRightToLeft(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.left peut connecter avec c.left
	 */
	boolean canBeLeftToLeft(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.left peut connecter avec c.right 
	 */
	boolean canBeLeftToRight(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.up peut connecter avec c.right 
	 */
	boolean canBeUpToRight(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.right peut connecter avec c.up
	 */
	boolean canBeRightToUp(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.up peut connecter avec c.left 
	 */
	boolean canBeUpToLeft(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.left  peut connecter avec c.up
	 */
	boolean canBeLeftToUp(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.down  peut connecter avec c.right 
	 */
	boolean canBeDownToRight(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.right peut connecter avec c.down
	 */
	boolean canBeRightToDown(CardPS c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.down  peut connecter avec c.left 
	 */
	boolean canBeDownToLeft(CardPS c);
	
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.left peut connecter avec c.down 
	 */
	boolean canBeLeftToDown(CardPS c);
	
}
