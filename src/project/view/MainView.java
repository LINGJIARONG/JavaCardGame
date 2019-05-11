package project.view;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.card.Path;

/**
 * 
 *Vue principale
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private int largeur;
	private int hauteur;

/**
 * Panel pricipale  
 */

	private ImagePane backgroundPanel;
	public MainView() throws IOException {
		BufferedImage backgroundImage=ImageIO.read(new File(Path.projectPath+"src/images/background.jpg"));
		this.largeur=backgroundImage.getWidth();
		this.hauteur=backgroundImage.getHeight();
		backgroundPanel=new ImagePane(backgroundImage, largeur, hauteur);
		setSize(largeur, hauteur+50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMenu();
		addImage();
		this.getContentPane().add(backgroundPanel);
		repaint();

	}
	/**
	 * definir MenuBar
	 */
	private void setMenu() {
		JMenuBar mb=new JMenuBar();
		JButton exit=new JButton("Exit") ;
		mb.add(exit);
		exit.addActionListener(e->System.exit(0));
		this.setJMenuBar(mb);

	}
	/**
	 * Definir des images et des actions
	 * @throws IOException
	 */
	private void addImage() throws IOException {

		JPanel buttons=new JPanel();
		BufferedImage dominoClassic = ImageIO.read(new File(Path.projectPath+"src/images/domino.jpg"));	
		BufferedImage dominoGommettes = ImageIO.read(new File(Path.projectPath+"src/images/gommettes.jpg"));
		BufferedImage puzzle = ImageIO.read(new File(Path.projectPath+"src/images/puzzle.jpg"));
		BufferedImage saboteur = ImageIO.read(new File(Path.projectPath+"src/images/saboteur.jpg"));
		ImagePane game1=new ImagePane(dominoClassic,300,500);
		JButton classique=new JButton("Play Domino Classic");
		classique.setPreferredSize(new Dimension(300,100));
		classique.setForeground(Color.BLACK);
		game1.add(classique);
		ImagePane game2=new ImagePane(dominoGommettes,300,500);
		JButton playGommettes=new JButton("Play Domino Gommettes");
		playGommettes.setPreferredSize(new Dimension(300,100));
		playGommettes.setForeground(Color.BLACK);
		game2.add(playGommettes);
		ImagePane game3=new ImagePane(puzzle,300,500);
		JButton playPuzzle=new JButton("Play Puzzle");
		playPuzzle.setPreferredSize(new Dimension(300,100));
		playPuzzle.setForeground(Color.BLACK);
		game3.add(playPuzzle);
		ImagePane game4=new ImagePane(saboteur,300,500);
		JButton playSaboteur=new JButton("Play Saboteur");
		playSaboteur.setPreferredSize(new Dimension(300,100));
		playSaboteur.setForeground(Color.BLACK);
		game4.add(playSaboteur);


		game1.setPreferredSize(new Dimension(300,500));
		game2.setPreferredSize(new Dimension(300,500));
		game3.setPreferredSize(new Dimension(300,500));
		game4.setPreferredSize(new Dimension(300,500));

		buttons.add(game1);
		buttons.add(game2);
		buttons.add(game3);
		buttons.add(game4);
		buttons.setOpaque(false);
		backgroundPanel.add(buttons);

		classique.addActionListener(e->{
			int n=0;
			try {
				n=Integer.parseInt(JOptionPane.showInputDialog("How many players ?(maximun 5) "));
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(this,"input a number pls!");

			}
			if(n>5||n<=0)
				JOptionPane.showMessageDialog(this,"maximun 5 players,"
						+ " minimun 1 player");

			try {
				ViewDC v = new ViewDC(n);
				//				v.newGame(n);
				//				v.setName(n);
				v.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		
		playGommettes.addActionListener(e->{
			int n=0;
			try {
				n=Integer.parseInt(JOptionPane.showInputDialog("How many players ?(maximun 5) "));
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(this,"input a number pls!");

			}
			if(n>5||n<=0)
				JOptionPane.showMessageDialog(this,"maximun 5 players,"
						+ " minimun 1 player");

			try {
				ViewDG v = new ViewDG(n);
				v.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		
		
		playPuzzle.addActionListener(e->{	
			try {
				ViewPuzzle v = new ViewPuzzle();
				v.setVisible(true);
				v.play();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		playSaboteur.addActionListener(e->{
			int n=0;
			try {
				n=Integer.parseInt(JOptionPane.showInputDialog("How many players ?(3--10 people) "));
			}catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(this,"input a number pls!");

			}
			if(n>10||n<3) {
				JOptionPane.showMessageDialog(this,"maximun 10 players,"
						+ " minimun 3 player");
				return;
			}
			
			try {
				ViewSabo v = new ViewSabo(n);
				v.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}

}
