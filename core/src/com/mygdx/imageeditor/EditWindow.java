package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable{
	public static Texture DoodleTexture;
	private Pixmap _doodleMap;
	public EditWindow(Vector2 scale, Vector2 position, Color color) {
		super(scale, position, color);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
		InputManager.getInstance().ClickableItem.add(this);
		
		
	}
	
	
	public void onClickDown(Vector2 mousePosition) {
		int reversedY = (int) (_doodleMap.getHeight() - mousePosition.y);
		int offSetX = (int) (ImageEditor.Instance._screenSize.x  - _doodleMap.getWidth() );

		
		_doodleMap.drawPixel((int)(mousePosition.x - offSetX),(int)(reversedY));
		DoodleTexture = new Texture(_doodleMap);
	}
	
	private void paintAtPosition(Vector2 worldPosition) {
		_doodleMap.drawPixel((int) (worldPosition.x - Position.x), (int)(Scale.y-worldPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}


	@Override
	public void onClickDragged(Vector2 mousePosition) {
		paintAtPosition(mousePosition);
	}


	@Override
	public void onClickUp(Vector2 mousePosition) {
		paintAtPosition(mousePosition);
		
	}

}
