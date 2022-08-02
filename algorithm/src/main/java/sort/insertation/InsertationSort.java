package sort.insertation;

import sort.SortTemplate;

public class InsertationSort extends SortTemplate
{
    public InsertationSort(final int[] array) {
        super(array);
    }

    /**
     * <pre>
     * 挿入ソート処理.
     *   《説明》
     *     データの一部分をソート済みの状態にしながら,未ソートデータの各要素を一つずつ、ソート済みの
     *     状態に挿入していく手法。
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
        for (int i = 1; i < arraySize; i++) {
            int j = i;
            while ((j >= 1) && (result[j - 1] > result[j])) {
                swap(result, (j - 1), j);
                j--;
                print(String.format(" i:%d j:%d ", i, j));
                print_array(result);
            }
        }
        return result;
    }
}
