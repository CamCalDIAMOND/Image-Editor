package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.EditWindow;

public class ColorButton extends Button{

	public ColorButton(Vector2 scale, Vector2 position, Color color) {
		super(scale, position, color);
	}
	
	public void onClickUp(Vector2 clickPosition) {
		super.onClickUp(clickPosition);
		EditWindow.Instance.DrawColor = _startColor;
		EditWindow.Instance._doodleMap.setColor(_startColor);
	}

}
