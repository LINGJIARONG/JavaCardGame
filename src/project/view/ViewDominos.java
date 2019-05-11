package project.view;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import project.board.DominoBoard;
import project.card.Card;
import project.card.cardDominoes.CardDomiClassic;
import project.game.DominoClassic;

/**
 * 
 * Vue de Dominos : classe abstraire 
 */
@SuppressWarnings("serial")
public abstract class ViewDominos extends View {
	/**
	 * a initialise selone des 2 variants de Dominos
	 */
	protected DominoClassic dc;
	protected DominoBoard db;
	private  String[] names;
	private JPanel handPanel;
	private JPanel tablePanel;
	private JLabel currentPlayer;
	protected int currentJoueur=0;
	private ImagePane cardSet;
	/**
	 * numero de carte en action 
	 */
	private int cardDragged;
	private JButton drawButton=new JButton("Draw");
	private JButton skipButton=new JButton("Skip");
	private JPanel toBegin=new JPanel ();
	private 	JPanel toEnd=new JPanel ();
	protected JButton reStart;
	@SuppressWarnings("unused")
	private static boolean end=false;
	
	public ViewDominos(int n) throws IOException {
		super();
		play(n);	
	}



	private void talon() throws IOException {
		BufferedImage back=ImageIO.read(new File("src/images/back.jpg"));
		BufferedImage empty=ImageIO.read(new File("src/images/empty.jpg"));
		if(!db.getCardSet().isEmpty()) {
			cardSet =new ImagePane(back,140,140);
		}
		else {
			cardSet =new ImagePane(empty,140,140);
		}
		cardSet.setPreferredSize(new Dimension(140,140));
		cardSet.setOpaque(false);
		mainPanel.add(cardSet,BorderLayout.PAGE_START);
		repaint();
	}
	
	private void changeName(int i) {
		currentPlayer=new JLabel(names[i]);
	}
/**
 * quitter le jeu et revenir 
 */
	private void hideMe() {
		this.setVisible(false);
	}
	/**
	 *  initialiser le jeu 
	 * @param n numbre de joueurs 
	 */
	public abstract void iniGame(int n);
		
	/**
	 * Jouer
	 */
	public void play(int n) throws IOException{
		iniGame(n);
		setName(n);
		addButton();
		db=dc.getDb();
		dc.getPlayers();
		talon();
		changeName(currentJoueur);
		tab();
		handCard(currentJoueur);
		mainPanel.validate();
		mainPanel.repaint();
		repaint();
		revalidate();

	}
	protected abstract void replay(int n) throws IOException ;
	protected abstract void addButton();
	protected void refresh() throws IOException {
		mainPanel.remove(cardSet);
		mainPanel.remove(handPanel);
		mainPanel.remove(currentPlayer);
		if(tablePanel!=null)
			mainPanel.remove(tablePanel);
		talon();
		changeName(currentJoueur);
		handCard(currentJoueur);
		tab();
		mainPanel.validate();
		mainPanel.repaint();
		repaint();
		revalidate();
	}

	


	protected void setName(int n) {
		names=new String[n];
		for(int i=0;i<n;i++) {
			names[i]=JOptionPane.showInputDialog("what is your name?");
		}
	}
/**
 * Definir des images et des actions de la tableuc
 * @throws IOException
 */
	private void tab() throws IOException {
		int k=0;
		int size=db.getTable().size();
		GridBagConstraints s = new GridBagConstraints();

		if(size==0) {
			tablePanel=new JPanel();
			dropAction a3=new dropAction(false,true);
			new DropTarget(tablePanel, DnDConstants.ACTION_COPY_OR_MOVE, a3);
		}else {
			tablePanel=new JPanel();
			tablePanel.setLayout(new GridBagLayout());
			s.gridwidth = 1;
			s.gridx=0;
			s.gridy=0;
			s.weightx =0;
			s.weighty =0;
			s.insets=new Insets(1, 1, 1, 1);
			toBegin=new JPanel();
			dropAction a1=new dropAction(false,false);
			new DropTarget(toBegin, DnDConstants.ACTION_COPY_OR_MOVE, a1);
			toBegin.setPreferredSize(new Dimension(100,100));
			tablePanel.add(toBegin,s);
			BufferedImage[] card=new BufferedImage[size];
			JLabel[] tab=new JLabel[size];
			for(Card c:db.getTable()) {
				if(k<=10) {
					s.gridx=k+1;
				}
				else if(k>10&&k<=12) {
					s.gridy=k-10;
				}
				else {
					s.gridx=23-k;
				}
				String addr=((CardDomiClassic) c).getAdresse();
				BufferedImage card1 = ImageIO.read(new File(addr));
				switch( c.getUpDirection()) {
				case 2:card[k]=Util.rotateClockwise90(card1);break;
				case -2:card[k]=Util.rotateCounterclockwise90(card1);break;
				case 1: card[k]=card1;break;
				case -1:card[k]=Util.rotate180(card1);break;
				default: break;
				}
				if(k>10&&k<=12) {
					BufferedImage bi=Util.rotateCounterclockwise90(card[k]);		
					card[k]=	bi;
				}
				if(k>12) {
					BufferedImage bi=Util.rotate180(card[k]);
					card[k]=bi;
				}
				tab[k]=new JLabel(new ImageIcon(card[k]));
				tablePanel.add(tab[k],s);
				k++;
			}	
		}
		if(k<=10) {
			s.gridx=k+1;
		}
		else if(k>10&&k<=12) {
			s.gridy=k-10;
		}
		else {
			s.gridx=23-k;
		}
		dropAction a2=new dropAction(true,false);
		toEnd=new JPanel();
		new DropTarget(toEnd, DnDConstants.ACTION_COPY_OR_MOVE, a2);
		toEnd.setPreferredSize(new Dimension(100,100));
		tablePanel.add(toEnd,s);
		tablePanel.setOpaque(false);
		toBegin.setOpaque(false);
		toEnd.setOpaque(false);
		mainPanel.add(tablePanel,BorderLayout.CENTER);
	}

