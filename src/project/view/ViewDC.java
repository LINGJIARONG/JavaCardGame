package project.view;


import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import project.game.DominoClassic;

/**
 * 
 * Vu de Dominos Gomette
 */
@SuppressWarnings("serial")
public class ViewDC extends ViewDominos {

	public ViewDC(int n) throws IOException {
		super(n);
	}

	@Override
	public void iniGame(int n) {
		dc=new DominoClassic(n);
	}

	protected void replay(int n) throws IOException{
		dc=new DominoClassic(n);
		currentJoueur=0;
		setName(n);
		db=dc.getDb();
		dc.getPlayers();
		refresh();

	}

	protected void addButton() {
		reStart=new JButton("new Game");
		mb.add(reStart);
		reStart.addActionListener(e->{
			int k=Integer.parseInt(JOptionPane.showInputDialog("How many players ?(maximun 5) "));
			try {
				replay(k);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			repaint();
		});
	}

}
