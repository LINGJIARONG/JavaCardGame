package project.player;

public class PlayerSaboteur extends Player {
	/**
	 * age de ce joueur 
	 */
	private int age;
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	/**
	 * 
	 * @return age
	 */
	public int scanAge() {
		System.out.println("how old are you? ");
		while(!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("how old are you? ");
		}
		age=scan.nextInt();
		return age;
	}
	/**
	 * 
	 * @return une action 
	 */
	public int scanAction() {
		System.out.println("skip and draw for 0," + 
				"  put card for 1");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input,skip and draw for 0,"
					+ " put card for 1");
		}
		int i=scan.nextInt();
		if(i!=1&&i!=0){
			System.out.println("Invailde input.");
			i=this.scanAction();
		}
		return i;
	}
	/**
	 * 
	 * @return la ligne de 1 a 5
	 */
	public int scanLigne() {
		System.out.println("Which line of the table do you want to put your card in ?(from 1 to 5)");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 5)");
		}
		int i=scan.nextInt();
		if(i<1||i>5){
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 5)");
			i=this.scanLigne();
		}
		return i;
	}
	/**
	 * 
	 * @return la ligne de 1 a 9 
	 */
	public int scanCol() {
		System.out.println("Which line of the table do you want to put your card in ?(from 1 to 9)");
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 9)");
		}
		int i=scan.nextInt();
		if(i<1||i>9){
			System.out.println("Invailde input. Which line of the table do you want to put your card in ?(from 1 to 9)");
			i=this.scanCol();
		}
		return i;
	}
	
	/**
	 * 
	 * @return le nom de victim/ beneficiaire
	 */
	public String scanActionCard() {
		System.out.println("");
		System.out.println("to Which Player you want to use ? (put his name,"
				+ ")\n (for yourself,put \'me\' or your name)");
		return scan.nextLine();
	}
	
	public int scanInt() {
		while(!scan.hasNextInt()){
			scan.nextLine();
			System.out.println("input a number pls ");
		}
		int i=scan.nextInt();
		return i;
		
	}
}
