package project.view;


import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import project.card.Path;
/**
 * Class abstraire pour definir le vue commun des 4 jeux 
 *
 */
@SuppressWarnings("serial")
public abstract class View extends JFrame {
	private int largeur=1280;
	private int hauteur=720;
	protected JMenuBar mb;
	/**
	 * on peut ajouter tous les components ici
	 */
	protected ImagePane mainPanel;

	public View() throws IOException {
		setSize(largeur, hauteur+50);
		setMenu();
		setBackground();

	}
	protected void setMenu() {
		mb=new JMenuBar();
		JButton exit=new JButton("Exit") ;
		mb.add(exit);
		exit.addActionListener(e->this.setVisible(false));
		this.setJMenuBar(mb);
	}
	private void setBackground() throws IOException {
		BufferedImage background=ImageIO.read(new File(Path.projectPath+"src/images/background.jpg"));
		mainPanel=new ImagePane(background,largeur,hauteur);
		mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);		
	}
	
	

}
