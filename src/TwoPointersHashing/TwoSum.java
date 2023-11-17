package TwoPointersHashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
	// Solution 1 = Brute force
	public static String solution1(int[] arr, int target) {
		int n = arr.length;

		// Check every possible pair
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(arr[i]+arr[j] == target) {
					return "Yes";
				}
			}
		}

		// If control reaches here, Implies that the target wasnt found
		return "No";
	}

	// Solution 2 = Hash Map
	public static int[] solution2(int[] arr, int target) {
		int n = arr.length;

		Map<Integer, Integer> hm = new HashMap<>();
		int[] answer = new int[2];

		for(int i=0; i<n; i++) {
			int elem1 = arr[i];
			int elem2 = target - arr[i];

			if(hm.containsKey(elem2)) {
				answer[0] = i;
				answer[1] = hm.get(elem2);
			}

			// Put arr[i] and its index(i.e i)
			hm.put(arr[i], i);
 		}

		return answer;
	}

	// Solution 3 = 2 pointers approach
	// Prerequisite = Array is sorted
	public static int[] solution3(int[] arr, int target) {
		int n = arr.length;

		int[] answer = new int[2];
		int l=0, r=n-1;
		while (l<r) {
			// If arr[l]+arr[r] == target, Return the answer
			if(arr[l]+arr[r] == target) {
				answer[0] = l;
				answer[1] = r;
			}
			// Else, If arr[l]+arr[r] < target, Increment l
			else if(arr[l]+arr[r] < target) {
				l++;
			}
			// Else, arr[l]+arr[r] > target, Decrement r
			else {
				r--;
			}
		}

		return answer;
	}
}
