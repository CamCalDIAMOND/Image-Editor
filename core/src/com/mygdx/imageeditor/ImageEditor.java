package com.mygdx.imageeditor;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;

	SpriteBatch batch;
	Texture img;
	
	public Vector2 _screenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public EditWindow editWindow;

	Button button1;
	@Override
	public void create () {
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);

		Instance = this;
		new ImageInputOutput();
//		Pixmap editMap = ImageInputOutput.Instance.loadImage("snail.bmp");
		
		batch = new SpriteBatch();
		_screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		Vector2 editWindowSize = new Vector2(500, _screenSize.y-50);
		
		editWindow = new EditWindow(	
				editWindowSize, new Vector2(_screenSize.x-editWindowSize.x,0));
		
		Vector2 rectangleScale = new Vector2(50,50);
		
		button1 = new Button(
				rectangleScale,
				new Vector2(_screenSize.x/2 - rectangleScale.x*5, _screenSize.y/2f-rectangleScale.y*2f),Color.ORANGE);

		CollisionManager collisionManager = new CollisionManager();

		Util.testIntToSignedBytes();


	}
	

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		Rec2D rec;
		
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			
		}

		

		
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x, editWindow.Scale.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public static ImageEditor getInstance() {
		
		if(Instance == null) {
			Instance = new ImageEditor();
		}
		return Instance;
	}
	
	public void filesImported(String[] filePaths) {
		
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if(map == null) {
			return;
		}
		editWindow.RecTexture = new Texture(map);
	}
	
}


