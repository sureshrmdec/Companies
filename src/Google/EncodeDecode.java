package Google;

/*
 * ����ĵ�����ֹ���Stringѹ���������������, ��ڵ�ʱ����û�кú�д��һ��.��һ��String����"abcdfffffffxyz", д����methods, encode��decode. 
 * encode���Ǳ���"fffffff"���"7xf",decode����Ҫ��Ϊԭ�ַ���.��˵"ff"��ô��,��˵���"2xf"�㲻���ø�������? �Ҳ�������,Ӧ����encoded���StringҪ��ԭ���Ķ�,
 * ��ȻΪɶҪencode,�Ŀ��������������...Ȼ��������,���ԭString��������"5xt"���ֽṹ, decode�����޷���������?��˵�ܸ�����������������,���ǲ��ù���,
 * һ��������,��д��.. д���Ժ������������ԭString��������"5xt"���ֽṹ,��encodeӦ����ô����? �Ҿ�ɵ��... 
 * ��Ϊһֱ����encode����ַ�������һ��Ҫ��ԭ���Ķ�,���Ը����벻������Ҫ�Ľⷨ. ˵�������ַ�������������, ������hint˵,Ҫ���и�"1xt"����������ô����? 
 * ��ʱ�Զ����������... ��ʵ��Ҫ�������"1xt"���ֽṹ, ����ԭString����"5xq", ��encode��Ϊ"1x51xx1xq"�ͺ���. �������ַ���Υ����encode��Ҫ��̵�rule,
 * ����������û�����......
 * �������˺ö������, ���һ����"1aaaaa"���������ô��, ��˵"1x15xa". ��˵����6���ַ�,�ܲ���ֻ��5��? ʵ���벻����,��ʱ�������С�������,
 * �������͹���������˵,��ʵ����1a��aaaa������encode�ͺ�...
 */
public class EncodeDecode {
	
	public static void main(String[] args) {
		String s = "abccdddfffffffxyzzz";
		String encode = encode(s);
		System.out.println(encode);
		String decode = decode(encode);
		System.out.println(decode);
	}
	
	public static String encode(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		String result = "";
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i != 0 && s.charAt(i) != s.charAt(i - 1)) {  //new char
				if (count > 2) {
					result += (count + "x" + s.charAt(i - 1));
				} else {
					for (int j = 0; j < count; j++) {
						result += s.charAt(i - 1);
					}
				}
				count = 1;
			} else {
				count++;
			}
		}
		if (count > 2) {
			result += (count + "x" + s.charAt(s.length() - 1));
		} else {
			for (int j = 0; j < count; j++) {
				result += s.charAt(s.length() - 1);
			}
		}
		return result;
	}
	
	
	public static String decode(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		String result = "";
		int i = 0;
		String strNum = "";
		while (i < s.length()) {
			char cur = s.charAt(i);
			if (Character.isDigit(cur)) {
				strNum += cur;
				i++;
			} else if (i != 0 && !Character.isDigit(cur) && Character.isDigit(s.charAt(i - 1))) {
				int num = Integer.parseInt(strNum);
				for (int j = 0; j < num; j++) {
					result += s.charAt(i + 1);
				}
				strNum = "";
				i = i + 2;
			} else {
				result += cur;
				i++;
			}
		}
		return result;
	}
}
