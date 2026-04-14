import java.util.Arrays;
import java.util.Random;

public class Selection {
    static void main() {
        int[] d = new int[10];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            d[i] = r.nextInt(100);
        }

        IO.println(Arrays.toString(d));

        selectionSort(d);

        IO.println(Arrays.toString(d));
    }

    public static void selectionSort(int[] nums) {
        int small, temp;

        for (int i = 0; i < nums.length - 1; i++) {
            small = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[small]) {
                    small = j;
                }
            }
            temp = nums[small];
            nums[small] = nums[i];
            nums[i] = temp;
        }
    }
}
