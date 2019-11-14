package recursion;

public class BinarySearch {
	
	private static int binarySearch(int[] a, int x, int first, int last) {
		if(first > last) {
			return -1;
		} else {
			int mid = first + ((last-first)/2);
			if(x == a[mid]) {
				return mid;
			} else if(x < a[mid]) {
				return binarySearch(a, x, first, mid-1);
			} else {
				return binarySearch(a, x, first+1, mid);
			}
		}
	}

	public static void main(String[] args) {
		int[] i;
	}

}
