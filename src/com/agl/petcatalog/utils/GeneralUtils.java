package com.agl.petcatalog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtils {
	public static boolean isStringcontainsSpecialChars(String string){
		Pattern p = Pattern.compile("\\W");
	    Matcher m = p.matcher(string);
	    return m.find();
	}
	
	public static String getCurrentDateTime(String format){
		//Example formats : dd/MM/yyyy hh mm ss, hh mm ss
		SimpleDateFormat sdfDate = new SimpleDateFormat(format);
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
		return strDate;
	}
	
	public static int getRandomNumberBetween(int min, int max){
		//excludes min and max
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        if(randomNumber == min){
            // Since the random number is between the min and max values, simply add 1
            return min + 1;
        }
        else{
            return randomNumber;
        }
    }
	
	public static int getRandomNumberFrom(int min, int max){
		//including min and max
        Random foo = new Random();
        int randomNumber = foo.nextInt((max + 1) - min) + min;
        return randomNumber;
    }
	
	public static void main(String[] args) {
		System.out.println(getCurrentDateTime("hh mm ss"));
		System.out.println(getRandomNumberBetween(1, 3));
		System.out.println(getRandomNumberFrom(1, 3));
	}
	
}
