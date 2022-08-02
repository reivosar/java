package sort;

import org.junit.jupiter.api.Test;

import sort.bubble.BubbleSort;

class BubbleSortTest extends SortTestTemplate
{
    @Override
    protected SortTemplate getTestClass(int[] random_array) {
        return new BubbleSort(random_array);
    }

    @Test
    void test() {
        assertArray(testClass.execute());
    }
}
