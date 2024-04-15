package com.mygdx.imageeditor;

public class Util {
	public static int bytesToInt(int[] bytes) {
		int result = 0;
		for(int i = 0; i < bytes.length; i++) {
			result += (int) bytes[i] << (8 * i);
		}
//		unsignBytes(bytes);
		return result;
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
