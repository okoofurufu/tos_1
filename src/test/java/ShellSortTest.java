import com.example.lab1_tos.ShellSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    public void testShellSort() {
        int[] input = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //пустой массив
    @Test
    public void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //одинаковые элементы
    @Test
    public void testArrayWithIdenticalElements() {
        int[] input = {5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //отрицательные числа
    @Test
    public void testArrayWithNegativeNumbers() {
        int[] input = {-5, -2, -9, -1, -5, -6};
        int[] expected = {-9, -6, -5, -5, -2, -1};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //отсортированный
    @Test
    public void testSortedArray() {
        int[] input = {1, 2, 5, 5, 6, 9};
        int[] expected = {1, 2, 5, 5, 6, 9};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //обратный порядок
    @Test
    public void testReverseSortedArray() {
        int[] input = {9, 6, 5, 5, 2, 1};
        int[] expected = {1, 2, 5, 5, 6, 9};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //один жлемент
    @Test
    public void testSingleElementArray() {
        int[] input = {1};
        int[] expected = {1};
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }

    //большой массив
    @Test
    public void testLargeArray() {
        int[] input = new int[1000];
        for (int i = 0; i < 1000; i++) {
            input[i] = 1000 - i;
        }
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expected[i] = i + 1;
        }
        ShellSort.shellSort(input);
        assertArrayEquals(expected, input);
    }
}