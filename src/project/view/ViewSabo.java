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
import project.board.SaboteurBoard;
import project.card.Card;
import project.card.cardSaboAndPuzzle.CardPS;
import project.card.cardSaboAndPuzzle.cardSaboteur.BrokenTools;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardGoal;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardPath;
import project.card.cardSaboAndPuzzle.cardSaboteur.CardSaboteur;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool1;
import project.card.cardSaboAndPuzzle.cardSaboteur.FixTool2;
import project.card.cardSaboAndPuzzle.cardSaboteur.Map;
import project.card.cardSaboAndPuzzle.cardSaboteur.RockFall;
import project.game.Saboteur;
/**
 * Vue pour Saboteur
 *
 */

@SuppressWarnings("serial")
public class ViewSabo extends View{
	private Saboteur s;
	private JPanel tablePanel;
	private JPanel handPanel;
	private JPanel playerInfo;
	private JLabel cardSet;
	private SaboteurBoard sb;
	private String[] names;
	private int[] ages;
	private int currentJoueur;
	private int cardDragged;
	private JButton restart;
	private int numPlayer;
	private boolean end;
	


	public ViewSabo(int n) throws IOException {
		super();
		addButton();
		numPlayer=n;
		play(n);
	}
	private void setName(int n) {
		names=new String[n];
		ages=new int[n];
		for(int i=0;i<n;i++) {
			names[i]=JOptionPane.showInputDialog("what is your name?");
			s.getPs()[i].setNom(names[i]);
			setAge(i);

		}
	}
	private void setAge(int n) {
		try {
			ages[n]=Integer.parseInt(JOptionPane.showInputDialog("How old are you?"));
			s.getPs()[n].setAge(ages[n]);
		}catch(NumberFormatException exception) {
			JOptionPane.showMessageDialog(this,"input a number pls!");
			this.setVisible(false);
		}
	}
	/**
	 * commencer a jouer
	 * @throws IOException
	 */
	private void players() throws IOException {
		playerInfo=new JPanel();
		playerInfo.setLayout(new GridBagLayout());
		GridBagConstraints s = new GridBagConstraints();
		s.insets=new Insets(3,3,3,3);
		s.gridwidth = 1;
		s.gridx=0;
		s.gridy=0;
		s.weightx =0;
		s.weighty =0;
		talon();
		for(int i=0;i<numPlayer;i++) {
			s.gridy=i+1;
			JPanel player=new JPanel();
			player.setPreferredSize(new Dimension(200,100));
			player.setLayout(new GridLayout(3, 1));
			JLabel name=new JLabel("player"+i+ ":"+names[i]);
			String etat = sb.getCanPut()[i].toString();
			if(etat.equals(" "))
				etat="Prefect State";
			JLabel state = new JLabel(etat);
			player.add(name);
			player.add(state);

			if(i==currentJoueur) {
				player.setBackground(Color.PINK);
				player.add(new JLabel(sb.getIdP()[i].toString()));
			}
			else
				player.setBackground(Color.lightGray);
			player.setOpaque(true);
			playerInfo.add(player,s);
			dropAction a=new dropAction(i,0,false);
			new DropTarget(player, DnDConstants.ACTION_COPY_OR_MOVE, a);
		}
		playerInfo.setOpaque(false);
		mainPanel.add(playerInfo,BorderLayout.EAST);


	}
	/**
	 * definir le talon
	 * @throws IOException
	 */
	private void talon() throws IOException {
		BufferedImage back=ImageIO.read(new File("src/images/Cache.png"));
		BufferedImage empty=ImageIO.read(new File("src/images/SaboEmpty.png"));
		if(!sb.getCardSet().isEmpty()) {
			cardSet =new JLabel(new ImageIcon(back));
		}
		else {
			cardSet =new JLabel(new ImageIcon(empty));
		}
		cardSet.setPreferredSize(new Dimension(60,100));
		cardSet.setOpaque(false);
		GridBagConstraints s = new GridBagConstraints();
		s.insets=new Insets(3,3,3,3);
		s.gridwidth = 1;
		s.gridx=0;
		s.gridy=11;
		s.weightx =0;
		s.weighty =0;
		playerInfo.add(cardSet,s);
		repaint();

	}

