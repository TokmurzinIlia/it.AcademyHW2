import com.it_academy.practice.junit_basics.Calculator;
import com.it_academy.practice.junit_basics.CustomArgumentProviderSubtraction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class calculatorTest {

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "2, 2, 4",
            "1, 10, 11",
            "22, 25, 47",
            "56, 44, 100",
            "1,-1, 0"
    })
    @Order(3)

    public void testAdd(int a, int b, float sum){
        Calculator calculator = new Calculator(a,b);
        assertEquals(sum, calculator.calculate('+'), 0.001);
    }


    @ParameterizedTest(name = "{index} => a={0}, b={1}, multiply={2}")
    @CsvFileSource(resources = "/multiplicationTest")
    @Order(2)

    public void testMultiply(int a, int b, float multiply){
        Calculator calculator = new Calculator(a,b);
        assertEquals(multiply, calculator.calculate('*'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, subtract={2}")
    @ArgumentsSource(CustomArgumentProviderSubtraction.class)
    @Order(1)

    public void testSubtract(int a, int b, float subtract){
        Calculator calculator = new Calculator(a,b);
        assertEquals(subtract, calculator.calculate('-'), 0.001);
    }
}
