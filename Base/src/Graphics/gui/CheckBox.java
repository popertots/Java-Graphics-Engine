package Graphics.gui;

import Graphics.Font;
import Graphics.Sprite;

public class CheckBox extends GuiObject {
	
	private boolean isActive = false;
	private String text;

	public CheckBox(int x, int y, int size, String text){
		super(x,y,size+2,size+2);
		this.text=text;
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
	
	public void update(){

	}
	
	public void render(){
		screen.renderSprite(x, y+3, new Sprite(width,0xffffff), true);
		screen.renderSprite(x+1, y+4, new Sprite(width-2,0x333333), true);
		if(isActive){
			screen.renderSprite(x+2, y+5, new Sprite(width-4,0x777777), true);
		}
		if(!isActive()){
			Sprite temp = Font.consolas.string(text);
			for(int i = 0; i < temp.pixels.length;i++){
				if(temp.pixels[i]==-1){
					temp.pixels[i]=0xaa6666;
				}
			}
			screen.renderSprite(x+16, y, temp, true);
		}else{
			Sprite temp = Font.consolas.string(text);
			for(int i = 0; i < temp.pixels.length;i++){
				if(temp.pixels[i]==-1){
					temp.pixels[i]=0x66aa66;
				}
			}
			screen.renderSprite(x+16, y, temp, true);
		}
	}
}
