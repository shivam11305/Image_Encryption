package ayush;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Encrypt {

    KeyGenerator keyGenerator = null;
    SecretKey secretKey = null;
    Cipher cipher = null;

    public Encrypt() {
        try {
            /**
             * Create a Blowfish key
             */
            keyGenerator = KeyGenerator.getInstance("Blowfish");
            secretKey = keyGenerator.generateKey();

            /**
             * Create an instance of cipher mentioning the name of algorithm
             *     - Blowfish
             */
            cipher = Cipher.getInstance("Blowfish");
        } catch (NoSuchPaddingException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in); 
        System.out.print("Enter the name of the image to encrypt: ");
        String str= sc.nextLine();
        String fileToEncrypt = str;
        String encryptedFile = "encryptedFile.jpg";
        String decryptedFile = "decryptedFile.jpg";
        String directoryPath = "C:\\Users\\Shivam\\OneDrive\\Desktop";
        Encrypt Encrypt = new Encrypt();
        System.out.println("Starting Encryption...");
        Encrypt.encrypt(directoryPath + fileToEncrypt,
                directoryPath + encryptedFile);
        System.out.println("Encryption completed...");
        System.out.println("Starting Decryption...");
        Encrypt.decrypt(directoryPath + encryptedFile,
                directoryPath + decryptedFile);
        System.out.println("Decryption completed...");
    }

    /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Encrypts the file in srcPath and creates a file in destPath
     */
    private void encrypt(String srcPath, String destPath) {
     
        Scanner sce= new Scanner(System.in); 
        System.out.print("Enter the name of the image to encrypt: ");
        String st= sce.nextLine();
        //String fileToEncrypt = st;
        File rawFile = new File("C:\\Users\\Shivam\\OneDrive\\"+st);
        File encryptedFile = new File("C:\\Users\\Shivam\\OneDrive\\"+"encryptedFile.jpg");
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            /**
             * Initialize the cipher for encryption
             */
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(rawFile);
            outStream = new FileOutputStream(encryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Decrypts the file in srcPath and creates a file in destPath
     */
    private void decrypt(String srcPath, String destPath) {
        File encryptedFile = new File("C:\\Users\\Shivam\\OneDrive\\"+"encryptedFile.jpg");
        File decryptedFile = new File("C:\\Users\\Shivam\\OneDrive\\"+"decryptedFile.jpg");
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            /**
             * Initialize the cipher for decryption
             */
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(encryptedFile);
            outStream = new FileOutputStream(decryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}