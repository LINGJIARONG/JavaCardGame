package project.player;

public class PlayerPuzzle extends PlayerDominoClassic{
	
	public PlayerPuzzle() {
		super();
	}
	/**
	 * 
	 * @return la ligne 
	 */
	public int scanPositionX() {
		System.out.println("Which line of the table do you want to put your card in ?(from 1 to 3)");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 3)");
		}
		int i=scan.nextInt();
		if(i<1||i>3){
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 3)");
			i=this.scanPositionX();
		}
		return i;
	}
	/**
	 * 
	 * @return la colonne 
	 */
	public int scanPositionY() {
		System.out.println("Which colon of the table do you want to put your card in ?(from 1 to 3)");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input. Which colon of the table do you want to put your card in ?(from 1 to 3)");
		}
		int i=scan.nextInt();
		if(i<1||i>3){
			System.out.println("Invailde input. Which colon of the table do you want to put your card in ?(from 1 to 3)");
			i=this.scanPositionY();
		}
		return i;
	}
	
	/**
	 * 
	 * @return une action 
	 */
	public int scanAction() {
		System.out.println("input card to table for 1, remove card from table for 0");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input, input card to table for 1, remove card from table for 0");
		}
		int i=scan.nextInt();
		if(i!=1&&i!=0){
			System.out.println("Invailde input.");
			i=this.scanAction();
		}
		return i;
	}
}

