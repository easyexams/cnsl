import java.math.BigInteger;
import java.util.Random;
import java.io.*;

public class Task7 {
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private Random r;

    // Default constructor to generate keys
    public Task7() {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    // Constructor for custom keys
    public Task7(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public static void main(String[] args) throws IOException {
        Task7 rsa = new Task7();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String testString;

        // Get plaintext input
        System.out.println("Enter the plain text:");
        testString = in.readLine();

        // Display original text
        System.out.println("Encrypting String: " + testString);
        System.out.println("String in Bytes: " + bytesToString(testString.getBytes()));

        // Encrypt
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));

        // Decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted String in Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }

    // Helper method to convert bytes to a readable string
    private static String bytesToString(byte[] encrypted) {
        StringBuilder result = new StringBuilder();
        for (byte b : encrypted) {
            result.append(Byte.toString(b)).append(" ");
        }
        return result.toString().trim();
    }

    // Encrypt message
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

}
