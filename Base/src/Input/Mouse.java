package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	private static int scale = 2;
	private boolean hasbeenclicked = false;
	private int clickCounter = 0;

	public  int getX(){
		return mouseX/scale;
	}
	public  int getY(){
		return mouseY/scale;
	}
	public int getB(){
		return mouseB;
	}
	
	public void update(){
		if(hasbeenclicked)
			clickCounter=0;
		else if(clickCounter<5)
			clickCounter++;
		hasbeenclicked = false;
	}
	
	public boolean clicked(){
		return clickCounter==0?true:false;
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		hasbeenclicked = true;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
		clickCounter=0;
	}
}
