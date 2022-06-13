package com.th.system.utils;

public class Text {
	 public static String asciiToString(String text) {
	        StringBuilder builder = new StringBuilder();
	        for (int i = 0; i < text.length(); i++) {
	            if (text.charAt(i) == '1' && i < text.length() - 2) {
	                int code = Integer.parseInt(text.substring(i, i + 3));
	                builder.append((char) code);
	                i += 2;
	            } else if (i < text.length() - 1) {
	                int code = Integer.parseInt(text.substring(i, i + 2));
	                builder.append((char) code);
	                i += 1;
	            }
	        }
	        return builder.toString();
	    }
	
	public static void main(String[] args) {
		String string = "4768";
        System.out.println(asciiToString(string));
	}

}
