package Sort;

public class QuickSort {
    public static void swap(int[] arr, int left, int right){
        arr[left] ^= arr[right];
        arr[right] ^= arr[right];
        arr[left] ^= arr[right];
    }

    public static void swap2(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void quickSortRZ(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        int len = arr.length;
        helper(arr, 0, len - 1);
    }

    public static void helper(int[] arr, int left, int right){
        if (left > right){
            return;
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

        helper(arr, left, cur - 1);
        helper(arr, cur + 1, right);
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

        quickSortRZ(arr);
    }
}
