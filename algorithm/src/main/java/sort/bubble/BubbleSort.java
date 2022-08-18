package sort.bubble;

import sort.SortTemplate;

public class BubbleSort extends SortTemplate
{
    public BubbleSort(final int[] array) {
        super(array);
    }

    /**
     * <pre>
     * バブルソート処理.
     *   《説明》
     *     隣接する要素の値の大小関係を比較し、大小関係が逆だったらそれを入れ替えていく手法。
     *
     *   《計算量と安定性》
     *     平均:O(n2)
     *     最良:O(n) ※ソート済みであれば
     *     最悪:O(n2)
     *     安定性:安定
     * </pre>
     * @param array ソート前の配列
     * @param arraySize 配列の要素数
     * @return ソート後の配列
     */
    @Override
    protected int[] sort(final int[] array, final int arraySize) {
        int[] result = arraycopy(array);
        for (int i = 0; i < arraySize - 1; i++) {
            for (int j = 0; j < arraySize - i - 1; j++) {
                if (result[j] > result[j + 1]) {
                    swap(result, (j + 1), j);
                }
                print(String.format(" i:%d j:%d ", i, j));
                print_array(result);
            }
        }
        return result;
    }
}
