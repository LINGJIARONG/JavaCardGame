package project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import project.card.cardSaboAndPuzzle.cardPuzzle.CardPuzzle;
import project.game.Puzzle;

/**
 * 
 * Vue de Puzzle 
 */
@SuppressWarnings("serial")
public class ViewPuzzle extends View{

	private Puzzle p;
	private JPanel table =new JPanel();
	private JPanel handPanel=new JPanel();
	@SuppressWarnings("unused")
	private boolean win=false;
	/**
	 * numero de carte en action dans 'hand'
	 */
	private int cardDragged;
	/**
	 * numero de carte en action dans 'Table'
	 */
	private int cardDraggedTab;
	private DragLabel[] tab;

	
	
	public ViewPuzzle() throws IOException {
		super();
	}
	/**
	 * 
	 *Class interne pour rendre des image( JLabel ) 'draggable' : des carte dans 'hand ' et 'table '
	 *
	 */
	class DragLabel extends JLabel implements DragGestureListener, DragSourceListener {
		
		private DragSource ds = DragSource.getDefaultDragSource();
		int cardPress;
		
		public DragLabel(ImageIcon i,int k) {
			super(i);
			cardPress=k;
			int action = DnDConstants.ACTION_COPY_OR_MOVE;
			ds.createDefaultDragGestureRecognizer(this, action, this);
		}

		public void dragGestureRecognized(DragGestureEvent e) {
			try {
				Transferable t = new StringSelection(getText());
				e.startDrag(DragSource.DefaultCopyNoDrop, t, this);
				if(e.getComponent().getY()<110) {
					cardDraggedTab=-1;
					cardDragged=cardPress;
					System.out.println("hand");
				}
				else {
					cardDragged=-1;
					cardDraggedTab=cardPress;
				}
			} catch (InvalidDnDOperationException e2) {
				System.out.println(e2);
			}
		}


		@Override
		public void dragEnter(DragSourceDragEvent arg0) {
		}

		@Override
		public void dragExit(DragSourceEvent arg0) {

		}

