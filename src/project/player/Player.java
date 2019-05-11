package project.player;
import java.util.Scanner;

public class Player{
	/**
	 * le nom du joueur
	 */
	private String name;

	/**
	 * scanner les actions du joueur
	 */
	protected Scanner scan;


	/**
	 * constructeur Joueur anonyme
	 */
	public Player(){
		name = "Anonyme";
		scan= new Scanner(System.in);
	}

	/**
	 * choose a game
	 * @return
	 */
	public String ChooseGame() {
		System.out.println("which game you wanna play ? Puzzle,Gomette or Dominos?");
		String s=scan.next();
		while(!s.equals("Gomette")&&!s.equals("Dominos")&&!s.equals("Puzzle"))
			ChooseGame();
		return s;
	}

	/**
	 * getters
	 * @return le nom du joueur
	 */
	public String getName() {
		return name;
	}


	/**
	 * setter
	 * @param nom le nom de l'utilisateur
	 */
	public void setNom(String nom) {
		this.name=nom;
	}

	/**
	 * scanner le nom de l'utilisateur
	 */
	public void scanNom(){
		System.out.println("what's your name ?");
		setNom(scan.nextLine());
	}

	/**
	 * demander à l'utilisateur le nombre des joueurs jusqu'il répond
	 * @return le nombre des joueurs participés
	 */
	public int scanNumber() {
		System.out.println("how many players? ");
		while(!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("how many players? ");
		}
		return scan.nextInt();
	}

	/**
	 * demander à l'utilisateur de choisir une carte jusqu'il fait la choix correctement
	 * @return le numéro de la carte choisie
	 */
	public int scanCard() {
		System.out.println("choose a card pls  ");
		while(!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("choose a card pls  ");
		}
		int i= scan.nextInt();
		scan.nextLine();

		return i;
	}


}
















