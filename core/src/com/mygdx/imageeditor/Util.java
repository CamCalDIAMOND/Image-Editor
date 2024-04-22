package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(int[] bytes) {
		int result = 0;
		for(int i = 0; i < bytes.length; i++) {
			result += (int) bytes[i] << (8 * i);
		}
//		unsignBytes(bytes);
		return result;
	}
	
	public static byte[] intToSignedBytes(int value) {
		byte [] result = new byte[4];
		int x = value;
		
		result [0] =  (byte) (x >> 24); 
		result [1] = (byte) (value << 8 >> 24);
		result[2] = (byte)(value << 16 >> 24);
		result[3] = (byte)(value << 24 >> 24);
//		result [1] = (byte) (value << 24);
//		result [2] = (byte) (value >> 16);
		

		return result;
	}
	
	
	public static void testIntToSignedBytes() {
		byte[] testResults = intToSignedBytes(543152314);
		int[] expectedResults = {32, 95, -40, -70};
		for(int i = 0; i < testResults.length; i++) {
			if((int) testResults[i] != expectedResults[i])
		 System.out.println("TEST FAILED! INDEX " + i + " IS " + testResults[i] + " EXPECTED: " + expectedResults[i]);
		}
		}

		public static Pixmap scalePixmap(Pixmap source, Vector2 desiredSize) {
			int targetWidth = (int) desiredSize.x;
			int targetHeight = (int) desiredSize.y;
			
			
			Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
			
			float scaleX = (float) source.getWidth() / targetWidth;
			float scaleY = (float) source.getHeight() / targetHeight;
			
			for(int targetX = 0; targetX < targetWidth; targetX ++) {
				for(int targetY = 0; targetY < targetHeight; targetY ++) {
					int sourceX = Math.round(targetX * scaleX);
					int sourceY = Math.round(targetY * scaleY);
					
//					sourceX = Math.min(sourceX, source.getWidth() -1);
//					sourceY = Math.min(targetY, source.getHeight() - 1);
					
					int color = source.getPixel(sourceX, sourceY);
					target.drawPixel(targetX, targetY, color);
				}
			}
			return target;
		}
		
		
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		int x;
		for(int i = 0; i < bytes.length; i++) {
			
			if(bytes[i] >= 0) {
				ints[i] = bytes[i];
			}
			
			else {
					x = bytes[i] + 129;
					x = x + 127;
					ints[i] = x;
					x = 0;

				}
		}
		
		return ints;
	}
}
