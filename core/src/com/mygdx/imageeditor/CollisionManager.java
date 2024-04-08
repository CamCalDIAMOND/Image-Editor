package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
	public static CollisionManager Instance;
	
	public CollisionManager() {
		Instance = this;

	}
	public Button getCollision(Vector2 coordinates) {
		Button iteratingButton;
		for(int i = 0; i < InputManager.Instance.Buttons.size; i++) {
			iteratingButton = InputManager.Instance.Buttons.get(i);
//			System.out.println(iteratingButton.Position.x);
//			System.out.println(iteratingButton.Position.x  + iteratingButton.Scale.x);
//			System.out.println(iteratingButton.Position.y);
//			System.out.println(iteratingButton.Position.y + iteratingButton.Scale.y);
		if(coordinates.x > iteratingButton.Position.x) {
			if(coordinates.x < (iteratingButton.Position.x) + iteratingButton.Scale.x * 2) {
					if(coordinates.y > iteratingButton.Position.y) {
						if(coordinates.y < (iteratingButton.Position.y + iteratingButton.Scale.y * 2 )) {
							System.out.println("Pressed Button " + (i + 1));
							return iteratingButton;
		}
		}
		}
		}
		}
		
		return null;
	}
}
