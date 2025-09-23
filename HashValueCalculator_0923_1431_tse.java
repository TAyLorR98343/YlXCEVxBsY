// 代码生成时间: 2025-09-23 14:31:16
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

/**
 * Managed Bean for calculating hash values using JSF.
 */
@ManagedBean
@RequestScoped
public class HashValueCalculator {
    
    private String inputText;
    private String hashValue;
    
    /**
     * Default constructor for HashValueCalculator bean.
     */
    public HashValueCalculator() {
        this.inputText = "";
        this.hashValue = "";
    }
    
    /**
     * Calculates the hash value of the input text using SHA-256.
     *
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public String calculateHash() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(inputText.getBytes(StandardCharsets.UTF_8));
        this.hashValue = bytesToHex(hash);
        return hashValue;
    }
    
    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The byte array to convert.
     * @return A hexadecimal string representation of the byte array.
     */
    private String bytesToHex(byte[] bytes) {
        BigInteger number = new BigInteger(1, bytes);
        String hexString = number.toString(16);
        while (hexString.length() < 32) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
    
    /**
     * Handles the hash calculation request, shows a success message,
     * and resets input text to empty.
     */
    public void handleHashRequest() {
        try {
            calculateHash();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hash calculated successfully.", ""));
            inputText = "";
        } catch (NoSuchAlgorithmException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error calculating hash.", "SHA-256 algorithm not found."));
        }
    }
    
    // Getters and setters for inputText and hashValue
    public String getInputText() {
        return inputText;
    }
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
    public String getHashValue() {
        return hashValue;
    }
    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }
}
