import com.example.lab1_tos.CosineFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CosineFunctionTest {

    //x = 0
    @Test
    public void testCosFunction() {
        assertEquals(1.0, CosineFunction.cosSeries(0), 0.0001);
    }

    //x = π/3
    @Test
    public void testCosFunctionPI() {
        assertEquals(0.5, CosineFunction.cosSeries(Math.PI / 3), 0.0001);
    }

    //x = π/2
    @Test
    public void testCosFunctionPI2() {
        assertEquals(0.0, CosineFunction.cosSeries(Math.PI / 2), 0.0001);
    }

    //x = π
    @Test
    public void testCosFunctionPi() {
        assertEquals(-1.0, CosineFunction.cosSeries(Math.PI), 0.0001);
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
}