package project.view;
import java.awt.image.BufferedImage;



/**
 * Rotation d'une BufferedImage. 
 * @author LING Jiarong - WEI Fengyi
 *
 */
public class Util {

	/**
	 * tourner l'image a 90 degree dans le sens horaire 
	 * @param image a tourner
	 * @return 
	 */
	public static BufferedImage rotateClockwise90(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage bufferedImage = new BufferedImage(height, width, image.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(height - 1 - j, width - 1 - i, image.getRGB(i, j));
		return bufferedImage;
	}

	/**
	 * tourner l'image a 90 degree dans le sens anti-horaire 
	 * @param image a tourner
	 * @return 
	 */
	public static BufferedImage rotateCounterclockwise90(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage bufferedImage = new BufferedImage(height, width, image.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(j, i, image.getRGB(i, j));
		return bufferedImage;
	}
	/**
	 * tourner l'image a 180 degree 
	 * @param image a tourner
	 * @return 
	 */
	public static BufferedImage rotate180(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(width-1-i, height-1-j,image.getRGB(i, j));
		return bufferedImage;
	}
	
	

}