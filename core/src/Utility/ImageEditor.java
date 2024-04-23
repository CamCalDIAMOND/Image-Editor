package Utility;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;
import com.mygdx.imageeditor.EditWindow;
import com.mygdx.imageeditor.InputManager;
import com.mygdx.imageeditor.Rec2D;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	private BitmapFont _font;

	SpriteBatch batch;
	Texture img;
	
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public EditWindow editWindow;

	Button button1;
	@Override
	public void create () {
		Instance = this;
		
		
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
		new ImageInputOutput();
		
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y-25);
		
		editWindow = new EditWindow(	
				editWindowSize, new Vector2(ScreenSize.x-editWindowSize.x,0));
		
		Vector2 rectangleScale = new Vector2(50,50);
		

		ColorButton button = new ColorButton(new Vector2(42,42), Vector2.Zero, Color.RED);
		
		ColorButton button1 = new ColorButton(new Vector2(42,42), new Vector2(42,0), Color.ORANGE);
		ColorButton button2 = new ColorButton(new Vector2(42,42), new Vector2(42,42), Color.YELLOW);
		ColorButton button3 = new ColorButton(new Vector2(42,42), new Vector2(0,42), Color.GREEN);
		ColorButton button4 = new ColorButton(new Vector2(42,42), new Vector2(0,84), Color.BLUE);
		ColorButton button5 = new ColorButton(new Vector2(42,42), new Vector2(42,84), Color.PURPLE);
		ColorButton button6= new ColorButton(new Vector2(42,42), new Vector2(0,126), Color.PINK);
		ColorButton button7 = new ColorButton(new Vector2(42,42), new Vector2(42,126), Color.BROWN);
		
		
		ColorButton button9 = new ColorButton(new Vector2(42,42), new Vector2(0,168), Color.MAROON);
		ColorButton button10 = new ColorButton(new Vector2(42,42), new Vector2(42,168), Color.CYAN);
		ColorButton button11 = new ColorButton(new Vector2(42,42), new Vector2(0,210), Color.CORAL);
		ColorButton button12 = new ColorButton(new Vector2(42,42), new Vector2(42,210), Color.FOREST);
		ColorButton button13 = new ColorButton(new Vector2(42,42), new Vector2(0,252), Color.CHARTREUSE);
		ColorButton button14 = new ColorButton(new Vector2(42,42), new Vector2(42,252), Color.OLIVE);


		new SaveButton(new Vector2(75,25), new Vector2(0, ScreenSize.y-25), Color.GRAY);
		new ExitButton(new Vector2(75,25), new Vector2(75, ScreenSize.y-25), Color.GRAY);
		new ClearDoodleButton(new Vector2(75,25), new Vector2(150, ScreenSize.y-25), Color.GRAY);


		CollisionManager collisionManager = new CollisionManager();

	}
	

	@Override
	
	
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		Rec2D rec;
		Button button = null;
		

		
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			
		}



		
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x, editWindow.Scale.y);
		

		for(int x = 0; x < Rectangles.size; x++) {
			rec = Rectangles.get(x);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		
		for(int i = 0; i < Rectangles.size; i++) {
			
			rec = Rectangles.get(i);
			
			if(rec instanceof Button) {
				button = (Button) rec;
			
			if(button.ButtonText == null) continue;
			
			_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
					 button.Scale.x, Align.center, false);
		}
		}
		
		
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