		@Override
		public void dragOver(DragSourceDragEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void dropActionChanged(DragSourceDragEvent arg0) {
		}

		@Override
		public void dragDropEnd(DragSourceDropEvent dsde) {
			if(	cardDraggedTab==-1)
				return;
			boolean successHand=p.getPboard().removeCard(cardDraggedTab/3+1, cardDraggedTab%3+1);
			System.out.println(successHand+" "+(cardDraggedTab/3+1)+" "+(cardDraggedTab%3+1));
			try {
				mainPanel.remove(handPanel);
				mainPanel.remove(table);
				handPanel=new JPanel();
				table=new JPanel();
				tab();
				setHandPanel();
				mainPanel.validate();
				mainPanel.repaint();
				table.validate();
				handPanel.validate();
				repaint();
				revalidate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
	}
	/**
	 * 
	 *Class interne pour que la table peuve recevoir une carte de 'hand '
	 */

	public class dropToTab implements DropTargetListener{
		int i ;
		int j;
		public dropToTab(int i, int j) {
			this.i=i;
			this.j=j;
		}
		@Override
		public void dragEnter(DropTargetDragEvent e) {

		}

		@Override
		public void dragExit(DropTargetEvent arg0) {

		}

		@Override
		public void dragOver(DropTargetDragEvent arg0) {

		}

		@Override
		public void drop(DropTargetDropEvent e) {
			if(	cardDragged==-1)
				return;
			boolean successTab=false;
			successTab=p.getPboard().putCard(p.getPboard().getHandCardP().get(cardDragged),i,j);
			
			if(!successTab)
				return;
			if(p.getPboard().getHandCardP().isEmpty()) {
				JOptionPane.showMessageDialog(null," congratuations ,"+ p.getPplayer().getName()+" You win ", "WIN", JOptionPane.YES_NO_OPTION);  				
				hideMe();
				
			}
		
			try {
				mainPanel.remove(handPanel);
				mainPanel.remove(table);
				mainPanel.validate();
				mainPanel.repaint();
				handPanel=new JPanel();
				table=new JPanel();
				tab();
				setHandPanel();
				repaint();
				revalidate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
			
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {
		}
	}
	

	/**
	 * Set Nom
	 */
	public void setName() {
		String name="";
		name=JOptionPane.showInputDialog("what is your name?");
		p.getPplayer().setNom(name);
	}
	
	/**
	 * Des image dans 'hand'
	 * @throws IOException
	 */
	public void setHandPanel() throws IOException {
		if(p.getPboard().getHandCardP()==null) this.win=true;
		else {
			DragLabel[] hand=new DragLabel[p.getPboard().getHandCardP().size()];
			int k=0;
			for(CardPuzzle cp:p.getPboard().getHandCardP()) {
				String addr=cp.getAdresse();
				BufferedImage card1 = ImageIO.read(new File(addr));
				hand[k]=new DragLabel(new ImageIcon(card1),k);
				this.handPanel.add(hand[k]);
				k++;
			}
		}
		handPanel.setOpaque(false);
		mainPanel.add(handPanel,BorderLayout.NORTH );
		repaint();
	}
	
	/**
	 * Definir la table 
	 * @throws IOException
	 */
	
	private void tab() throws IOException {
		GridBagConstraints s = new GridBagConstraints();
		table.setLayout(new GridBagLayout());
		s.gridwidth = 1;
		s.gridx=0;
		s.gridy=0;
		s.weightx =0;
		s.weighty =0;
		int l=100;
		int h=100;
		s.insets=new Insets(1, 1, 1, 1);
		tab=new DragLabel[9];
		int k=0;
		for(int i=1;i<4;i++) {
			for(int j=1;j<4;j++) {
				String addr="";
				if(p.getPboard().getTablePS()[i][j]==null) addr="src/images/pz0.png";
				else addr=((CardPuzzle) (p.getPboard().getTablePS()[i][j])).getAdresse();
				BufferedImage card1 = ImageIO.read(new File(addr));
				tab[k]=new DragLabel(new ImageIcon(card1),k);
				s.gridx++;
				table.add(tab[k],s);
				dropToTab a=new dropToTab(i,j);
				new DropTarget(tab[k], DnDConstants.ACTION_COPY_OR_MOVE, a);
				k++;
			}
			s.gridx=0;
			s.gridy++;
		}
		table.setOpaque(false);
		table.setPreferredSize(new Dimension(4*l,4*h));
		mainPanel.add(table,BorderLayout.CENTER);
		repaint();	
	}
	
	
	/**
	 * initialiser le jeu(des modeles)
	 */
	private void iniGame() {
		this.p=new Puzzle();
		p.getPboard().initialisizeCard();
	}
	/**
	 * jouer
	 * @throws IOException
	 */
	public void play() throws IOException {
		iniGame();
		setName();
		addButton();
		setHandPanel();
		tab();
		mainPanel.validate();
		mainPanel.repaint();
		repaint();
		revalidate();

	}
	/**
	 * rejouer
	 * @throws IOException
	 */
	
	private void replay() throws IOException {
		p=new Puzzle();
		mainPanel.remove(handPanel);
		mainPanel.remove(table);
		mainPanel.validate();
		mainPanel.repaint();
		repaint();
		revalidate();
		this.handPanel=new JPanel();
		this.table=new JPanel();
		this.mb=new JMenuBar();
		this.setMenu();
		play();
		repaint();
		revalidate();
	}
	/**
	 * ajouter une Button
	 */
	private void addButton(){
		JButton reStart=new JButton("new Game");
		mb.add(reStart);
		reStart.addActionListener(e->{
			try {
				replay();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			repaint();
		});
	}
	
	/**
	 * termine
	 */
	private void hideMe() {
		this.setVisible(false);
	}

}


















