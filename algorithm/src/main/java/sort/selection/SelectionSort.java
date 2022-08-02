package sort.selection;

import sort.SortTemplate;

public class SelectionSort extends SortTemplate {
    public SelectionSort(final int[] array) {
        super(array);
    }

    /**
     * <pre>
     * 選択ソート処理.
     *   《説明》
     *     データのソートされていない部分から最小の部分を選択し、それを先頭部分へ移動するという操作
     *     を繰り返す手法。
     *
     *   《計算量と安定性》
     *     平均:O(n2)
     *     最良:O(n2)
     *     最悪:O(n2)
     *     安定性:不安定 ※最小値を求める際、同じ値が来た場合に、元の並びが保持されない。
     * </pre>
     *
     * @param array     ソート前の配列
     * @param arraySize 配列の要素数
     * @return ソート後の配列
     */
    @Override
    protected int[] sort(final int[] array, final int arraySize) {
        int[] result = arraycopy(array);
        for (int i = 0; i < arraySize - 1; i++) {
            int min_idx = i;
            for (int j = (i + 1); j < arraySize; j++) {
                if (result[j] < result[min_idx]) {
                    min_idx = j;
                }
                print(String.format(" i:%d j:%d ", i, j));
                print_array(result);
            }
            swap(result, min_idx, i);
        }
        return result;
    }
}
