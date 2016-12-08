package Graphics.gui;

import Graphics.Sprite;

public class TextBox extends GuiObject{
	
	private int cursorTime = 0;
	private int cursorTimeMax = 60;
	private int characters = 0;
	private boolean isActive = false;
	
	private String input = ""; 
	
	public TextBox(int width, int height, int x, int y){
		super(x,y,width,height);
	}
	
	public TextBox(int width, int height, int x, int y, int color){
		super(x,y,width,height,color);
	}
	
	public void update(){
		if(isActive){
			if(cursorTime<cursorTimeMax){
				cursorTime++;
			}else{
				cursorTime = 0;
			}
		}
	}
	
	public void activate(){
		isActive = true;
	}
	
	public void deactivate(){
		isActive = false;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void render(){
		if(isActive){
			screen.renderSprite(x-1, y-1, new Sprite(width+4,height+4,0xeeee33), true);
		}
		screen.renderSprite(x, y, new Sprite(width+2,height+2,0xffffff), true);
		screen.renderSprite(x+1, y+1, new Sprite(width,height,color), true);
		
		if(cursorTime<cursorTimeMax/2&&isActive){
			screen.renderSprite(x+3+(characters*16), y+3, new Sprite(1,16,0xffffff), true);
		}
	}
}
