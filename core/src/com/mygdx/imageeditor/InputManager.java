package com.mygdx.imageeditor;

import java.io.IOException;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.buttons.Button;

import Utility.CollisionManager;
import Utility.IClickable;
import Utility.IHoverable;
import Utility.ImageEditor;
import Utility.ImageInputOutput;

public class InputManager implements InputProcessor{
	public static InputManager Instance;
	public Array<Button> Buttons = new Array<Button>();
	public Array<IClickable> ClickableItem = new Array<IClickable>();
	public Array<IHoverable> HoverableItem = new Array<IHoverable>();
	private IClickable _currentlyClicked;
	public IHoverable _currentlyHoverable;
	private Button lastHoveredButton;
	private boolean _controlPressed;

	@Override
	public boolean keyDown(int keycode) { 
		if(keycode==Keys.CONTROL_LEFT) {
			_controlPressed = true;
		}
		if(_controlPressed && keycode == Keys.S) {
			try {
				if(ImageInputOutput.ImageFolderLocation != null) {
					ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.ImageFolderLocation + "\\output.bmp");

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}
		return false;
	}

	
	@Override
	public boolean keyUp(int keycode) {
	
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
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
		
		
		IClickable x = collisionManager.getClicked(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY));
		
		if(collisionManager.getClicked(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY))!= null) {
			x = collisionManager.getClicked(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY));
			x.onClickDown(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY));
		}
		_currentlyClicked = x;




		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(_currentlyClicked != null) {
			_currentlyClicked.onClickUp(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY));
		}
		
		return true;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		
		if(_currentlyClicked != null) {
			_currentlyClicked.onClickDragged(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y-screenY));
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = collisionManager.Instance.getHovered(new Vector2(screenX,ImageEditor.Instance.ScreenSize.y - screenY));
		
		if(collision != null) {
			collision.onHovered();
			_currentlyHoverable = collision;
			}
		
		if(_currentlyHoverable != null && _currentlyHoverable != collision) {
			_currentlyHoverable.onHoveredExit();
			}

		
		if(collision != _currentlyHoverable) {
		_currentlyHoverable = null;
	}
		

		
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
