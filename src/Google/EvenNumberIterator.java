package Google;

public class EvenNumberIterator {
	
	/*
	 * ʵ��һ��iterator, input ��һ��array{3, 8, 0, 12, 2, 9}, ϣ������� {8, 8, 8, 9, 9},   
	 * Ҳ����eventh number���� ��Ƶ�� oddth number ����ʣ� {3, 8, 12, 0, 2, 9}�� ����3��8�� 0��12�� 2��9. 
����ü���������벻��array, �ø�List<Integer> �򵥺öࡣ 
	 */
	
	public static void main(String[] args) {
		int array[] = {3, 8, 0, 12, 2, 9};
		EvenNumberIterator e = new EvenNumberIterator(array);
		while (e.hasNext()) {
			System.out.println(e.next());
		}
	}
	
	int nums[];
	int[] output;
	int globalIndex;
	EvenNumberIterator(int[] nums) {
		this.nums = nums;
		int count = 0;
		for (int i = 0; i < nums.length; i = i + 2) {
			count += nums[i];
		}
		output = new int[count];
		int index = 0;
		for (int i = 0; i < count; i++) {
			while (nums[index] == 0) {
				index = index + 2;
			}
			nums[index]--;
			output[i] = nums[index + 1];
		}
		globalIndex = 0;
	}
	boolean hasNext() {
		if (globalIndex >= output.length) {
			return false;
		} else {
			return true;
		}
	}
	int next() {
		int index = globalIndex;
		globalIndex++;
		return output[index];
	}
}
