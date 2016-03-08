package cn.edu.csu.util;

/**
 *  数据库中托盘号以数字存储
 *  而客户端输入是是字母ABCD
 *  用此工具类进行转化
 */
public class SalverStringToIntAdapter {

	public static int parse(String salver){
		return salver.toUpperCase().charAt(0)-'A'+1;
	}
}
