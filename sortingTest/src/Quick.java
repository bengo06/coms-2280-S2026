import java.util.Arrays;
import java.util.Random;

public class Quick {
    static void main() {
        int[] d = new int[10];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            d[i] = r.nextInt(100);
        }

        IO.println(Arrays.toString(d));

        quickSort(d);

        IO.println(Arrays.toString(d));
    }

    public static void quickSort(int[] nums) {
        quickSortRec(nums, 0, nums.length - 1);
    }

    private static void quickSortRec(int[] nums, int first, int last) {
        if (first >= last) {
            return;
        }

        int p = partition(nums, first, last);
        quickSortRec(nums, first, p - 1);
        quickSortRec(nums, p + 1, last);
    }

    private static int partition(int[] nums, int first, int last) {
        int pivot = nums[last];
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (nums[j] <= pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        int temp = nums[i + 1];
        nums[i + 1] = nums[last];
        nums[last] = temp;

        return i + 1;
    }
}
