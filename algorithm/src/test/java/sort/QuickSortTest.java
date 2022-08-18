package sort;

import org.junit.jupiter.api.Test;

import sort.quick.QuickSort;

class QuickSortTest extends SortTestTemplate
{
    @Override
    protected SortTemplate getTestClass(int[] random_array) {
        return new QuickSort(random_array);
    }

    @Test
    void test() {
        assertArray(testClass.execute());
    }
}
