package Google;

import java.util.*;

/*
 * ��һ��, һ����ά���������һ����. ��Χ���Ǻ�, ���������ϲ�ͨ��Pacific, �Ҳ���²�ͨ��Atlantic. ÿ�����ֶ��������Ǹ�λ�õĺ��θ߶�.
 *  ����������, ��ֻ�дӺ��θߵĵض������򺣰εͻ���һ���ĵض�.
 *  ���ص��ϵķ�ˮ��ĵ�, ������ĳ��/ĳЩ����, ��ˮ��������Pacific, ��������Atlantic.
 *  
 *  
 *  �ҵķ���Ҳ��DP+DFS,ֻɨһ��. ����������HashSet�ֱ����ڴ���ܹ�����Pacific��Atlantic�ĵ������. Pacific��Atlantic���������DFS��ͬʱ��.
��DFS��ʱ��, �ȷֱ�recursion���������ĸ�����, ���hashset�����¼����ֱ�ӷ��ؽ��(��Ȼ�����boolean[] visitedȥ��¼��ǰ����û�б�ɨ���϶�����,
 ����Ԫ��̫��, ��ʱʱ��ʵ�ڲ�����...). Ȼ���жϵ�ǰ���Ƿ�������Pacific��Atlantic, �����, �ֱ��¼��Hashset��.
���ֻҪ������Щ��������HashSet���涼���־ͺ���.
����ÿ����ֻ��ɨһ��, �ռ临�ӶȾ��Ƿ��Ϸֱ��ܹ�����Pacific��Atlantic���������..

 *  
 */
public class PacificAtlantic {

	public static void main(String[] args) {
		int[][] matrix = {{2, 0, 4, 0, 9}, {4, 2, 3, 2, 0}, {0, 5, 0, 4, 2}, {2, 4, 5, 2, 3}, {2, 3, 0, 3, 0}};
		List<int[]> list = PacificAtlantic(matrix);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(Arrays.toString(list.get(i)));
		}
	}
	static int[] xOffset = {0, 0, -1, 1};
	static int[] yOffset = {-1, 1, 0, 0};
	public static List<int[]> PacificAtlantic(int[][] matrix) {
		List<int[]> result = new ArrayList<int[]>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		
		int[][] pacific = new int[matrix.length][matrix[0].length];
		int[][] atlantic = new int[matrix.length][matrix[0].length];
		//initialize 
		for (int i = 0; i < pacific.length; i++) {
			if (pacific[i][0] <= 0) {
				pacific[i][0] = 1;
				oceanDFS(matrix, pacific, i, 0);
			}
		}
		for (int i = 0; i < pacific[0].length; i++) {
			if (pacific[0][i] <= 0) {
				pacific[0][i] = 1;
				oceanDFS(matrix, pacific, 0, i);
			}
		}
		for (int i = atlantic.length - 1; i >= 0; i--) {
			if (atlantic[i][0] <= 0) {
				atlantic[i][0] = 1;
				oceanDFS(matrix, atlantic, i, 0);
			}
		}
		for (int i = atlantic[0].length - 1; i >= 0; i--) {
			if (atlantic[0][i] <= 0) {
				atlantic[0][i] = 1;
				oceanDFS(matrix, atlantic, 0, i);
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (pacific[i][j] == 1 && atlantic[i][j] == 1) {
					int[] array = new int[2];
					array[0] = i;
					array[1] = j;
					result.add(array);
				}
			}
		}
		return result;
	}
	
	private static void oceanDFS(int[][] matrix, int[][] ocean, int x, int y) {
		for (int i = 0; i < 4; i++) {
			int a = x + xOffset[i];
			int b = y + yOffset[i];
			if (a >= 0 && a < ocean.length && b >= 0 && b < ocean[0].length && ocean[a][b] == 0 && matrix[a][b] >= matrix[x][y]) {
				ocean[a][b] = 1;
				oceanDFS(matrix, ocean, a, b);
			}
		}
	}
}
