package project.player;
/**
 * Comunication dans la console 
 * Pour Dominos Games 
 */
public class PlayerDominoClassic extends Player{

	public PlayerDominoClassic() {
		super();
	}


	public int scanCard() {
		System.out.println("choose a card pls (tape the index of the card from left to right. the first card is 0, the second card is 1 etc.) ");
		while(!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("choose a card pls  (tape the index of the card from left to right. the first card is 0, the second card is 1 etc.)");
		}
		return scan.nextInt();
	}
	/**
	 * demander à l'utilisateur de placer vers le debut ou vers la fin 
	 * @return  0/1
	 */
	public int scanPosition() {
		System.out.println(" Please put your card on the table. 0 for the beginning , 1 for the end  ");
		while(!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("Please put your card on the table. 0 for the beginning , 1 for the end  ");
		}
		return scan.nextInt();
	}
	/**
	 *  bloquer et si joueur voudrais skip >> mais c'est la seul choix possible dans ce cas 
	 * @return s pour skip
	 */ 
	public String scanActionSkip() {
		System.out.println("you are blocked \n s for skip, there is no card to be drawed ");
		String s="";
		s=scan.next();
		while(!s.equals("s"))
			scanActionSkip();
		return s;
	}
	/**
	 *  tirer une carte de talon 
	 * @return d for draw 
	 */
	public String scanActionDraw() {
		System.out.println("you are blocked \n d for draw");
		String s="";
		s=scan.next();
		while(!s.equals("d"))
			scanActionDraw();
		return s;
	}
}