	private void initial(int n) {
		s=new Saboteur(n);
		sb=s.getSb();
		setName(n);

	}
	/**
	 * 
	 * @param p numero du joueur courrent 
	 * @throws IOException 
	 */
	private void hand() throws IOException {
		handPanel=new JPanel();
		handPanel.setLayout(new GridBagLayout());
		GridBagConstraints s = new GridBagConstraints();
		s.insets=new Insets(5,5, 5, 5);
		s.gridwidth = 1;
		s.gridx=0;
		s.gridy=0;
		s.weightx =0;
		s.weighty =0;
		BufferedImage[] card=new BufferedImage[sb.getHandCard()[currentJoueur].size()];
		DragLabel[] hand=new DragLabel[sb.getHandCard()[currentJoueur].size()];
		int k=0;
		for(Card c:sb.getHandCard()[currentJoueur]) {
			System.out.println(c);//a supprimer
			s.gridy =k;
			String addr=((CardSaboteur) c).getAddr();
			System.out.println(addr);//a supprimer
			BufferedImage card1 = ImageIO.read(new File(addr));
			card[k]=card1;
			hand[k]=new DragLabel(new ImageIcon(card1),k);
			handPanel.add(hand[k],s);
			k++;
		}
		handPanel.setOpaque(false);
		s.weighty =10;
		JLabel defausse=new JLabel(new ImageIcon(ImageIO.read(new File("src/images/defausse.png"))));
		dropAction a=new dropAction(-1,-1,false);
		new DropTarget(defausse, DnDConstants.ACTION_COPY_OR_MOVE, a);
		handPanel.add(defausse);
		mainPanel.add(handPanel,BorderLayout.WEST);
	}
/**
 * definir la table 
 * @throws IOException
 */
	private void tab() throws IOException {
		sb.turn();
		GridBagConstraints s = new GridBagConstraints();
		tablePanel=new JPanel();
		CardPS[][] tab=sb.getTablePS();
		tablePanel.setLayout(new GridBagLayout());
		s.gridwidth = 1;
		s.gridx=0;
		s.gridy=0;
		s.weightx =0;
		s.weighty =0;
		s.insets=new Insets(1, 1, 1, 1);
		for(int ligne=0;ligne<=6;ligne++) {
			for(int col=0;col<=10;col++) {
				if(end) {
					if(tab[ligne][col]!=null) {
						tab[ligne][col].setVisible(true);
					}
				}
				String addr;
				CardPS card1;
				if(tab[ligne][col]!=null) {
					card1=tab[ligne][col];
					addr=((CardPath)card1).getAddr();
					System.out.println(tab[ligne][col]);
					System.out.println(addr);
					BufferedImage image=ImageIO.read(new File(addr));
					JLabel tabCard=new JLabel(new ImageIcon(image));
					s.gridx=col;
					s.gridy=ligne;
					dropAction a=new dropAction(ligne,col,true);
					new DropTarget(tabCard, DnDConstants.ACTION_COPY_OR_MOVE, a);
					tablePanel.add(tabCard,s);
			
				}else {
					if(!sb.getConquerer()[ligne][col]) {
						JPanel vide=new JPanel();
						vide.setOpaque(false);
						vide.setPreferredSize(new Dimension (60,100));
						s.gridx=col-1;
						s.gridy=ligne-1;
						tablePanel.add(vide,s);

					}
					else {
						addr="src/images/SaboEmpty.png";
						System.out.println((sb.getConquerer()[ligne][col]));
						System.out.println(addr);
						BufferedImage image=ImageIO.read(new File(addr));
						JLabel tabCard=new JLabel(new ImageIcon(image));
						dropAction a=new dropAction(ligne,col,true);
						new DropTarget(tabCard, DnDConstants.ACTION_COPY_OR_MOVE, a);
						s.gridx=col;
						s.gridy=ligne;
						tablePanel.add(tabCard,s);
					}
				}

			}
		}
		tablePanel.setOpaque(false);		
		mainPanel.add(tablePanel,BorderLayout.CENTER);
		tablePanel.validate();
		tablePanel.repaint();
	}

	private void refresh() throws IOException {
		mainPanel.remove(playerInfo);
		mainPanel.remove(handPanel);
		mainPanel.remove(tablePanel);
		players();
		hand();
		tab();
		mainPanel.validate();
		mainPanel.repaint();
		repaint();
		revalidate();

	}

	private void resetName() {
		for(int i=0;i<numPlayer;i++) {
			names[i]=s.getPs()[i].getName();
		}

	}
	public void play(int n) throws IOException {
		s=new Saboteur(n);
		initial(n);
		sb.initialisizeCard();
		s.setPosition();
		resetName();
		players();
		hand();
		tab();
	}

