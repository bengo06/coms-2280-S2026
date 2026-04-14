import java.util.Arrays;
import java.util.Random;

public class Insertion {
    static void main() {
        int[] d = new int[10];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            d[i] = r.nextInt(100);
        }

        IO.println(Arrays.toString(d));

        insertionSort(d);

        IO.println(Arrays.toString(d));
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int focus = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > focus) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = focus;
        }
    }
}
