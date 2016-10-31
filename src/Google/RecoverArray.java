package Google;

import java.util.*;

/*
 * ��һ��, ��һ��array����[4,2,1,3,5],�������array��������������һ���µ�array => ÿ��������ԭarray��, ������ߵ����б������number�ĸ���,����[0,1,2,1,0].
 *  ��Ŀ�����ڸ������[0,1,2,1,0]Ҫ��ԭarray, ԭ��array��range��1~n
 */
public class RecoverArray {
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 1, 0};
		array = recoverArray(array, 5);
		System.out.println(Arrays.toString(array));
	}
	public static int[] recoverArray(int[] array, int n) {
		if (array == null || array.length == 0) {
			return null;
		}
		int[] nums = new int[n];
		for (int i = array.length - 1; i >= 0; i--) {
			int count = array[i] + 1;
			int full = nums.length;
			
			while (count > 0) {
				full--;
				if (nums[full] == 0) {
					count--;
				}
			}
			nums[full] = 1;
			array[i] = full + 1;
		}
		return array;
	}
}
