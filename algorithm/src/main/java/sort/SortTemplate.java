package sort;

public abstract class SortTemplate {
    private final int[] array;
    private final boolean enableDebugging;

    public SortTemplate(final int[] array, final boolean enableDebugging) {
        this.array = arraycopy(array);
        this.enableDebugging = enableDebugging;
    }

    public SortTemplate(final int[] array) {
        this(array, false);
    }

    protected int[] arraycopy(final int[] array) {
        final int[] result = new int[array.length];
        System.arraycopy (array, 0, result, 0, array.length);
        return result;
    }

    public final int[] execute() {
        final long startTime = System.currentTimeMillis();
        try {
            startLog (startTime);
            return doExecute();
        } finally {
            endLog (startTime);
        }
    }

    private int[] doExecute() {
        print_array (array);
        final int[] result = sort(array, array.length);
        print_array (result);
        return result;
    }

    private void startLog(final long startTime) {
        System.out.println(getStartTimeLabel() + startTime + " ms");
    }

    private void endLog(final long startTime) {
        final long endTime = System.currentTimeMillis();
        System.out.println(getEndtimeLabel() + endTime + " ms");
        System.out.println(getProcessingTimeLabel() + (endTime - startTime) + " ms");
    }

    private String getStartTimeLabel() {
        return labelFormat("開始時間");
    }

    private String getEndtimeLabel() {
        return labelFormat("終了時間");
    }

    private String getProcessingTimeLabel() {
        return  labelFormat("処理時間");
    }

    private String labelFormat(String label) {
        return String.format(label +"(%s):", getClass().getSimpleName());
    }

    protected void print(final Object obj) {
        if (enableDebugging) {
            System.out.print(obj);
        }
    }

    protected void println(final Object obj) {
        if (enableDebugging) {
            System.out.println(obj);
        }
    }

    protected void print_array(final int[] array) {
        if (enableDebugging) {
            print("array:");
            for (int i : array)
                print(i + " ");
            System.out.println();
        }
    }

    protected void swap(final int[] array, int from, int to) {
        final int tmp = array[to];
        array[to]     = array[from];
        array[from]   = tmp;
    }

    protected abstract int[] sort(int[] array, int arraySize);
}
