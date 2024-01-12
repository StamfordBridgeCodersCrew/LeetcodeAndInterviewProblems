package BinarySearchArrayMatrix;

public class SearchInA2DMatrix {

    /*
    This is a Sorted 2D Matrix
        Each row is sorted in increasing order
        First integer of each row > Previous integer of each row

    Hence this method is applicable
    https://leetcode.com/problems/search-a-2d-matrix/
    */
    public boolean searchMatrix1(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        int low=0, high=(n*m)-1;
        while(low <= high) {

            // mid = Mid element in the flattened matrix

            /*
            idx_arr = Index
            Maps to mat[idx_arr/m][idx_arr%m]
            **/
            int mid = low + (high-low)/2;

            if(mat[mid/m][mid%m] == target) {
                return true;
            }
            // Reduce the search space to flattnd_mat[low, mid-1]
            else if (mat[mid/m][mid%m] < target) {

                high = mid - 1;
            }
            // Otherwise mat[mid/m][mid%m] > target
            // Reduce the search space to flattnd_mat[mid+1, high]
            else {
                low = mid + 1;
            }
        }
        return false;
    }

    // A more generic method
    public boolean searchMatrix2(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        // Start searching from the Top Right corner

        return false;
    }

    public static void main(String[] args) {

    }
}
