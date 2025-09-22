// 代码生成时间: 2025-09-23 01:09:35
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HashValueCalculator is a JSF Managed Bean used to calculate hash values of strings.
 */
@ManagedBean
@RequestScoped
public class HashValueCalculator {

    private String inputString;
    private String hashValue;
    private String algorithm = "SHA-256";

    // Logger for any error messages
    private static final Logger LOGGER = Logger.getLogger(HashValueCalculator.class.getName());

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Method to calculate hash value of the input string.
     *
     * @return hash value as a string or an error message.
     */
    public String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            hashValue = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Algorithm not found: " + algorithm, ex);
            hashValue = "Error: Algorithm not found.";
        }
        return hashValue;
    }
}
