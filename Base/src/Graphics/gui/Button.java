package Graphics.gui;

import Graphics.Font;
import Graphics.Sprite;
import Main.Main;

public class Button extends GuiObject {
	
	private String text;
	private boolean isActive = false;


	public Button(int width, int x, int y) {
		super(x, y, width*16, 20);
		color+=0x202020;
	}
	
	public Button(int width, int x, int y, String text) {
		super(x, y, width*16, 20);
		this.text=text;
		color+=0x202020;
	}
	
	public void update(){

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
		screen.renderSprite(x-2, y, new Sprite(width+4,height,isActive()?0xaaaaff:0xffffff), true);
		screen.renderSprite(x, y-2, new Sprite(width,height+4,isActive()?0xaaaaff:0xffffff), true);
		screen.renderSprite(x, y, new Sprite(width,height,color), true);
		String bs = "";
		for(int i = 0; i < (width/16/2-text.length()/2); i++){
			bs += " ";
		}
		screen.renderSprite(x+2, y+2, Font.consolas.string(bs+text), true);
	}
}
	