import com.it_academy.practice.junit_basics.Calkulator.Calculator;
import com.it_academy.practice.junit_basics.CustomArgumentProvider.CustomArgumentProviderSubtraction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CalculatorTest {

    Calculator calculator;



    @BeforeEach
    public void getCalculator(){
        calculator = new Calculator();
    }


    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource({
            "0, 0, 0",
            "1, 1, 2",
            "-1, 1, 0",
            "-1, -1, -2",
            "-1, 0, -1",
            "1, 0, 1",
            "1,-1, 0",
            "2147483647, 1, 2147483648",
            "2147483647, -1, 2147483646",
            "2147483647, 0, 2147483647",
            "-2147483648, 1, -2147483647",
            "-2147483648, -1, -2147483649",
            "-2147483648, 0, -2147483648",
            "-2147483649, 0, -2147483649",
            "2147483648, 0, 2147483648",
            "2147483647, 2147483647, 4294967294",
            "-2147483648, -2147483648, -4294967296"
    })
    @Order(3)
    @DisplayName("Add operation test")

    public void testAdd(int a, int b, float sum){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(sum, calculator.calculate('+'), 0.001);

    }


    @ParameterizedTest(name = "{index} => a={0}, b={1}, multiply={2}")
    @CsvFileSource(resources = "/multiplicationTest")
    @Order(2)
    @DisplayName("Multiplication test")

    public void testMultiply(int a, int b, int multiply){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(multiply, calculator.calculate('*'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, subtract={2}")
    @ArgumentsSource(CustomArgumentProviderSubtraction.class)
    @Order(1)
    @DisplayName("Subtraction test")

    public void testSubtract(int a, int b, int subtract){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(subtract, calculator.calculate('-'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, divide={2}")
    @MethodSource("CustomArgumentProviderDivide")
    @Order(4)
    @DisplayName("Division test")

    public void testDivide(int a, int b, float divide) {

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(divide, calculator.calculate('/'), 0.001);
    }
        private static Stream<Arguments> CustomArgumentProviderDivide() {
            return Stream.of(
                    Arguments.of(1, 1, 1),
                    Arguments.of(0, 1, 0),
                    Arguments.of(-1, 1, -1),
                    Arguments.of(1, -1, -1),
                    Arguments.of(-1, -1, 1),
                    Arguments.of(1, 2, 0.5f),
                    Arguments.of(1,-2, -0.5f),
                    Arguments.of(-1, 2, -0.5f),
                    Arguments.of(3, 4, 0.75f),
                    Arguments.of(-3, 4, -0.75f),
                    Arguments.of(-2147483648, 1, -2147483648),
//                    Arguments.of(-2147483648, -1, 2147483648),
                    Arguments.of(2147483647, 1, 2147483647),
                    Arguments.of(2147483647, -1, -2147483647)
//                    Arguments.of(2147483648, -1, -2147483648),
//                    Arguments.of(-2147483649, 1, -2147483649),
//                    Arguments.of(2147483648, 1, 2147483648)


            );

        }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, divide={2}")
    @CsvSource({
            "1, 0, ArithmeticException ",
            "-1, 0, ArithmeticException ",
            "0, 0, ArithmeticException ",
            "9, 0, ArithmeticException ",
            "10, 0, ArithmeticException ",
            "11, 0, ArithmeticException ",
            "2147483647, 0, ArithmeticException",
            "2147483648, 0, ArithmeticException",
            "2147483646, 0, ArithmeticException",
            "-2147483648, 0, ArithmeticException",
            "-2147483647, 0, ArithmeticException",
            "-2147483649, 0, ArithmeticException"

    })
    @Order(5)
    @DisplayName("Division by zero test")

    public void testDivideZero(int a, int b, Exception e){

        calculator.setA(a);
        calculator.setB(b);
        assertThrows(e.getClass(),() -> calculator.calculate('/'));
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, degree={2}")
    @CsvSource({
            "1, 1, 1",
            "4, 2, 2",
            "1, 3, 1",
            "2, 3, 1.26",
            "16, 2, 4",
            "1, -1, 1",
            "4, -1, 0.25",
            "0, 2, 0",

    })
    @Order(8)
    @DisplayName("Test Disabled")
    @Disabled

    public void testDisabled(int a, int b, float root){



    }
}
