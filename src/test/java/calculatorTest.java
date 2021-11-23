import com.it_academy.practice.junit_basics.Calculator;
import com.it_academy.practice.junit_basics.CustomArgumentProviderSubtraction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    public void testAdd(int a, int b, int sum){
        Calculator calculator = new Calculator(a,b);
        assertEquals(sum, calculator.calculate('+'), 0.001);
    }


    @ParameterizedTest(name = "{index} => a={0}, b={1}, multiply={2}")
    @CsvFileSource(resources = "/multiplicationTest")
    @Order(2)

    public void testMultiply(int a, int b, int multiply){
        Calculator calculator = new Calculator(a,b);
        assertEquals(multiply, calculator.calculate('*'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, subtract={2}")
    @ArgumentsSource(CustomArgumentProviderSubtraction.class)
    @Order(1)

    public void testSubtract(int a, int b, int subtract){
        Calculator calculator = new Calculator(a,b);
        assertEquals(subtract, calculator.calculate('-'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, divide={2}")
    @MethodSource("CustomArgumentProviderDivide")
    @Order(4)

    public void testDivide(int a, int b, float divide) {
        Calculator calculator = new Calculator(a, b);
        assertEquals(divide, calculator.calculate('/'), 0.001);
    }
        private static Stream<Arguments> CustomArgumentProviderDivide() {
            return Stream.of(
                    Arguments.of(2, 1, 2),
                    Arguments.of(4, 2, 2),
                    Arguments.of(1, 2, 0.5f),
                    Arguments.of(3, 4, 0.75f),
                    Arguments.of(7, 8, 0.875f)
            );

        }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, divide={2}")
    @CsvSource({
            "1, 0, ArithmeticException ",
            "2, 0, ArithmeticException ",
            "2, 0, ArithmeticException ",
            "1, 0, ArithmeticException ",
            "22, 0, ArithmeticException ",
            "56, 0, ArithmeticException ",
            "1, 0, ArithmeticException "
    })
    @Order(5)

    public void testDivideZero(int a, int b, Exception e){
        Calculator calculator = new Calculator(a,b);
        assertThrows(e.getClass(), () ->  calculator.calculate('/'));
    }

}
