package com.mygdx.imageeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class ImageInputOutput {
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
		return pixels;
	}
}
