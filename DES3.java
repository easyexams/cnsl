import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DES3 {

    public static void main(String[] args) {
        try {
            // Generate keys for DES2
            SecretKey desKey1 = generateDESKey();
            SecretKey desKey2 = generateDESKey();

            // Generate key for DES3 (Triple DES)
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            keyGen.init(168); // 168-bit key for 3DES
            SecretKey des3Key = keyGen.generateKey();

            String plaintext = "Sensitive Data";

            System.out.println("\n----- DES2 Encryption and Decryption -----");
            byte[] des2Encrypted = encryptWithDES2(plaintext, desKey1, desKey2);
            System.out.println("Encrypted (DES2): " + Base64.getEncoder().encodeToString(des2Encrypted));

            String des2Decrypted = decryptWithDES2(des2Encrypted, desKey1, desKey2);
            System.out.println("Decrypted (DES2): " + des2Decrypted);

            System.out.println("\n----- DES3 Encryption and Decryption -----");
            byte[] des3Encrypted = encryptWithDES3(plaintext, des3Key);
            System.out.println("Encrypted (DES3): " + Base64.getEncoder().encodeToString(des3Encrypted));

            String des3Decrypted = decryptWithDES3(des3Encrypted, des3Key);
            System.out.println("Decrypted (DES3): " + des3Decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate a DES key
    private static SecretKey generateDESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // 56-bit DES key
        return keyGen.generateKey();
    }

    // DES2 Encryption (Encrypt with DES1, then DES2)
    public static byte[] encryptWithDES2(String data, SecretKey key1, SecretKey key2) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // First encryption with key1
        cipher.init(Cipher.ENCRYPT_MODE, key1);
        byte[] firstPass = cipher.doFinal(data.getBytes());

        // Second encryption with key2
        cipher.init(Cipher.ENCRYPT_MODE, key2);
        return cipher.doFinal(firstPass);
    }

    // DES2 Decryption (Decrypt with DES2, then DES1)
    public static String decryptWithDES2(byte[] data, SecretKey key1, SecretKey key2) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // First decryption with key2
        cipher.init(Cipher.DECRYPT_MODE, key2);
        byte[] firstPass = cipher.doFinal(data);

        // Second decryption with key1
        cipher.init(Cipher.DECRYPT_MODE, key1);
        byte[] original = cipher.doFinal(firstPass);
        return new String(original);
    }

    // DES3 (Triple DES) Encryption using DESede
    public static byte[] encryptWithDES3(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data.getBytes());
    }

    // DES3 (Triple DES) Decryption using DESede
    public static String decryptWithDES3(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] original = cipher.doFinal(data);
        return new String(original);
    }
}