	private void replay(int n) throws IOException{
		mainPanel.remove(playerInfo);
		s=new Saboteur(n);
		numPlayer=n;
		currentJoueur=0;
		initial(n);
		sb.initialisizeCard();
		s.setPosition();
		resetName();
		players();
		refresh();
	}

	public void addButton() {
		restart=new JButton("new Game");
		mb.add(restart);
		restart.addActionListener(e->{
			int k=Integer.parseInt(JOptionPane.showInputDialog("How many players ?(3 --- 10 players) "));
			try {
				replay(k);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			repaint();
		});
	}

/**
 * 
classe interne pour rendre Draggable des cartes
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
				cardDragged=cardPress;

				tablePanel.validate();
				tablePanel.repaint();
			} catch (InvalidDnDOperationException e2) {
				System.out.println(e2);
			}
		}

		public void dragDropEnd(DragSourceDropEvent e) {
			System.out.println("drop end");
			if(sb.saboteurWin()) {
				end=true;
				JOptionPane.showMessageDialog(null," congratuations ,Saboteurs, You win ", "WIN", JOptionPane.YES_NO_OPTION);  				
				try {
					refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				hideMe();	
			}
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
 *Classe interne pour que la table peuve recevoir des carte 
 */

	public class dropAction implements DropTargetListener{
		int ligne;
		int col;
		boolean toTable;

		public dropAction(int l,int c,boolean b) {
			ligne=l;
			col=c;		
			toTable=b;
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
			if(toTable) {
				Card c= sb.getHandCard()[currentJoueur].get(cardDragged);
				if(c instanceof CardPath) {
					System.out.println("cardPath");
					success=sb.add(currentJoueur, (CardPath) c, ligne, col);
					if(!success)
						return;
					if(sb.isSucceed()) {//gagné!
						end=true;
						try {
							refresh();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null," congratuations ,Miners, You win ", "WIN", JOptionPane.YES_NO_OPTION);  				
						hideMe();
					}
				}else if(c instanceof RockFall){//RockFall Card
					success=sb.removeCard(ligne, col, c, currentJoueur);
					if(!success)
						return;
				}else if(c instanceof Map) {
					System.out.println("MAP");
					if(!(sb.getTablePS()[ligne][col] instanceof CardGoal)) {
						System.out.println(ligne+","+col);
						System.out.println("not a CardGoal");
						return;
					}
					else {	
						JOptionPane.showConfirmDialog(mainPanel, sb.getTablePS()[ligne][col].toString());	
						sb.throwCard(currentJoueur, cardDragged);
					}
				}else {
					return;
				}
			}else {//Action Card!-> toTable=false			
				
				System.out.println("Action Card!");
				Card c= sb.getHandCard()[currentJoueur].get(cardDragged);
				if(col==-1&&ligne==-1) {
					sb.throwCard(currentJoueur, cardDragged);
				}
				else if(c instanceof BrokenTools) {
					System.out.println(c);
					System.out.println(ligne+" , "+col);
					success=sb.block(currentJoueur, c, ligne);
					System.out.println(success);
					if(!success)
						return ;
				}else if(c instanceof FixTool1) {
					if(c instanceof FixTool2) {
						Object[] options = {"fix Chariot","fix hamber","fix latern"};
						int response=JOptionPane.showOptionDialog(mainPanel, "choose a tool", " Fixtool ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if(response==0&&(((FixTool2)c).getNature1()==1||((FixTool2)c).getNature2()==1)) {
							((FixTool2)c).choice(1);
						}else if(response==1&&(((FixTool2)c).getNature1()==2||((FixTool2)c).getNature2()==2)) {
							((FixTool2)c).choice(2);
						}else if(response==2&&(((FixTool2)c).getNature1()==3||((FixTool2)c).getNature2()==3)) {
							((FixTool2)c).choice(3);
						}else {
							return;
						}
					}	
					success=sb.fix(currentJoueur, ((FixTool1)c), ligne);
					if(!success) {
						System.out.println("not OK");
						if(c instanceof FixTool2) {
							((FixTool2)c).redo();
						}
						return ;
					}
				}else {
					return;
				}
			}
			sb.draw(currentJoueur);
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

	private void hideMe() {
		this.setVisible(false);		
	}






}



