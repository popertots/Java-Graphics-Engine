package Graphics.gui;

import java.util.ArrayList;
import java.util.List;

import Graphics.Screen;
import Input.Keyboard;
import Input.Mouse;


public class GuiController {
	
	private boolean isActive = true;
	private Screen screen;
	private Keyboard key;
	private Mouse mouse;
	private Object[] output;
	
	private List<TextBox> TextBoxes = new ArrayList<TextBox>();
	private List<Button> Buttons = new ArrayList<Button>();
	private List<CheckBox> CheckBoxes = new ArrayList<CheckBox>();
	private List<RadioButton> RadioButtons = new ArrayList<RadioButton>();
	
	private List<GuiObject> GenericGuiObjects = new ArrayList<GuiObject>();
	
	public GuiController(Screen screen, Keyboard key, Mouse mouse, GuiObject[] gui){
		this.screen=screen;
		this.key=key;
		this.mouse=mouse;
		output=new Object[gui.length-1];
		for(int i = 0; i < gui.length; i++){
			gui[i].init(screen, key, mouse);
			if(gui[i] instanceof TextBox){
				TextBoxes.add((TextBox)gui[i]);
			}else if(gui[i] instanceof Button){
				Buttons.add((Button)gui[i]);
			}else if(gui[i] instanceof CheckBox){
				CheckBoxes.add((CheckBox)gui[i]);
			}else if(gui[i] instanceof RadioButton){
				RadioButtons.add((RadioButton)gui[i]);
			}else{
				GenericGuiObjects.add(gui[i]);
			}
		}
	}
	
	public Object[] getOutput(){
		fillOutput();
		return output;
	}
	
	private void fillOutput(){
		for(int i = 0; i < TextBoxes.size(); i++){
			output[i]=TextBoxes.get(i).getOutput();
		}
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void update(){
		updateTextBoxes();
		updateButtons();
		updateCheckBoxes();
		updateRadioButtons();
		updateGenericGuiObjects();
	}
	
	private void updateTextBoxes(){
		for(int i = 0; i < TextBoxes.size(); i++){
			if(mouse.getB()==1){
				TextBoxes.get(i).deactivate();
				if(mouse.getX()>TextBoxes.get(i).getX()&&mouse.getX()<TextBoxes.get(i).getX()+TextBoxes.get(i).getWidth()&&mouse.getY()>TextBoxes.get(i).getY()&&mouse.getY()<TextBoxes.get(i).getY()+TextBoxes.get(i).getHeight()){
					TextBoxes.get(i).activate();
				}
			}
			if(TextBoxes.get(i).isActive()){
				TextBoxes.get(i).update();
			}
		}
	}
	
	private void updateButtons(){
		for(int i = 0; i < Buttons.size(); i++){
			Buttons.get(i).deactivate();
			if(mouse.getX()>Buttons.get(i).getX()&&mouse.getX()<Buttons.get(i).getX()+Buttons.get(i).getWidth()&&mouse.getY()>Buttons.get(i).getY()&&mouse.getY()<Buttons.get(i).getY()+Buttons.get(i).getHeight()){
				Buttons.get(i).activate();
			}
			Buttons.get(i).update();
		}
	}

	private void updateCheckBoxes(){
		for(int i = 0; i < CheckBoxes.size(); i++){
			if(mouse.getX()>CheckBoxes.get(i).getX()&&mouse.getX()<CheckBoxes.get(i).getX()+CheckBoxes.get(i).getWidth()&&mouse.getY()>CheckBoxes.get(i).getY()&&mouse.getY()<CheckBoxes.get(i).getY()+CheckBoxes.get(i).getHeight()){
				if(mouse.clicked()){
				}
				if(!CheckBoxes.get(i).isActive()&&mouse.clicked())
					CheckBoxes.get(i).activate();
				else if(CheckBoxes.get(i).isActive()&&mouse.clicked())
					CheckBoxes.get(i).deactivate();
			}
			CheckBoxes.get(i).update();
		}
	}
	
	private void updateRadioButtons(){
		for(int i = 0; i < RadioButtons.size(); i++){
			if(mouse.getX()>RadioButtons.get(i).getX()&&mouse.getX()<RadioButtons.get(i).getX()+RadioButtons.get(i).getWidth()&&mouse.getY()>RadioButtons.get(i).getY()&&mouse.getY()<RadioButtons.get(i).getY()+RadioButtons.get(i).getHeight()){
				if(mouse.getB()==1){
					for(int j = 0; j < RadioButtons.size(); j++){
						RadioButtons.get(j).deactivate();
					}
					RadioButtons.get(i).activate();
				}
			}
			RadioButtons.get(i).update();
		}
	}
	
	private void updateGenericGuiObjects(){
		for(int i = 0; i < GenericGuiObjects.size(); i++){
			GenericGuiObjects.get(i).update();
		}
	}
	
	
	public void render(){
		for(int i = 0; i < TextBoxes.size(); i++){
			TextBoxes.get(i).render();
		}
		for(int i = 0; i < Buttons.size(); i++){
			Buttons.get(i).render();
		}
		for(int i = 0; i < CheckBoxes.size(); i++){
			CheckBoxes.get(i).render();
		}
		for(int i = 0; i < RadioButtons.size(); i++){
			RadioButtons.get(i).render();
		}
		for(int i = 0; i < GenericGuiObjects.size(); i++){
			GenericGuiObjects.get(i).render();
		}
	}
}