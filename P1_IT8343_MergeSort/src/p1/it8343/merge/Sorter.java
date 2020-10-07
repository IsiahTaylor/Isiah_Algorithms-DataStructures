package p1.it8343.merge;

import java.util.Arrays;

public class Sorter {
	public static <T extends Comparable<T>> void sort(T[] items) {
		// Main call action
		sortAux(items, 0, items.length-1);
	}

	// Needed Methods
	public static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
		// Make new arrays to store into
		int new1 = mid - left+1;
		int new2 = right - mid;

		var L = Arrays.copyOfRange(arr, left, mid+1);
		var R = Arrays.copyOfRange(arr, mid+1, right+1);

		// Set the counters back to 0
		int i = 0, j = 0;
		// set k to the left start bound of the index used in the original array
		int k = left;

		// go through the 2 arrays and overwrite the old array
		while (i < new1 && j < new2) {
			if (L[i].compareTo(R[j]) < 0) {
				arr[k] = (T) L[i++];
			} else  {
				arr[k] = (T) R[j++];
			}
			k++;
		}

		// add any stragglers
		while (i < new1) {
			arr[k] = (T) L[i++];
			k++;
		}

		while (j < new2) {
			arr[k] = (T) R[j++];

			k++;
		}
	}

	static <T extends Comparable<T>> void sortAux(T[] arr, int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// sortAux first and second halves
			sortAux(arr, l, m);
			sortAux(arr, m + 1, r);

			// Merge the sortAuxed halves
			merge(arr, l, m, r);
		}
	}

}
