package com.mygdx.imageeditor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Rec2D rectangleMap;
	private Vector2 _screenSize;
	@Override
	public void create () {
		_screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		rectangleMap = new Rec2D(new Vector2(200,100), new Vector2(200,200),new Vector2(5,4), Color.RED);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(rectangleMap.RecTexture, rectangleMap.Position.x, rectangleMap.Position.y);
		if(rectangleMap.Position.x > (_screenSize.x - rectangleMap.Scale.x)) {
			rectangleMap.Velocity.x = rectangleMap.Velocity.x * -1;
			Random random = new Random();
			rectangleMap.changeColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
			
		}
		if(rectangleMap.Position.x < 0) {
			rectangleMap.Velocity.x = rectangleMap.Velocity.x * -1;
			Random random = new Random();
			rectangleMap.changeColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
		}
		if(rectangleMap.Position.y < 0) {
			rectangleMap.Velocity.y = rectangleMap.Velocity.y * -1;
			Random random = new Random();
			rectangleMap.changeColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
		}
		if(rectangleMap.Position.y > (_screenSize.y - rectangleMap.Scale.y)) {
			rectangleMap.Velocity.y = rectangleMap.Velocity.y * -1;
			Random random = new Random();
			rectangleMap.changeColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
		}
		rectangleMap.Position.add(rectangleMap.Velocity);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
