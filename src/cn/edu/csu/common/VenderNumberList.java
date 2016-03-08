package cn.edu.csu.common;

import java.util.List;

/**
 * 用于和手机端通信，使得@ResposeBody注解返回List<String>时字段有名字为venderNumbers
 */
public class VenderNumberList {
	List<String> venderNumbers;

	public List<String> getVenderNumbers() {
		return venderNumbers;
	}

	public void setVenderNumbers(List<String> venderNumbers) {
		this.venderNumbers = venderNumbers;
	}
}