	/**
	 * Definir des images et des actions de handCard
	 * @throws IOException
	 */
	private void handCard(int i) throws IOException {
		int k=0;
		BufferedImage[] card=new BufferedImage[db.getHandCard()[i].size()];
		currentPlayer.setPreferredSize(new Dimension(100,50));
		DragLabel[] hand=new DragLabel[db.getHandCard()[i].size()];
		handPanel=new JPanel();
		handPanel.add(currentPlayer);
		for(Card c:db.getHandCard()[i]) {
			String addr=((CardDomiClassic) c).getAdresse();
			BufferedImage card1 = ImageIO.read(new File(addr));
			card[k]=card1;
			hand[k]=new DragLabel(new ImageIcon(card1),k);
			handPanel.add(hand[k]);
			k++;
		}
		if(db.block(currentJoueur)&&(!db.getCardSet().isEmpty())) {
			draw();
			handPanel.add(drawButton);
		}
		if(db.block(currentJoueur)&&(db.getCardSet().isEmpty())) {
			skip();
			handPanel.add(skipButton);
		}
		handPanel.setOpaque(false);
		mainPanel.add(handPanel,BorderLayout.SOUTH);
	}
/**
 *  prendre une carte 
 */
	private void draw() {
		drawButton=new JButton("Draw");
		drawButton.addActionListener(
				e->{db.draw(currentJoueur);
				try {
					refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}});
	}
/**
 * faire rien
 */
	private void skip() {
		skipButton.addActionListener(
				e->{db.skip(currentJoueur);
				currentJoueur++;
				currentJoueur%=names.length;
				try {
					refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}});	
	}

/**
 * 
 *Class interne pour rendre des image( JLabel ) 'draggable'
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
				cardDragged=cardPress;
				if(db.canPutCardToBegin((CardDomiClassic)(db.getHandCard()[currentJoueur].get(cardDragged)))) {
					toBegin.setBackground(Color.GREEN);
					toBegin.setOpaque(true);
				}
				if(db.canPutCardToEnd((CardDomiClassic)(db.getHandCard()[currentJoueur].get(cardDragged)))) {
					toEnd.setBackground(Color.GREEN);
					toEnd.setOpaque(true);
				}
				tablePanel.validate();
				tablePanel.repaint();
			} catch (InvalidDnDOperationException e2) {
				System.out.println(e2);
			}
		}

		public void dragDropEnd(DragSourceDropEvent e) {
			System.out.println("drop end");
			toBegin.setOpaque(false);
			toEnd.setOpaque(false);
			tablePanel.validate();
			tablePanel.repaint();

		}


		@Override
		public void dragEnter(DragSourceDragEvent arg0) {
		}

		@Override
		public void dragExit(DragSourceEvent arg0) {

		}

		@Override
		public void dragOver(DragSourceDragEvent arg0) {

		}

		@Override
		public void dropActionChanged(DragSourceDragEvent arg0) {
		}
	}




/**
 * 
 *Class interne pour que  la table peuve recevoir une carte  
 */

	public class dropAction implements DropTargetListener{
		
		boolean toTheEnd;
		boolean isEmpty;

		public dropAction(boolean b, boolean c) {
			toTheEnd=b;
			isEmpty=c;
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
			boolean success=false;
			success=db.putCards(currentJoueur, (CardDomiClassic)(db.getHandCard()[currentJoueur].get(cardDragged)),(toTheEnd)?1:0);
			if(!success)
				return;
			if(db.getHandCard()[currentJoueur].isEmpty()) {//gagné!
				end=true;
				JOptionPane.showMessageDialog(null," congratuations ,"+ names[currentJoueur]+" You win ", "WIN", JOptionPane.YES_NO_OPTION);  				
				hideMe();
				try {
					refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			currentJoueur++;
			currentJoueur%=names.length;
			try {
				refresh();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {

		}
	}


}
