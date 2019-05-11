package project.card;
/**
 *  Si on peut connecter deux carte pour des cartes qui ont seulement deux face 
 * (.i.e Dominos)  
 */
public interface Connectable {
/**
 *  
 * @param c une carte 
 * @return true si this .up peut connecter avec c.up
 */
	boolean canBeUpToUp(Card c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.down peut connecter avec c.up
	 */
	boolean canBeDownToUp(Card c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.down  peut connecter avec c.down
	 */
	boolean canBeDownToDown(Card c);
	/**
	 *  
	 * @param c une carte 
	 * @return true si this.up  peut connecter avec c.down
	 */
	boolean canBeUpToDown(Card c);
	
}
