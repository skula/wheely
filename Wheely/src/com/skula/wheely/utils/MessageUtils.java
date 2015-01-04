package com.skula.wheely.utils;

public class MessageUtils {
	public static String format(int angle, int speed){
		String angleStr = "";
		String speedStr = "";
		if(angle<0){
			angle *= -1;
		}
		
		if(angle<100){
			if(angle<10){
				angleStr = "00" + angle;				
			}else{
				angleStr = "0" + angle;
			}
		}else{
			angleStr = angle + "";
		}
		
		if(speed<0){
			speed *= -1;
			speedStr = "-";
			if(speed<100){
				if(speed<10){
					speedStr += "00" +speed;
				}else{
					speedStr += "0" +speed;
				}
			}else{
				speedStr += speed;
			}
		}else if(speed == 0){
			speedStr = "0000";
		}else{
			speedStr = "0";
			if(speed<100){
				if(speed<10){
					speedStr += "00" +speed;
				}else{
					speedStr += "0" +speed;
				}
			}else{
				speedStr += speed;
			}
		}
		
		return angleStr + ":" + speedStr;
	}
	
}
