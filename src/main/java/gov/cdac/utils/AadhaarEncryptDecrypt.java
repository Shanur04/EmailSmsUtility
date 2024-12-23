package gov.cdac.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * This class is used for Encrypting and decrypting Aadhaar No.
 * 
 * @author Deepali Pingle
 *
 */
public class AadhaarEncryptDecrypt {
	 private static final String encryptionKey           = "ABCDEFGHIJKLMNOP";
	 private static final String characterEncoding       = "UTF-8";
	 private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
	 private static final String aesEncryptionAlgorithem = "AES";
	 
	 
	 /**Encrypts Aadhaar No
	  * 
	  * @param plainText
	  * @return cipherText
	  */
	 public static String encrypt(String plainText) {
	        String encryptedText = "";
	        try {
	            Cipher cipher   = Cipher.getInstance(cipherTransformation);
	            byte[] key      = encryptionKey.getBytes(characterEncoding);
	            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
	            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
	            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
	            Base64.Encoder encoder = Base64.getEncoder();
	            encryptedText = encoder.encodeToString(cipherText);

	        } catch (Exception E) {
	             System.err.println("Encrypt Exception : "+E.getMessage());
	        }
	        return encryptedText;
	    }

	 /**decrypts Aadhaar No
	  * 
	  * @param plainText
	  * @return cipherText
	  */
	 public static String decrypt(String encryptedText) {
	        String decryptedText = "";
	        try {
	            Cipher cipher = Cipher.getInstance(cipherTransformation);
	            byte[] key = encryptionKey.getBytes(characterEncoding);
	            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
	            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
	            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
	            Base64.Decoder decoder = Base64.getDecoder();
	            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
	            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

	        } catch (Exception E) {
	            System.err.println("decrypt Exception : "+E.getMessage());
	        }
	        return decryptedText;
	    }
	 

}
