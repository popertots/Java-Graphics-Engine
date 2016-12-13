package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[1024];
	public boolean up, down, left, right, shift, ctrl, alt, space, enter, tab;
	public boolean numpad0, numpad1, numpad2, numpad3, numpad4, numpad5, numpad6, numpad7, numpad8, numpad9;
	public boolean keyNum1, keyNum2, keyNum3, keyNum4, keyNum5, keyNum6, keyNum7, keyNum8, keyNum9, keyNum0;
	public boolean backspace;
	private char currentKey = '\u0000';
	
	public void update(){
		//game inputs
		enter = keys[KeyEvent.VK_ENTER];
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
		ctrl = keys[KeyEvent.VK_CONTROL];
		alt = keys[KeyEvent.VK_ALT];
		space = keys[KeyEvent.VK_SPACE];
		tab = keys[KeyEvent.VK_TAB];
		backspace = keys[KeyEvent.VK_BACK_SPACE];
		
		//numpad
		numpad1 = keys[KeyEvent.VK_NUMPAD0];
		numpad1 = keys[KeyEvent.VK_NUMPAD1];
		numpad2 = keys[KeyEvent.VK_NUMPAD2];
		numpad3 = keys[KeyEvent.VK_NUMPAD3];
		numpad4 = keys[KeyEvent.VK_NUMPAD4];
		numpad5 = keys[KeyEvent.VK_NUMPAD5];
		numpad6 = keys[KeyEvent.VK_NUMPAD6];
		numpad7 = keys[KeyEvent.VK_NUMPAD7];
		numpad8 = keys[KeyEvent.VK_NUMPAD8];
		numpad9 = keys[KeyEvent.VK_NUMPAD9];
		
		//top row numbers
		keyNum1 = keys[KeyEvent.VK_1];
		keyNum2 = keys[KeyEvent.VK_2];
		keyNum3 = keys[KeyEvent.VK_3];
		keyNum4 = keys[KeyEvent.VK_4];
		keyNum5 = keys[KeyEvent.VK_5];
		keyNum6 = keys[KeyEvent.VK_6];
		keyNum7 = keys[KeyEvent.VK_7];
		keyNum8 = keys[KeyEvent.VK_8];
		keyNum9 = keys[KeyEvent.VK_9];
		keyNum0 = keys[KeyEvent.VK_0];
		
	}
	
	public boolean isPressed(){
		for(int i = 0; i < keys.length; i++){
			if(keys[i]){
				return true;
			}
		}
		return false;
	}
	
	public char getCurrentKey(){
		return currentKey;
	}
	
	public void keyPressed(KeyEvent e) {
		try{
			currentKey = e.getKeyChar();
			keys[e.getKeyCode()] = true;
		}catch(java.lang.ArrayIndexOutOfBoundsException E){
		}
	}

	public void keyReleased(KeyEvent e) {
		try{
			keys[e.getKeyCode()] = false;
		}catch(java.lang.ArrayIndexOutOfBoundsException E){
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}