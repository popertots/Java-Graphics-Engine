package Graphics.gui;

import java.awt.event.KeyEvent;

import Graphics.Font;
import Graphics.Sprite;

public class TextBox extends GuiObject{
	
	private int cursorTime = 0;
	private int cursorTimeMax = 60;
	private int characters = 0;
	private int typeDelay=0;
	private char lastChar = '\u0000';
	private String blankText;
	private boolean isActive = false;
	
	private String input = ""; 
	
	public TextBox(int width, int x, int y, String blankText){
		super(x,y,width*16+3,20);
		this.blankText=blankText;
	}
	
	public TextBox(int width, int x, int y){
		super(x,y,width*16+3,20);
	}
	
	public String getOutput(){
		return input;
	}

	public void update(){
		if(isActive){
			typeDelay++;
			if(cursorTime<cursorTimeMax){
				cursorTime++;
			}else{
				cursorTime = 0;
			}
			if(key.isPressed()){
				if(key.getCurrentKey()!=lastChar){
					typeDelay=0;
				}else{
					typeDelay++;
				}
				if(typeDelay==0 || typeDelay>45){
					if(key.backspace&&input.length()>0)
						input = input.substring(0, input.length()-1);
					else if(!key.backspace&&characters<width/16)
						if(isPrintableChar(key.getCurrentKey())){
							input += key.getCurrentKey();
						}
					characters = input.length();
				}
			}
		}
		lastChar=key.isPressed()?key.getCurrentKey():'\u0000';
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
	
	private boolean isPrintableChar(char c ) {
	    Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
	    return (!Character.isISOControl(c)) &&
	            c != KeyEvent.CHAR_UNDEFINED &&
	            block != null &&
	            block != Character.UnicodeBlock.SPECIALS;
	}
	
	public void render(){
		if(isActive){
			screen.renderSprite(x-2, y-2, new Sprite(width+4,height+4,0xeeee33), true);
		}
		screen.renderSprite(x-1, y-1, new Sprite(width+2,height+2,0xffffff), true);
		screen.renderSprite(x, y, new Sprite(width,height,color), true);
		
		if(cursorTime<cursorTimeMax/2&&isActive){
			screen.renderSprite(x+3+(characters*16), y+3, new Sprite(1,16,0xffffff), true);
		}
		if(characters>0){
			screen.renderSprite(x+2, y+3, Font.consolas.string(input), true);
		}else if(blankText!=null){
			String bs = "";
			for(int i = 0; i < (width/16/2-blankText.length()/2); i++){
				bs += " ";
			}
			Sprite temp = Font.consolas.string(bs+blankText);
			for(int i = 0; i < temp.pixels.length;i++){
				if(temp.pixels[i]==-1){
					temp.pixels[i]=0x666666;
				}
			}
			screen.renderSprite(x+2, y+3, temp, true);
		}
	}
}
