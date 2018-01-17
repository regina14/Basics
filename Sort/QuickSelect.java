package Sort;

public class QuickSelect {
    public static void swap2(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static int quickSelectRZ(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return - 1;
        }
        int len = arr.length;
        return helper(arr, k ,0, len - 1);
    }

    public static int helper(int[] arr, int k, int left, int right){
        if (left > right){
            return -1;
        }
        int pivot = arr[right];
        int cur = left;
        int len = arr.length;

        for (int i = left; i < right; i++){
            if (arr[i] <= pivot) {
                swap2(arr, cur, i);
                cur++;
            }
        }

        swap2(arr, right, cur);
        int m = cur - left;
        if (m == k - 1){
            return pivot;
        } else if (m > k - 1){
            return helper(arr, k, left, cur - 1);
        } else {
            return helper(arr, k -  m, cur, right );
        }
    }

    public static void printArrayList(int[] nums) {
        System.out.print("Array List: ");
        if(nums != null){
            for(int n: nums) {
                System.out.print(n  + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3,7,8,5,2,1,9,5,4};

        System.out.println(quickSelectRZ(arr, 6));
    }
}
