package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.EditWindow;

public class ClearDoodleButton extends Button{

	public ClearDoodleButton(Vector2 scale, Vector2 position, Color color) {
		super(scale, position, color);
		ButtonText = "Clear";
	}	
	public void onClickUp(Vector2 clickPosition) {
		super.onClickUp(clickPosition);
		EditWindow edit = EditWindow.Instance;
		
		edit._doodleMap = new Pixmap((int) edit.Scale.x, (int) edit.Scale.y, Format.RGBA8888);
		edit._doodleMap.setColor(edit.DrawColor);
		edit.DoodleTexture = new Texture(edit._doodleMap);
	}
}
