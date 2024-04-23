package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.InputManager;
import com.mygdx.imageeditor.Rec2D;

import Utility.IClickable;
import Utility.IHoverable;




public class Button extends Rec2D implements IClickable, IHoverable{ 
	public String ButtonText;
	protected Color _recColor;
	protected Color _startColor;
	public enum ButtonState{Clicked,Hovered,None};
	private ButtonState _state;
	public Button(Vector2 scale, Vector2 position, Color color) {
		super(scale, position, color);
		_state = ButtonState.None;

		_recColor = color;
		_startColor = _recColor;
		InputManager.Instance.ClickableItem.add(this);
		InputManager.Instance.HoverableItem.add(this);
	}
	
	public void onClickDown(Vector2 mousePosition){
		
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r /4f , _startColor.g /4f, _startColor.b/4f, 1);
		changeColor(_recColor);
		generateTexture();
	}
	
	public void onClickUp(Vector2 mousePosition) {
		_state = ButtonState.None;
		_recColor = new Color(_startColor.r /2f , _startColor.g /2f, _startColor.b/2f, 1);
		changeColor(_recColor);
		generateTexture();
	}
	
	public void onHovered(Vector2 mousePosition) {
		if(_state != ButtonState.Clicked) {
			_state = ButtonState.Hovered;
			_recColor = new Color(_startColor.r /2f , _startColor.g /2f, _startColor.b/2f, 1);
			changeColor(_recColor);
			generateTexture();
		}

	}
	public void onHoveredExit(Vector2 mousePosition) {
		_state = ButtonState.None;
		_recColor = _startColor;
		changeColor(_startColor);
		generateTexture();
	}

	@Override
	public void onHovered() {
		if(_state != ButtonState.Clicked) {
			_state = ButtonState.Hovered;
			_recColor = new Color(_startColor.r /2f , _startColor.g /2f, _startColor.b/2f, 1);
			changeColor(_recColor);
			generateTexture();
		}		
	}

	@Override
	public void onHoveredExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		changeColor(_startColor);
		generateTexture();
	
	}

	@Override
	public void onClickDragged(Vector2 clickPosition) {
		// TODO Auto-generated method stub
		
	}





}
