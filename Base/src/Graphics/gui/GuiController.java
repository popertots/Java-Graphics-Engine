package Graphics.gui;

import java.util.ArrayList;
import java.util.List;

import Graphics.Screen;
import Graphics.Sprite;
import Input.Keyboard;
import Input.Mouse;


public class GuiController {
	
	private boolean isActive = true;
	private boolean activeTextBox = false;
	private Screen screen;
	private Keyboard key;
	private Mouse mouse;
	
	private static List<TextBox> TextBoxes = new ArrayList<TextBox>();
	private static List<GuiObject> GenericGuiObjects = new ArrayList<GuiObject>();

	private static List<Boolean> textBoxStates = new ArrayList<Boolean>();

	
	
	public GuiController(Screen screen, Keyboard key, Mouse mouse, GuiObject[] gui){
		this.screen=screen;
		this.key=key;
		this.mouse=mouse;
		for(int i = 0; i < gui.length; i++){
			gui[i].init(screen, key, mouse);
			if(gui[i] instanceof TextBox){
				TextBoxes.add((TextBox)gui[i]);
			}else{
				GenericGuiObjects.add(gui[i]);
			}
		}
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void update(){
		for(int i = 0; i < TextBoxes.size(); i++){
			TextBoxes.get(i).update();
		}
		for(int i = 0; i < GenericGuiObjects.size(); i++){
			GenericGuiObjects.get(i).update();
		}
	}
	
	public void render(){
		if(mouse.getB()==1){
			activeTextBox=false;
			for(int i = 0; i < TextBoxes.size(); i++){
				TextBoxes.get(i).deactivate();
				if(mouse.getX()>TextBoxes.get(i).getX()&&mouse.getX()<TextBoxes.get(i).getX()+TextBoxes.get(i).getWidth() && mouse.getY()>TextBoxes.get(i).getY()&&mouse.getY()<TextBoxes.get(i).getY()+TextBoxes.get(i).getHeight()){
					TextBoxes.get(i).activate();
					activeTextBox=true;
				}
			}
		}
		for(int i = 0; i < TextBoxes.size(); i++){
			TextBoxes.get(i).render();
		}
		for(int i = 0; i < GenericGuiObjects.size(); i++){
			GenericGuiObjects.get(i).render();
		}
	}
}
