import com.example.lab1_tos.CosineFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CosineFunctionTest {

    //x = 0
    @Test
    public void testCosFunction() {
        assertEquals(1.0, CosineFunction.cosSeries(0), 0.0001);
    }

    //x = π/3
    //x = π/2
    //x = π
    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 3, Math.PI / 2, Math.PI})
    public void testCosFunctionWithVariousValues(double input) {
        double expected = Math.cos(input);
        assertEquals(expected, CosineFunction.cosSeries(input), 0.0001);
    }

    //отрицательные значения
    @Test
    public void testCosFunctionNegative() {
        assertEquals(-1.0, CosineFunction.cosSeries(-Math.PI), 0.0001);
    }

    //очень маленькие значения 0.0001
    @Test
    public void testCosFunctionSmall() {
        assertEquals(1.0, CosineFunction.cosSeries(0.0001), 0.0001);
    }

    //погрешность
    @Test
    public void testCosFunctionPrecision() {
        assertEquals(0.7071, CosineFunction.cosSeries(Math.PI / 4), 0.0001);
    }
    // симмертичность
    @Test
    public void testCosSymmetry() {
        double x = Math.PI / 4;
        assertEquals(CosineFunction.cosSeries(-x), CosineFunction.cosSeries(x), 0.0001);
    }

    // периодичность
    @Test
    public void testCosPeriodicity() {
        double x = Math.PI / 4;
        assertEquals(CosineFunction.cosSeries(x + 2 * Math.PI), CosineFunction.cosSeries(x), 0.05);
    }

    // проверка на NaN
    @Test
    public void testCosNaN() {
        assertTrue(Double.isNaN(CosineFunction.cosSeries(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(CosineFunction.cosSeries(Double.NEGATIVE_INFINITY)));
    }

}