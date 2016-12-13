package Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Graphics.Screen;
import Graphics.Sprite;
import Graphics.gui.Button;
import Graphics.gui.ButtonQuit;
import Graphics.gui.CheckBox;
import Graphics.gui.GuiController;
import Graphics.gui.GuiObject;
import Graphics.gui.RadioButton;
import Graphics.gui.TextBox;
import Input.Keyboard;
import Input.Mouse;

public class world {
	
	public int width, height, num=0;
	private Screen screen;
	protected Random rnd = new Random();
	
	private static List<GuiController> guis = new ArrayList<GuiController>();
	
	
	public world(int width, int height, Screen screen, Keyboard key,Mouse mouse){
		this.width=width;
		this.height=height;
		this.screen=screen;
		guis.add(new GuiController(screen, key, mouse, new GuiObject[]{
				new TextBox(15,screen.width/2-15*16/2,screen.height-250,"IP ADDRESS"),
				new TextBox(15,screen.width/2-15*16/2,screen.height-200,"PORT"),
				new TextBox(15,screen.width/2-15*16/2,screen.height-150,"USERNAME")}));
		guis.add(new GuiController(screen, key, mouse, new GuiObject[]{
				new Button(7,screen.width/2-15*16/2,screen.height-100,"Connect"),
				new ButtonQuit(7,screen.width/2+15*16/2-7*16+2,screen.height-100)}));
		guis.add(new GuiController(screen, key, mouse, new GuiObject[]{
				new CheckBox(screen.width/2-60,15,10,"Check A"),
				new CheckBox(screen.width/2-60,35,10,"Check B"),
				new CheckBox(screen.width/2-60,55,10,"Check C")}));
		guis.add(new GuiController(screen, key, mouse, new GuiObject[]{
				new RadioButton(screen.width/2-60,85,10,"Radio A"),
				new RadioButton(screen.width/2-60,105,10,"Radio B"),
				new RadioButton(screen.width/2-60,125,10,"Radio C")}));
	}
	
	public void update(){
		for(int i = 0; i < guis.size(); i++){
			guis.get(i).update();
		}
	}
	
	public void render(){
		//screen.renderSheet(0, 0, SpriteSheet.Background, false);
		screen.renderSprite(screen.width/2, 0, new Sprite(1,screen.height,0xdd00dd), true);
		screen.renderSprite(screen.width/2-screen.width/4, 0, new Sprite(1,screen.height,0x660066), true);
		screen.renderSprite(screen.width/2+screen.width/4, 0, new Sprite(1,screen.height,0x660066), true);
		screen.renderSprite(0, screen.height/2, new Sprite(screen.width,1,0xdd00dd), true);
		screen.renderSprite(0, screen.height/2-screen.height/4, new Sprite(screen.width,1,0x660066), true);
		screen.renderSprite(0, screen.height/2+screen.height/4, new Sprite(screen.width,1,0x660066), true);

		
		for(int i = 0; i < guis.size(); i++){
			guis.get(i).render();
		}
	}
}
