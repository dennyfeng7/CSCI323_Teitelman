import java.util.Random;
import java.lang.*;

class BinarySearchAssignment {

    int[] binarySearch (int[] arr, int key, int low, int high) {
        // Declare result array that is going to store values such as low, high that I'll output in MAIN method.
        // Declare counter to count number of comparisons.
        int[] result = new int[5];
        int index = Integer.MAX_VALUE;
        int counter = 0;

        // Binary Search with a Counter Variable.
        while (low <= high) {

            int mid = (low + high) / 2;
            if(arr[mid] < key) {
                low = mid + 1;
                counter++;
            } else if (arr[mid] > key) {
                high = mid - 1;
                counter++;
            } else if (arr[mid] == key) {
                index = mid;
                counter++;
                break;
            }
        }

        // NUMBER NOT FOUND. Retrieve low, high, key and counter values to output in the MAIN method.
        if(index == Integer.MAX_VALUE) {
            result[0] = arr[high];
            result[1] = arr[low];
            result[2] = key;
            result[3] = -1;
            result[4] = counter;
            return result;
        }

        // NUMBER FOUND. Retrieve low, high, key and counter values to output in the MAIN method.
        result[0] = arr[high];
        result[1] = arr[low];
        result[2] = key;
        result[3] = index;
        result[4] = counter;
        return result;
    } // IMPROVED BINARY SEARCH

    int[] classicBinarySearch(int arr[], int key, int low, int high) {
        int index = Integer.MAX_VALUE;
        int[] result = new int[5];
        int counter = 0;

        while(low <= high) {
            counter++;
            int mid = low + (high-low) / 2;
            if(arr[mid] == key) {
                index = mid;
                break;
            }

            if(arr[mid] < key) {
                counter++;
                low = mid + 1;
            } else {
                counter++;
                high = mid - 1;
            }
        } // WHILE

        // NUMBER NOT FOUND. Retrieve low, high, key and counter values to output in the MAIN method.
        if(index == Integer.MAX_VALUE) {
            result[0] = arr[high];
            result[1] = arr[low];
            result[2] = key;
            result[3] = -1;
            result[4] = counter;
            return result;
        }

        // NUMBER FOUND. Retrieve low, high, key and counter values to output in the MAIN method.
        result[0] = arr[high];
        result[1] = arr[low];
        result[2] = key;
        result[3] = index;
        result[4] = counter;
        return result;
    } // CLASS

    public static void main(String[] args) {

        int array[] = new int[65536];

        for (int i = 0; i < array.length-1; i++) {
            Random rn = new Random();
            int rand = rn.nextInt(10) + 1;
            array[i+1] = array[i] + rand;
        }


        BinarySearchAssignment binSearch = new BinarySearchAssignment();

        // Initialize array to the size of 2^16.
        int arr[] = new int[(int)Math.pow(2, 16)];

        // Populate array with random numbers in sorted order by adding a random number between 1 and 10 to each index.
        for (int i = 0; i < arr.length-1; i++) {
            Random rn = new Random();
            int rand = rn.nextInt(10) + 1;
            arr[i+1] = arr[i] + rand;
        }

        // Output 10 samples in tabular form.
        Object[][] table = new String[10][];

        for (int i = 0; i < 10; i++) {
            int minRandomNumber = 0;
            int maxRandomNumber = arr.length - 1;
            int searchKey = minRandomNumber + (int) (Math.random() * ((maxRandomNumber - minRandomNumber) + 1));

            int[] resultTest = binSearch.binarySearch(arr, searchKey, 0, arr.length);
            int expectedComparison = 1 + (int)(Math.log(arr.length) / Math.log(2));

            if (resultTest[3] == -1) {
                table[i] = new String[]
                        {
                                String.valueOf(arr.length), // # OF ELEMENTS
                                String.valueOf(searchKey), // SEARCH KEY
                                "NOT FOUND",
                                "N/A",
                                String.valueOf(resultTest[0]), // LEFT NUMBER
                                String.valueOf(resultTest[1]), // RIGHT NUMBER
                                String.valueOf(resultTest[4]), // # OF COMPARISONS
                                String.valueOf(expectedComparison)
                        };
            } else {
                table[i] = new String[]
                        {
                                String.valueOf(arr.length), // # OF ELEMENTS
                                String.valueOf(searchKey),  // SEARCH KEY
                                "FOUND",
                                String.valueOf(resultTest[3]), // INDEX
                                String.valueOf(resultTest[0]), // LEFT NUMBER
                                String.valueOf(resultTest[1]), // RIGHT NUMBER
                                String.valueOf(resultTest[4]), // # OF COMPARISONS
                                String.valueOf(expectedComparison)
                        };
            }
        } // FOR

        System.out.println();

        // Formatting
        System.out.format("%-12s%-18s%-15s%-20s%-20s%-15s%-20s%-20s%-20s\n",
                "SAMPLE #",
                "# OF ELEMENTS",
                "SEARCH KEY",
                "FOUND/NOT FOUND",
                "INDEX (IF FOUND)",
                "LEFT NUMBER",
                "RIGHT NUMBER",
                "# OF COMPARISONS",
                "COMPARISONS EXPECTED"
        );

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < table.length; i++) {
            Object[] row = table[i];
            // Data Output
            System.out.format("%-12d%-18s%-15s%-20s%-20s%-15s%-20s%-20s%-20s\n",
                    i, // SAMPLE #
                    row[0], // # OF ELEMENTS
                    row[1], // SEARCH KEY
                    row[2], // FOUND/NOT FOUND
                    row[3], // INDEX IF FOUND
                    row[4], // LEFT NUMBER
                    row[5], // RIGHT NUMBER
                    row[6], // # OF COMPARISONS
                    row[7] // COMPARISONS EXPECTED
            );
        } // FOR
    } //MAIN
} //CLASS
