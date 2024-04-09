package test;

import controller.Calculator;
import model.exceptions.NotFoundOperationsException;
import model.operations.exceptions.OperationException;
import model.operations.exceptions.arithmetic.ConstantNotDefineException;
import model.operations.exceptions.arithmetic.ExtractRootFromNegativeNumberException;
import model.operations.exceptions.arithmetic.ZeroDivisionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Ref;

public class AppTest {
    private static final String _commandsDir = "/home/grisha/Projects/Stack_Calculator/src/commands";
    private static final Logger _logger = LogManager.getRootLogger();

    @BeforeAll
    static void startLogInfo() {
        _logger.info("Start testing");
    }

    @AfterAll
    static void endLogInfo() {
        _logger.info("End testing");
    }

    @Test
    @DisplayName("TEST_ADDITION")
    void testAddition() {
        System.out.println("=======TEST_ADDITION_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 15\n" +
                    "DEFINE b 6\n" +
                    "+\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Object result = calc.execute();
            System.out.println("15 + 6 = " + result);
            Assertions.assertInstanceOf(Double.class, result);
            Assertions.assertEquals(21.0, (Double) result);
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_ADDITION_END=======");
    }

    @Test
    @DisplayName("TEST_DIVISION")
    void testDivision() {
        System.out.println("=======TEST_DIVISION_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 15\n" +
                    "DEFINE b 6\n" +
                    "/\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Object result = calc.execute();
            System.out.println("6 / 15 = " + result);
            Assertions.assertInstanceOf(Double.class, result);
            Assertions.assertEquals(0.4, (Double) result);
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_DIVISION_END=======");
    }

    @Test
    @DisplayName("TEST_DIVISION_BY_ZERO")
    void testDivisionByZero() {
        System.out.println("=======TEST_DIVISION_BY_ZERO_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 0\n" +
                    "DEFINE b 6\n" +
                    "/\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Throwable exception = Assertions.assertThrows(ZeroDivisionException.class, calc::execute);
            System.out.println(exception.getMessage());
        } catch (IOException | NotFoundOperationsException exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_DIVISION_BY_ZERO_END=======");
    }

    @Test
    @DisplayName("TEST_MULTIPLICATION")
    void testMultiplication() {
        System.out.println("=======TEST_MULTIPLICATION_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 15\n" +
                    "DEFINE b 6\n" +
                    "*\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Object result = calc.execute();
            System.out.println("15 * 6 = " + result);
            Assertions.assertInstanceOf(Double.class, result);
            Assertions.assertEquals(90.0, (Double) result);
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_MULTIPLICATION_END=======");
    }

    @Test
    @DisplayName("TEST_SQRT")
    void testSQRT() {
        System.out.println("=======TEST_SQRT_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "DEFINE a 25\n" +
                    "SQRT\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Object result = calc.execute();
            System.out.println("SQRT(25) = " + result);
            Assertions.assertInstanceOf(Double.class, result);
            Assertions.assertEquals(5.0, (Double) result);
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_SQRT_END=======");
    }

    @Test
    @DisplayName("TEST_SQRT_FROM_NEGATIVE_NUMBER")
    void testSQRTFromNegativeNumber() {
        System.out.println("=======TEST_SQRT_FROM_NEGATIVE_NUMBER_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "DEFINE a -25\n" +
                    "SQRT\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Throwable exception = Assertions.assertThrows(ExtractRootFromNegativeNumberException.class, calc::execute);
            System.out.println(exception.getMessage());
        } catch (IOException | NotFoundOperationsException exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_SQRT_FROM_NEGATIVE_NUMBER_END=======");
    }

    @Test
    @DisplayName("TEST_SUBTRACTION")
    void testSubtraction() {
        System.out.println("=======TEST_SUBTRACTION_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 15\n" +
                    "DEFINE b 6\n" +
                    "-\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Object result = calc.execute();
            System.out.println("6 - 15 = " + result);
            Assertions.assertInstanceOf(Double.class, result);
            Assertions.assertEquals(-9.0, (Double) result);
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_SUBTRACTION_END=======");
    }

    @Test
    @DisplayName("TEST_DEFINE")
    void testDefine() {
        System.out.println("=======TEST_DEFINE_START=======");
        try (FileWriter writer = new FileWriter(_commandsDir, false)) {
            writer.write("PUSH a\n" +
                    "PUSH b\n" +
                    "DEFINE a 15\n" +
                    "-\n" +
                    "POP\n");
            writer.close();
            Calculator calc = new Calculator(_commandsDir);
            Throwable exception = Assertions.assertThrows(ConstantNotDefineException.class, calc::execute);
            System.out.println(exception.getMessage());
        } catch (IOException | NotFoundOperationsException exception) {
            _logger.error(exception.getMessage());
            Assertions.fail();
        }
        System.out.println("=======TEST_DEFINE_END=======");
    }
}
