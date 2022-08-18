package sort.merge;

import sort.SortTemplate;

public class MergeSort extends SortTemplate
{
    public MergeSort(final int[] array) {
        super(array);
    }

    /**
     * <pre>
     * マージソート処理.
     *   《説明》
     *     ソート対象となるデータ配列を半分に分割する。2つに分割した配列Aと配列Bをぞれぞれ
     *     ソートする。ソート済みの配列のマージを繰り返し行う手法。
     *
     *   《計算量と安定性》
     *     平均:O(n log n)
     *     最良:O(n log n)
     *     最悪:O(n2) ※ソート済みの配列に対しては、バブルソートなどの原始的なソートより遅いので注意。
     *     安定性:安定
     * </pre>
     * @param array ソート前の配列
     * @param arraySize 配列の要素数
     * @return ソート後の配列
     */
    @Override
    protected int[] sort(final int[] array, final int arraySize) {
        final int[] result = arraycopy(array);
        final int[] tmp    = new int[arraySize];
        merge(result, 0, (arraySize - 1), tmp);
        return result;
    }

    private void merge(final int[] array, final int lower_bound, final int upper_bound, final int[] tmp)
    {
        print_input_data(array, lower_bound, upper_bound, tmp);

        if (lower_bound >= upper_bound) return;

        // 配列を2つに分割
        final int middle_bound = (lower_bound + upper_bound) / 2;
        merge (array, lower_bound,        middle_bound, tmp);
        merge (array, (middle_bound + 1), upper_bound,  tmp);

        // 配列のマージ
        for (int i = lower_bound; i <= middle_bound; i++)
            tmp[i] = array[i];
        for (int i = (middle_bound + 1), j = upper_bound; i <= upper_bound; i++, j--)
            tmp[i] = array[j];

        // マージした配列を昇順にソート
        int tmp_lower_bound = lower_bound;
        int tmp_upper_bound = upper_bound;
        for (int i = lower_bound; i <= upper_bound; i++)
            if (tmp[tmp_lower_bound] <= tmp[tmp_upper_bound])
                array[i] = tmp[tmp_lower_bound++];
            else
                array[i] = tmp[tmp_upper_bound--];
    }

    private void print_input_data(final int[] array, final int lower_bound, final int upper_bound, final int[] tmp) {
        println(String.format("  lower_bound:%d upper_bound:%d", lower_bound, upper_bound) );
        print("    Result ");  print_array(array);
        print("    Temp   ");  print_array(tmp);
    }
}
