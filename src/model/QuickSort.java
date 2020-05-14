package model;

import java.io.Serializable;

public class QuickSort implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void sort(int arr[],int low,int high) {
		
		if(low < high) {
			
			int pi = partition(arr,low,high);
			
			sort(arr,low,pi-1);
			sort(arr,pi+1,high);
		}
		
	}
	
	public int partition(int arr[],int low, int high) {
		
		int pivote = arr[high];
		int i =(low-1);
		
		for(int j = low; j < high;j++) {
			
			if(arr[j] < pivote) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
			}
			
		}
		
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		return i+1;
	}
	
}
