package cn.edu.csu.common;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class FontFactory {
	
	public static List<String> FontNames = null ;
	
	public static List<Font> FontSet = null;
	
	public static int FontSizeVerification = 30;
	
	static {
		FontFactory.FontSet = new ArrayList<Font>() ;
		FontFactory.FontNames = new ArrayList<String>();
		FontFactory.FontNames.add("Consolas");
		FontFactory.FontNames.add("Aldus LT Std Roman");
		FontFactory.FontNames.add("Bell Gothic Std Light");
		FontFactory.FontNames.add("Cooper Std Black");
		FontFactory.FontNames.add("Georgia");
		FontFactory.FontNames.add("Tekton Pro");
		for (int i = 0; i < FontFactory.FontNames.size(); i++) {
			Font f = new Font(FontFactory.FontNames.get(i), Font.PLAIN, FontFactory.FontSizeVerification);
			FontFactory.FontSet.add(f);
		}
	}

}
