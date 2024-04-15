package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
	public CollisionManager Instance;
	
	public CollisionManager() {
		Instance = this;

	}
	
	public static IHoverable getHovered(Vector2 coordinates) {
		Rec2D hovered;
		for(int i = 0; i < InputManager.Instance.HoverableItem.size; i++) {
			hovered = (Rec2D) InputManager.Instance.HoverableItem.get(i);
		if(coordinates.x > hovered.Position.x 
				&& coordinates.x <(hovered.Position.x)+hovered.Scale.x) {
					if(coordinates.y > hovered.Position.y 
							&& coordinates.y <(hovered.Position.y + hovered.Scale.y)) {
							return (IHoverable) hovered;
		}
		}
		}
		return null;
		
		
		
	}
	public IClickable getClicked(Vector2 coordinates) {

		Rec2D clicked;
		
		for(int i = 0; i < InputManager.Instance.ClickableItem.size; i++) {
			clicked = (Rec2D) InputManager.Instance.ClickableItem.get(i);
		if(coordinates.x > clicked.Position.x 
				&& coordinates.x < (clicked.Position.x + clicked.Scale.x)) {
					if(coordinates.y > clicked.Position.y 
							&& coordinates.y <(clicked.Position.y + clicked.Scale.y)) {
							return (IClickable) clicked;
		}
		}
		}
		return null;
		
	}
	
	
	public Button getCollision(Vector2 coordinates) {
		Button iteratingButton;
		for(int i = 0; i < InputManager.Instance.Buttons.size; i++) {
			iteratingButton = InputManager.Instance.Buttons.get(i);
		if(coordinates.x > iteratingButton.Position.x) {
			if(coordinates.x < (iteratingButton.Position.x) + iteratingButton.Scale.x) {
					if(coordinates.y > iteratingButton.Position.y) {
						if(coordinates.y < (iteratingButton.Position.y + iteratingButton.Scale.y)) {
							return iteratingButton;
		}
		}
		}
		}
		}
		
		return null;
	}
}
