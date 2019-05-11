package project.view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import project.game.DominoGommettes;




@SuppressWarnings("serial")
public class ViewDG extends ViewDominos {

	public ViewDG(int n) throws IOException {
		super(n);
	}

	@Override
	public void iniGame(int n) {
		dc=new DominoGommettes(n);
	}
	
	
	protected void replay(int n) throws IOException{

		dc=new DominoGommettes(n);
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
