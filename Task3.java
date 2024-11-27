
// import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.util.Base64; // Use Base64 from java.util

public class Task3 {
    public static void main(String[] args) throws Exception {
        // Generate a Blowfish key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();

        // Initialize the cipher in encryption mode
        Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");
        cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encode and display the Initialization Vector (IV)
        byte iv[] = cipherOut.getIV();
        if (iv != null) {
            String encodedIV = Base64.getEncoder().encodeToString(iv); // Encode using java.util.Base64
            System.out.println("Initialization Vector of the Cipher: " + encodedIV);
        }

        // File input/output streams
        FileInputStream fin = new FileInputStream("inputFile.txt");
        FileOutputStream fout = new FileOutputStream("outputFile.txt");
        CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);

        // Read from input file and write encrypted data to output file
        int input = 0;
        while ((input = fin.read()) != -1) {
            cout.write(input);
        }

        // Close the streams
        fin.close();
        cout.close();
    }

}
