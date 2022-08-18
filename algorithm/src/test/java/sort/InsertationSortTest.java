package sort;

import org.junit.jupiter.api.Test;

import sort.insertation.InsertationSort;

class InsertationSortTest extends SortTestTemplate {

    @Override
    protected SortTemplate getTestClass(int[] random_array) {
        return new InsertationSort(random_array);
    }

    @Test
    void test() {
        assertArray(testClass.execute());
    }
}
