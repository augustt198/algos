public class BinarySearch {

    private static final int ARR_SIZE = 100_000_000;
    private static final int TESTS = 500;

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];

        for (int i = 0; i < ARR_SIZE; i++) {
            arr[i] = i;
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < TESTS; i++) {
            linearSearch(arr, i * (arr.length / TESTS));
        }
        System.out.println(TESTS + " linear searches took " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < TESTS; i++) {
            binarySearch(arr, i * (arr.length / TESTS));
        }
        System.out.println(TESTS + " binary searches took " + (System.currentTimeMillis() - start) + "ms");
    }

    public static boolean binarySearch(int[] arr, int num) {
        return binarySearch(arr, num, 0, arr.length);
    }

    private static boolean binarySearch(int[] arr, int num, int left, int right) {
        int pos = left + ((right - left) / 2);

        if (pos >= arr.length - 1) return arr[arr.length - 1] == num;
        if (pos <= 0) return arr[0] == num;

        if (arr[pos] > num) {
            return binarySearch(arr, num, left, pos);
        } else if (arr[pos] < num) {
            return binarySearch(arr, num, pos, right);
        } else {
            return true;
        }
    }

    public static boolean linearSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) return true;
        }
        return false;
    }

}
