import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

public class Task12 {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.genKeyPair();

        // Input data to sign
        byte[] data = "Sample Text".getBytes("UTF8");

        // Create and initialize the signature object for signing
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initSign(keyPair.getPrivate());
        sig.update(data);

        // Generate the digital signature
        byte[] signatureBytes = sig.sign();
        System.out.println("Signature: \n" + Base64.getEncoder().encodeToString(signatureBytes));

        // Verify the signature
        sig.initVerify(keyPair.getPublic());
        sig.update(data);
        System.out.println("Signature verification result: " + sig.verify(signatureBytes));
    }
}
