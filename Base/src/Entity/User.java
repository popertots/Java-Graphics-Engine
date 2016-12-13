package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Input.Keyboard;

public class User {
	private Screen Screen;
	private Keyboard key;
	private int x=0,y=0,xa=0,ya=0;
	private Sprite sprite = new Sprite(1,0x00ff00);
	
	public User(int x, int y, Screen screen, Keyboard key){
		this.x=x;
		this.y=y;
		this.Screen=screen;
		this.key=key;
	}
	
	public void update(){
		xa=0;
		ya=0;
				
		if(key.up)ya=-1;
		if(key.down)ya=1;
		if(key.left)xa=-1;
		if(key.right)xa=1;
		move(xa, ya);
	}
	
	public void move(int xa, int ya){
		if (xa!=0&&ya!=0){
			move(xa,0);
			move(0,ya);
			return;
		}
		x += xa;
		y += ya;
	}
	
	public void render(){
		Screen.renderSheet(x, y, SpriteSheet.consolas16, false);
	}
}
