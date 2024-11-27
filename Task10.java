import java.security.*;

public class Task10 {
    public static void main(String[] args) {
        try {
            // Create MD5 message digest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Print information about the MessageDigest object
            System.out.println("Message digest object info:");
            System.out.println("Algorithm = " + md.getAlgorithm());
            System.out.println("ToString = " + md.toString());

            // Test cases
            String input;

            // Case 1: Empty input
            input = "";
            md.update(input.getBytes());
            byte[] output = md.digest();
            System.out.println("\nMD5(\"" + input + "\") = " + bytesToHex(output));

            // Case 2: "abc"
            input = "abc";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println("\nMD5(\"" + input + "\") = " + bytesToHex(output));

            // Case 3: "abcdefghijklmnopqrstuvwxyz"
            input = "abcdefghijklmnopqrstuvwxyz";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println("\nMD5(\"" + input + "\") = " + bytesToHex(output));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // Helper method to convert a byte array to a hexadecimal string
    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }

}
