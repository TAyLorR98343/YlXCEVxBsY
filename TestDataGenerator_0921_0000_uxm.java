// 代码生成时间: 2025-09-21 00:00:48
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for generating test data.
 * This class provides functionality to generate various types of test data.
 */
@ManagedBean
@SessionScoped
public class TestDataGenerator {

    private static final Random random = new Random();
    private List<String> testStrings;

    /**
     * Initializes the managed bean.
     */
    public void init() {
        testStrings = new ArrayList<>();
    }

    /**
     * Generates a random string of a specified length.
     *
     * @param length The desired length of the random string.
     * @return A randomly generated string.
     */
    public String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero.");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }

    /**
     * Generates a list of random strings.
     *
     * @param count The number of strings to generate.
     * @param length The length of each string.
     * @return A list of randomly generated strings.
     */
    public List<String> generateRandomStrings(int count, int length) {
        if (count <= 0 || length <= 0) {
            throw new IllegalArgumentException("Count and length must be greater than zero.");
        }

        for (int i = 0; i < count; i++) {
            testStrings.add(generateRandomString(length));
        }
        return testStrings;
    }

    /**
     * Clears the list of test strings.
     */
    public void clearTestStrings() {
        testStrings.clear();
    }
}
