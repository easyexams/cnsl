import java.io.*;

public class Task5 {
    public static void main(String args[]) throws IOException {
        int temp = 0;
        String ptext;
        String key;
        int s[] = new int[256];
        int k[] = new int[256];

        // Input handling
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nENTER PLAIN TEXT:");
        ptext = in.readLine();
        System.out.println("\nENTER KEY TEXT:");
        key = in.readLine();

        // Conversion of input to character arrays
        char ptextc[] = ptext.toCharArray();
        char keyc[] = key.toCharArray();

        int cipher[] = new int[ptext.length()];
        int decrypt[] = new int[ptext.length()];
        int ptexti[] = new int[ptext.length()];
        int keyi[] = new int[key.length()];

        // Convert plaintext and key to integer arrays
        for (int i = 0; i < ptext.length(); i++) {
            ptexti[i] = (int) ptextc[i];
        }
        for (int i = 0; i < key.length(); i++) {
            keyi[i] = (int) keyc[i];
        }

        // Initialize S and K arrays
        for (int i = 0; i < 256; i++) {
            s[i] = i;
            k[i] = keyi[i % key.length()];
        }

        // Key scheduling algorithm (KSA)
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + k[i]) % 256;
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        // Pseudo-random generation algorithm (PRGA)
        int i = 0;
        j = 0;
        int z = 0;
        for (int l = 0; l < ptext.length(); l++) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            z = s[(s[i] + s[j]) % 256];
            cipher[l] = z ^ ptexti[l]; // Encryption
            decrypt[l] = z ^ cipher[l]; // Decryption
        }

        // Display results
        System.out.println("\nENCRYPTED:");
        display(cipher);
        System.out.println("\nDECRYPTED:");
        display(decrypt);
    }

    // Helper method to display an array as a string
    static void display(int disp[]) {
        char convert[] = new char[disp.length];
        for (int l = 0; l < disp.length; l++) {
            convert[l] = (char) disp[l];
            System.out.print(convert[l]);
        }
    }
}
