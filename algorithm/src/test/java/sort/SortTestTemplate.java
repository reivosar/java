package sort;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

abstract class SortTestTemplate
{
    private int[] sorted_array;
    protected SortTemplate testClass;

    @BeforeEach
    void setup() {
        final int[] random_array = new int[] {2, 5, 1, 1, 3, 4};
        this.testClass    = getTestClass        (random_array);
        this.sorted_array = testClass.arraycopy (random_array);
        Arrays.sort (sorted_array);
    }

    protected abstract SortTemplate getTestClass(int[] random_array);

    protected void assertArray(int[] result) {
        assertThat(result, is(sorted_array));
    }
}
