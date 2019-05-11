package project.card;

public interface Spinnable {
	 /**
	  * to Spin Clockwise the card by 90 degrees
	  * @param card that needs to be rotated 
	  */
	 default void spinClockwise90(Card c) {
	  switch(c.getUpDirection()) {
	  case 1: c.setUpDirection(2);break;
	  case 2: c.setUpDirection(-1);break;
	  case -1: c.setUpDirection(-2);break;
	  case -2: c.setUpDirection(1);break;
	  default: break;
	  }
	 }
	 /**
	  * to Spin Clockwise the card by 180 degrees
	  * @param card that needs to be rotated 
	  */
	 default void spinClockwise180(Card c) {
		 int i=-c.getUpDirection();
		 c.setUpDirection(i);
	 }
	 
	 
	 
	}