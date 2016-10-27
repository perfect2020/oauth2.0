package org.base.utils;

import java.util.Random;

/**
 * 获取数字，字符，密码生成器
 * @author wangwr
 *
 */
public class RandomUtil {

	public static enum RandomType{
		DIGITAL,
		STRING,
		MIXING
	}
	
	private static char[] numchars = { '0', '1', '2', '3', '4', '5', '6', '7','8', '9'};
	
	private static char[] strchars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
	     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
	     'y', 'z', 'A', 'B','C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
	     'Z','-'};
	
	private static char[] numstrchars = { '0', '1', '2', '3', '4', '5', '6', '7',
	     '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
	     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
	     'y', 'z', 'A', 'B','C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
	     'Z','-'};
	
	/**
	 * 获取随机密码
	 * @param randomType 1：全数字 		2：全字符	3：数字加字符
	 * @param passLength 密码长度
	 * @return 随机密码
	 */
	public static String randomWords(RandomType randomType,int passLength){
		int type = randomType.ordinal()+1;
		String str = null;
		switch(type){
		case 1:
			str = randomWords(numchars,passLength);
			break;
		case 2:
			str = randomWords(strchars,passLength);
			break;
		case 3: 
			str = randomWords(numstrchars,passLength);
			break;	
		}
		return str;
	}
	
	private static String randomWords(char[] chars,int wordsLength){
		Random random = new Random();
		int randomlength = chars.length;
	    StringBuilder password = new StringBuilder("");// 保存生成密码的变量
	    for (int m = 0; m < wordsLength; m++) {// 内循环 从1开始到密码长度 正式开始生成密码
	       password.append(chars[random.nextInt(randomlength)]);// 为密码变量随机增加上面字符中的一个
	    }
	    return password.toString();// 将生成出来的密码返回
	}
}
