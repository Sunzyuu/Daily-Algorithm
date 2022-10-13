public class QuickSort {
    private int[] array;
    public QuickSort(int[] array) {
        this.array = array;
    }
    public void sort() {
        quickSort(array, 0, array.length - 1);
    }
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 递归排序
     * @param src
     * @param begin
     * @param end
     */
    private void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            // 基准数指针，这里采用的时随机获取指针的方式，可以避免有序数组的时间复杂度很高的情况
            int pivot = (int) (begin + Math.random() * (end - begin + 1));
            int key = src[pivot];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                while (i < j && src[i] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            src[i] = key;
            quickSort(src, begin, i - 1);
            quickSort(src, i + 1, end);
        }
    }


    /**
     * 递归排序
     * @param src
     * @param begin
     * @param end
     */
    private void quickSortBase(int[] src, int begin, int end) {
        if (begin < end) {
            // 基准数指针，这里采用的时随机获取指针的方式，可以避免有序数组的时间复杂度很高的情况
//            int pivot = (int) (begin + Math.random() * (end - begin + 1));
            int key = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                while (i < j && src[i] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            src[i] = key;
            quickSort(src, begin, i - 1);
            quickSort(src, i + 1, end);
        }
    }
}

