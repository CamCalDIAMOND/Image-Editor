package com.mygdx.imageeditor;

import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

public class ImageInputOutput {
	private byte[] _fileHeader;
	private Pixmap _pixels;
	
	public static ImageInputOutput Instance;
	public ImageInputOutput() {
		Instance = this;
	}
	
	public Pixmap loadImage(String filePath) {
		byte[] fileBytes = Gdx.files.internal(filePath).readBytes();
		if(fileBytes[0]!= 'B' || fileBytes[1] != 'M') {
			System.out.println(filePath + "is NOT a bitmap image");
			return null;
		}
		
		int[] start = {fileBytes[10], fileBytes[11], fileBytes[12], fileBytes[13]};
		int[] widthBytes = {fileBytes[18], fileBytes[19], fileBytes[20], fileBytes[21]};
		int[] heightBytes = {fileBytes[22], fileBytes[23], fileBytes[24], fileBytes[25]};
		int[] bitsPerPixel = {fileBytes[28], fileBytes[29]};
		
		int[] fileIntData = Util.unsignBytes(fileBytes);
		
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		if(bytesPerPixel != 3) {System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return null;}


		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int r,g,b;
		int x = 0;
		int y = pixels.getHeight();

		_fileHeader = new byte[startPoint];
		
		for(int i = 0; i < startPoint; i++) {
			_fileHeader[i] = fileBytes[i];
		}
		
			for(int i = startPoint; i < fileIntData.length - 3; i += 3) {
			r = fileIntData[i];
			g = fileIntData[i+1];
			b = fileIntData[i+2];
			pixels.setColor(b/256f, g/256f, r/256f, 1);
			pixels.drawPixel(x, y);
			if(x >= pixels.getWidth()) {
				x = 0;
				y = y -1;
			}
			x += 1;
		}
		
		_pixels = pixels;
		return pixels;
	}
	public void saveImage(String filePath) throws IOException{
		byte[] color;
		FileOutputStream output = new FileOutputStream(filePath);
		Pixmap doodle = Util.scalePixmap(EditWindow.Instance._doodleMap, new Vector2(_pixels.getWidth(), _pixels.getHeight()));
		byte[] colorData = new byte[_pixels.getWidth() * _pixels.getHeight() * 3];
		int colorIndex = 0;
		System.out.println("Image Saved");
		for(int y = _pixels.getHeight() - 1; y >=0; y--) {
		for(int x = 0; x < _pixels.getWidth(); x++) {
			color = Util.intToSignedBytes(_pixels.getPixel(x, y));
			colorData[colorIndex] = color[2];
			colorData[colorIndex + 1] = color[1];
			colorData[colorIndex + 2] = color[0];
			colorIndex += 3;
		}
		}
		
//		Pixmap doodle = EditWindow.Instance._doodleMap;
		colorIndex = 0;
		
		for(int y = doodle.getHeight() - 1; y >=0; y--) {
		for(int x = 0; x < doodle.getWidth(); x++) {
			
			color = Util.intToSignedBytes(doodle.getPixel(x, y));
			if(color[3] != -1) {colorIndex +=3; continue;}
			
			colorData[colorIndex] = color[2];
			colorData[colorIndex + 1] = color[1];
			colorData[colorIndex + 2] = color[0];
			colorIndex += 3;
		}
		}
		
		
		
		output.write(_fileHeader);
		output.write(colorData);
		output.close();
	}
	
}
