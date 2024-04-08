package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;




public class Button extends Rec2D{
	private static int _buttonCount;
	private int _buttonNumber;
	public Button(Vector2 scale, Vector2 position, Color color) {
		super(scale, position, color);
		_buttonCount+= 1;
		_buttonNumber = _buttonCount;
		InputManager.getInstance().Buttons.add(this);
	}
	
	public static void onPressed(){
		
	}
	
	public int getButtonNumber() {
		return _buttonNumber;
	}

}