package cn.edu.csu.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RandomVerification
 */
@WebServlet("/randomverification")
public class RandomVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ��֤����
	public static int VerificationWidth = 100;
	// ��֤��߶�
	public static int VerificationHeight = 40;
	// ������֤����ַ���
	public static final String RandomStringList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	// ������֤��ʱ���ұ������µı߾��С
	public static int VerificationPadding = 10 ;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ҳ�治����
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		// �����հ�ͼƬ
		BufferedImage image = new BufferedImage(RandomVerification.VerificationWidth, 
									RandomVerification.VerificationHeight, 
									BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(new Color(192, 192, 192));
		g.fillRect(0, 0, RandomVerification.VerificationWidth, RandomVerification.VerificationHeight);
		g.setFont(FontFactory.FontSet.get(random.nextInt(FontFactory.FontSet.size())));
		
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		// �����ˮƽ��
		int lineHeight = random.nextInt(6) ;
		int sy = RandomVerification.VerificationHeight / 5 + 
				random.nextInt(RandomVerification.VerificationHeight * 3 / 5) ;
		g.fillRect(0, sy, VerificationWidth, lineHeight);
		
		// ���������ĸ��� (100 ~ 150 ����)
		int pointCount = random.nextInt(100) + 50;
		// �������
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
//		int starty = (height - g.getFont().getSize()) / 2;
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
		ImageIO.write(image, "JPEG", response.getOutputStream()); 
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
