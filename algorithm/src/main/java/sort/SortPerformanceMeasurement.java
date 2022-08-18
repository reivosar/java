package sort;

import sort.bubble.BubbleSort;
import sort.insertation.InsertationSort;
import sort.merge.MergeSort;
import sort.quick.QuickSort;
import sort.selection.SelectionSort;

public class SortPerformanceMeasurement
{
    public static void main(String[] args) {
        new SortPerformanceMeasurement().run();
    }

    private void run() {
        SortItemGenerator generator = new SortItemGenerator();
        sortAction("ランダム値", generator.random ());
        sortAction("ソート済",  generator.sorted ());
        sortAction("同値",    generator.same   ());
    }

    private void sortAction(String sortItemType, int[] sortItems) {
        System.out.println ("ソート種別：" + sortItemType);
        doSort(new BubbleSort      (sortItems));
        doSort(new SelectionSort   (sortItems));
        doSort(new InsertationSort (sortItems));
        doSort(new QuickSort       (sortItems));
        doSort(new MergeSort       (sortItems));
    }

    private void doSort(SortTemplate sortClass) {
        sortClass.execute();
    }
}
