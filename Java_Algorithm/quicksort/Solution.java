package quicksort;

public class Solution {
	public static void main(String[] args) {
		
		QuickSort quickSort = new QuickSort();
		int[] datas = new int[]{5, 6, 8, 1, 3, 10, 8, 5, 4, 15, 11};
		quickSort.setData(datas);
		quickSort.Quick_Sort(0, datas.length-1);
		quickSort.Display();
		
	}
}

class QuickSort {
	
	private int[] data;
	
	public void setData(int[] datas) {
		data = datas;
	}
	
	public int Partition(int start, int end) {
		int i = start - 1;
		//默认最后一个为主元阈值
		int x = data[end];
		
		for(int j = start; j < end; j++) {
			if(data[j] <= x) {
				i++;
				// Swap data[i] and data[j]
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
		
		int temp = data[i + 1];
		data[i + 1] = data[end];
		data[end] = temp;
		
		return i + 1;
	}
	
	public void Quick_Sort(int start, int end) {
		if( start < end) {
			int partition = Partition(start, end);
			Quick_Sort(start, partition - 1);
			Quick_Sort(partition + 1, end);
		}
	}
	
	public void Display() {
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
}