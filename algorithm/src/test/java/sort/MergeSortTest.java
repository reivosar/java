package sort;

import org.junit.jupiter.api.Test;

import sort.merge.MergeSort;

class MergeSortTest extends SortTestTemplate
{
    @Override
    protected SortTemplate getTestClass(int[] random_array) {
        return new MergeSort(random_array);
    }

    @Test
    void test() {
        assertArray(testClass.execute());
    }
}
