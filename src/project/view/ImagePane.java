package project.view;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * JPanel qui contient une image : util pour definir des images à l'arrière-plan
 * @author LING Jiarong - WEI Fengyi
 *
 */


@SuppressWarnings("serial")
public class ImagePane extends JPanel {
	private BufferedImage background;
	private int width1;
	private int height1;
	public ImagePane(){
		
	}
	
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	
	public void setWidth(int width) {
		this.width1 = width;
	}
	
	public void setHeight(int height) {
		this.height1 = height;
	}
	public ImagePane(BufferedImage image,int w,int h){
		background=image;
		width1=w;
		height1=h;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,0,width1,height1,this);
	}
}
