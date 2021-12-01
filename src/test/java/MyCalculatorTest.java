import com.it_academy.practice.junit_basics.MyCalculator.Calculator;
import com.it_academy.practice.junit_basics.CustomArgumentProvider.CustomArgumentProviderSubtraction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class MyCalculatorTest {
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
            "1.5,-1.5, 0",
            "1.5,-1, 0.5",
            "1,-1.5, -0.5",
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
    @DisplayName("Multiply test")

    public void testMultiply(int a, int b, int multiply){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(multiply, calculator.calculate('*'), 0.001);
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, subtract={2}")
    @ArgumentsSource(CustomArgumentProviderSubtraction.class)
    @Order(1)
    @DisplayName("Subtract test")

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
                Arguments.of(-1, -1.6, 0.625),
                Arguments.of(-1.6, -1, 1.6),
                Arguments.of(-1.6, -1.6, 1),
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
            "1, 0, 1",
            "-1, 0, -1 ",
            "0, 0, 0 ",
            "9, 0, 9 ",
            "10, 0, 10 ",
            "11, 0, 11 ",
            "-1.1, 0, -1.1 ",
            "2147483647, 0, 2147483647",
            "2147483648, 0, 2147483648",
            "2147483646, 0, 2147483646",
            "-2147483648, 0, -2147483648",
            "-2147483647, 0, -2147483647",
            "-2147483649, 0, -2147483649"

    })
    @Order(5)
    @DisplayName("Division by zero test")

    public void testDivideZero(int a, int b, float divide){
        calculator.setA(a);
        calculator.setB(b);
        assertEquals(divide, calculator.calculate('/'));
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, degree={2}")
    @CsvSource({
            "0, 1, 0",
            "1, 0, 1",
            "-1, 1, -1",
            "-1, -1, -1",
            "-1, 0, 1",
            "1, -1, 1",
            "2, 2, 4",
            "-2, 2, 4",
            "2, -2, 0.25",
            "1.1, 2, 1.21",
            "2, 1.9, 3.7321319661472296",
            "-2147483647, 2, 4611686014132420609",
            "2147483646, 2, 4611686009837453316",
            "2147483647, 2, 4611686014132420609",
            "2147483648, 2, 4611686018427387904",
            "-2147483648, 2, 4611686018427387904",
            "-2147483648, 30, 9076030935533343889148330677184451660957398691768765008885326289770145612551296225251271450782204288267814476258502032778653474399077793626653018683486295323382390383590453332169716856898789897889643528945016096228440849041002686084943230837088977557446564364344140092918489677824",
            "-2147483648, 50, Infinity",
            "-2147483649, 2, 4611686022722355201"

    })
    @Order(6)
    @DisplayName("Exponentiation test")

    public void testDegree(int a, int b, float degree){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(degree, calculator.calculate('^'));

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
            "2, 1.4, 1.6406707120153",
            "1.4, 2, 1.1832159566199",
            "2, 0, Infinity",
            "-2, 2, NaN",
            "2147483647, 0, Infinity",
            "2147483648, 0, Infinity",
            "2147483646, 0, Infinity",
            "-2147483648, 0, Infinity",
            "-2147483647, 0, Infinity",
            "-2147483649, 0, Infinity"


    })
    @Order(7)
    @DisplayName("Nth root extraction test")

    public void testRoot(int a, int b, float root){

        calculator.setA(a);
        calculator.setB(b);
        assertEquals(root, calculator.calculate('@'), 0.001);

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

    /*========================================================================
    Если можно, то хотелось бы примерчик для этого теста, чтобы параметры для int[] num
    брались из аннотаций  @CsvSource, @CsvFileSource.
=================================================================================*/
    @ParameterizedTest(name = "{index} => sum={0}, count={1}, int [] num={2}")
    @MethodSource("testFunc")
    @Order(10)
    @DisplayName("My calkulator myCalculateAdd test")

    public void testmyCalculateAdd(int sum, int count, int... num) {

        assertEquals(sum, calculator.myCalculateAdd(count,  num));
    }
       private static Stream<Arguments> testFunc() {
            return Stream.of(
                    Arguments.of(3,3, new int [] {1, 1, 1, 1, 1, 1, 1}),
                    Arguments.of(0, 3, new int [] {1}),
                    Arguments.of(21, 0, new int [] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}),
                    Arguments.of(-2147483648, 0, new int [] {-2147483648,-1, 1}),
                    Arguments.of(-2147483648, 0, new int [] {-2147483648,-1, -1, 1, 1}),
                    Arguments.of(0, -10, new int [] {-2147483648,-1, -1, 1, 1}),
                    Arguments.of(0, 3, new int [] {1,1})
            );
        }

    @Test

    @Order(9)
    @DisplayName("My Calculate Add test assertAll")

    public void myCalculateAdd(){
        Assertions.assertAll(
                () -> assertEquals(0, calculator.myCalculateAdd(3,1,1)),
                () -> assertEquals(0, calculator.myCalculateAdd(3,1)),
                () -> assertEquals(3, calculator.myCalculateAdd(3,1,1,1,1,1,1)),
                () -> assertEquals(9, calculator.myCalculateAdd(0,1,1,1,1,1,1,1,1,1))
        );

    }

}
