import controller.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger _logger = LogManager.getRootLogger();
    public static void main(String[] args) {
        _logger.info("Program start");

        Calculator calc;
        try {
            calc = new Calculator("/home/grisha/Projects/Stack_Calculator/src/commands");
            System.out.println(calc.execute());
        } catch (Exception exception) {
            _logger.error(exception.getMessage());
        }
        _logger.info("Program end");
    }
}
