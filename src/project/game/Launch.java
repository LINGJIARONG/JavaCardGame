package project.game;


import project.game.DominoClassic;
import project.game.DominoGommettes;
import project.game.Puzzle;
import project.player.Player;
/**
 *  Pour lancer un jeu dans la console , 4 jeux au choix
 *
 */
public class Launch {
	public static void main(String[] args) {
		Player p=new Player();
		String type=p.ChooseGame();
		if(type.equals("Gomette")) {
			DominoGommettes gomette=new DominoGommettes();
			gomette.play();
		}
		else if(type.equals("Dominos")) {
			DominoClassic dominos=new DominoClassic();
			dominos.play();
		}else if(type.equals("Puzzle")) {
			(new Puzzle()).play();
		}else if(type.equals("Saboteur")) {
			(new Saboteur()).play();
		}

	}
}
