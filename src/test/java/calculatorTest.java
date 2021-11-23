import com.it_academy.practice.junit_basics.Calculator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class calculatorTest {

    @Test
    @Order(2)
    public void testAdd(){
        Calculator calculator = new Calculator(2,3);
        assertEquals(5, calculator.calculate('+'), 0.001);
    }
    @Test
    @Order(1)
    public void testAdd2(){
        Calculator calculator = new Calculator(3,3);
        assertEquals(6, calculator.calculate('+'), 0.001);
    }
}
