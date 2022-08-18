package sort.quick;

import sort.SortTemplate;

public class QuickSort extends SortTemplate {
    public QuickSort(final int[] array) {
        super(array);
    }

    /**
     * <pre>
     * クイックソート処理.
     *   《説明》
     *     分割統治アルゴリズムとしても知られている代表的なソート処理。
     *     ソート対象となるデータ配列を2つの部分配列として分割する。分割に使う配列の要素はピボット
     *     と呼ばれる。ピボットによって分割された、部分配列に対してソート処理を繰り返し行う手法。
     *
     *   《計算量と安定性》
     *     平均:O(n log n)
     *     最良:O(n log n)
     *     最悪:O(n2) ※ソート済みの配列に対しては、バブルソートなどの原始的なソートより遅いので注意。
     *     安定性:安定
     * </pre>
     *
     * @param array     ソート前の配列
     * @param arraySize 配列の要素数
     * @return ソート後の配列
     */
    @Override
    protected int[] sort(final int[] array, final int arraySize) {
        final int[] result = arraycopy(array);
        quicksort("【INITIAL】", result, 0, result.length - 1);
        return result;
    }

    private void quicksort(final String label, final int[] array, final int left, final int right) {
        println("===== " + label + " =====");
        if (left < right) {
            final int p = partition(array, left, right);
            println(String.format("  pivot:%d", p));
            quicksort("【LEFT】", array, left, (p - 1));
            quicksort("【RIGHT】", array, (p + 1), right);
        } else {
            println(String.format("  ★★★★★ IGNORED ★★★★★ left:%d right:%d", left, right));
        }
    }

    private int partition(final int[] array, final int left, final int right) {
        int num = array[left];
        int down = left;
        int up = right;
        println(String.format("  num:%d left:%d right:%d", num, left, right));
        while (down < up) {
            while ((array[down] <= num) && (down < right))
                down++;
            while (array[up] > num)
                up--;
            print(String.format("   down:%d up:%d ", down, up));
            if (down < up)
                swap(array, up, down);
            print_array(array);
        }
        array[left] = array[up];
        array[up] = num;
        return up;
    }
}
