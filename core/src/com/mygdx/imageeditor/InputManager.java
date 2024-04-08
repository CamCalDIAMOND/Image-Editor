package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor{
	public static InputManager Instance;
	public Array<Button> Buttons = new Array<Button>();

	@Override
	public boolean keyDown(int keycode) { 
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public CollisionManager collisionManager;
	
	public InputManager() {
		collisionManager = new CollisionManager();
	}

	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		Rec2D collision = CollisionManager.Instance.getCollision(new Vector2(screenX,ImageEditor.Instance._screenSize.y - screenY));
//		if(collision == ImageEditor.Instance.rectangle) {
//			System.out.println("Button 1 Pressed");
//		}
//		if(collision == ImageEditor.Instance.rectangle2) {
//			System.out.println("Button 2 Pressed");
//		}
		
		if(collisionManager.getCollision(new Vector2(screenX,ImageEditor.Instance._screenSize.y - screenY))!= null) {
			Button.onPressed();
			
		}


		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static InputManager getInstance() {
		
		if(Instance == null) {
			Instance = new InputManager();
		}
		return Instance;
	}

}
