package Google;

import java.util.*;

/*
 * 
�ڶ���, һ��array,rearrange��Ϊ��һ��array, ���ڸ���������array, ������ô�仯�ɵڶ���array��. 
ͦ�򵥵ľ�����Hashmap����...
Ȼ������,�����ڸ���ԭarray,Ҳ֪��������ô�仯����,�����������ڿ�����ԭarray����仯���array����? 
������Ҫrun���method�ö�α���k��, ��ô��������array��rearrange��k���Ժ�Ľ��? ����Ҿ��Ƶ�����LCM.

û���������~ ÿ��λ����,rearrange��Ϊԭʼ�����ֵ������ǲ�ͬ��. ��Ҫ�������λ�õ�����, Ȼ�������ǵ�LCM,��������rearrange��Ϊԭarray��������. 
Ȼ���ٰ�k�����LCM������ȡģ, �����������Ҫrearrange������

���ǵ��뷨�ǳ�����. ��������ÿ��λ�ö��ֱ��ҹ���,һ��һ��λ�õ�����뷨�ǳ���~���������array�ǳ���,�ǲ���ÿ��λ�ö�Ҫ��һ����?�����Ļ�ʱ�临�Ӷ����Ƕ�����? 
���ǰ����array����һ������,ȡLCM����˼����,����һ����array��˵,Ҫ�任���ٴ��ܱ��ԭarray? ����˵λ��һҪ3��,λ�ö�Ҫ2��,
λ����Ҫ5��,��ôLCM����30.Ҳ����˵,�任30/60/90/...������ȫ���ȥ. 
���Ҿ��Ȱ�k����30ȡģ,�������任29�ξ��ǽ����

�Ǿ���˵ÿ��λ���ϵ�Ԫ�ض�����һ���Ĺ��ɸı�λ�ã�Ȼ����һ��array����¼ÿ��λ���ϵ�Ԫ��Ҫ�������ٴ�arrange���Իص�ԭ����λ�ã�
Ȼ������array��LCM�����mod�¾Ϳ���������
��~ Ҳ���ǵ�һ�������ʾ��arr1�仯��arr2�ı仯λ�õ�array, ����rearrangeһ�εĹ��ɾͺ���. �ڶ����Ƿֱ����ÿ��position�ϱ仯������,Ȼ����������LCM.


LCM: Least Common Multiple
GCD: Greatest Common Divisor
 */
public class LCM {
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4};
		int[] format = {3, 2, 0, 1};
		//int[] array = {1, 2};
		//int[] format = {1, 0};
		int[] array1 = rearrange(array, format, 6);
		System.out.println(Arrays.toString(array1));
		int[] array2 = rearrangeLCM(array, format, 6);
		System.out.println(Arrays.toString(array2));
	}
	
	
	
	// O(kn) if k is a large number, this would not be efficient.
	public static int[] rearrange(int[] array, int[] format, int k) {
		int[] result = new int[array.length];
		for (int j = 0; j < k; j++) {
			for (int i = 0; i < array.length; i++) {
				result[format[i]] = array[i];
			}
			array = result;
			result = new int[array.length];
			//System.out.println(Arrays.toString(array));
		}
		return array;
	}
	
	public static int[] rearrangeLCM(int[] array, int[] format, int k) {
		int[] record = new int[array.length];
		int[] changedArray = new int[array.length];
		int[] stay = Arrays.copyOf(array, array.length);
		int count = 0;
		int changedTime = 0;
		while (count < array.length - 1) {
			changedTime++;
			for (int i = 0; i < array.length; i++) {
				changedArray[format[i]] = array[i];
				if (changedArray[format[i]] == stay[format[i]] && record[format[i]] == 0) {
					record[format[i]] = changedTime;
					count++;
				}
			}
			array = changedArray;
			changedArray = new int[array.length];
		}
		
		int lcm = record[0];
		for (int i = 1; i < record.length; i++) {
			lcm = getLCM(lcm, record[i]);
		}
		k = k % lcm;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < stay.length; j++) {
				changedArray[format[j]] = stay[j];
			}
			stay = changedArray;
			changedArray = new int[stay.length];
		}
		return stay;
	}
	
	private static int getLCM(int a, int b) {
		return (a * b) / getGCD(a, b);
	}
	
	private static int getGCD(int a, int b) {
		if (a == b) {
			return a;
		}
		if (a > b) {
			return getGCD(a - b, b);
		} else {
			return getGCD(a, b - a);
		}
	}
}
