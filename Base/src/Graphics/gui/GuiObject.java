package Graphics.gui;

import Graphics.Screen;
import Input.Keyboard;
import Input.Mouse;

public class GuiObject {

	protected Screen screen;
	protected int width, height, x, y;
	protected int color = 0x333333;
	protected Keyboard key;
	protected Mouse mouse;
	
	protected GuiObject(int x, int y, int width, int height){
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}
	
	protected GuiObject(){
		
	}
	
	public void init(Screen screen, Keyboard key, Mouse mouse){
		this.screen = screen;
		this.key=key;
		this.mouse=mouse;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	protected void update(){
		
	}
	
	protected void render(){
		
	}
	
}
