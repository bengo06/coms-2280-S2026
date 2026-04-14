import java.util.Arrays;
import java.util.Random;

public class Merge {
    static void main() {
        int[] d = new int[10];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            d[i] = r.nextInt(100);
        }

        IO.println(Arrays.toString(d));

        mergeSort(d);

        IO.println(Arrays.toString(d));
    }

    public static void mergeSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        int mid = nums.length / 2;
        int[] l = new int[mid];
        int[] r = new int[nums.length - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = nums[i];
        }

        for (int i = mid; i < nums.length; i++) {
            r[i - mid] = nums[i];
        }

        mergeSort(l);
        mergeSort(r);

        merge(nums, l, r);

    }

    public static void merge(int[] og, int[] l, int[] r) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                og[k] = l[i];
                i++;
            }
            else {
                og[k] = r[j];
                j++;
            }
            k++;
        }

        while (i < l.length) {
            og[k] = l[i];
            i++;
            k++;
        }

        while (j < r.length) {
            og[k] = r[j];
            j++;
            k++;
        }
    }
}
