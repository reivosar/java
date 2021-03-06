package sort;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SortItemGenerator {

	private static final int ARRAY_SIZE = 5000;

	public int[] random() {
		final Random rand = new Random();
		return generate(() -> {
			return rand.nextInt(ARRAY_SIZE);
		});
	}

	public int[] sorted() {
		return generate(new Supplier<Integer>() {
			private int nums = 0;
			@Override
			public Integer get() {
				nums++;
				return nums;
			}
		});
	}

	public int[] same() {
		return generate(() -> {
			return 0;
		});
	}

	private int[] generate(Supplier<Integer> supplier) {
		return IntStream.generate(() -> {
			return supplier.get();
		}).limit(ARRAY_SIZE).toArray();
	}
}
