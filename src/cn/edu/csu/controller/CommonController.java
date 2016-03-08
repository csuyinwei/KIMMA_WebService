package cn.edu.csu.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import cn.edu.csu.common.FontFactory;
import cn.edu.csu.common.RandomVerification;

@Controller
@SessionAttributes({"admin"})
public class CommonController {
	
	@RequestMapping("/index")
	public String toIndex(Model model) {
		return "../index";
	}
	
	
	public static int VerificationWidth = 1600;
	public static int VerificationHeight = 40;
	public static final String RandomStringList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static int VerificationPadding = 10 ;
	
	@RequestMapping("/randomverification")
	public void randomverification(HttpServletResponse response, HttpServletRequest request, Model model) {
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		BufferedImage image = new BufferedImage(RandomVerification.VerificationWidth, 
									RandomVerification.VerificationHeight, 
									BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(new Color(192, 192, 192));
		g.fillRect(0, 0, RandomVerification.VerificationWidth, RandomVerification.VerificationHeight);
		g.setFont(FontFactory.FontSet.get(random.nextInt(FontFactory.FontSet.size())));
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		int lineHeight = random.nextInt(6) ;
		int sy = RandomVerification.VerificationHeight / 5 + 
				random.nextInt(RandomVerification.VerificationHeight * 3 / 5) ;
		g.fillRect(0, sy, VerificationWidth, lineHeight);
		int pointCount = random.nextInt(100) + 50;
		for (int i = 0; i < pointCount; i++)
		{
			g.setColor(getRandColor(64,224));
			int x = random.nextInt(RandomVerification.VerificationWidth);
			int y = random.nextInt(RandomVerification.VerificationHeight);
			g.fillArc(x, y, 3, 3, 0, 360);
		}
		String sRand="";
		int width = (RandomVerification.VerificationWidth - 
				(2 * RandomVerification.VerificationPadding)) / 4;
		int height = RandomVerification.VerificationHeight;
		int widthOffset = width * 1 / 5;
		int heightOffset = height * 1 / 10;
		int startx = (width - g.getFont().getSize()) / 2;
		int starty = height * 2 / 3;
		startx += RandomVerification.VerificationPadding ;
		for (int i=0;i<4;i++){
			String rand = "" + RandomStringList.charAt(random.nextInt(RandomStringList.length()));
			sRand += rand;
			g.setColor(new Color(32+random.nextInt(224), random.nextInt(32), random.nextInt(224)));
			int x = startx + (-widthOffset) + random.nextInt(2 * widthOffset);
			int y = starty + (-heightOffset) + random.nextInt(2 * heightOffset) ;
			g.drawString(rand, x, y);
			startx += width ;
		}
		request.getSession().setAttribute("captcha", sRand.toLowerCase());
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	Color getRandColor(int fc,int bc)
	{
		Random random = new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}

}
