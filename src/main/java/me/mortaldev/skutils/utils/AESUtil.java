package me.mortaldev.skutils.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {

  private static final String ALGORITHM = "AES";

  public static Key generateKey(String keyValue) throws Exception {
    byte[] byteKey = keyValue.getBytes(StandardCharsets.UTF_8);
    MessageDigest sha = MessageDigest.getInstance("SHA-1");
    byteKey = sha.digest(byteKey);
    byteKey = Arrays.copyOf(byteKey, 16); // Use only first 128 bit (16 bytes).

    return new SecretKeySpec(byteKey, ALGORITHM);
  }

  public static String encrypt(String dataToEncrypt, Key key) throws Exception {
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.ENCRYPT_MODE, key);

    byte[] encVal = c.doFinal(dataToEncrypt.getBytes());
    return Base64.getEncoder().encodeToString(encVal);
  }

  public static String decrypt(String encryptedData, Key key) throws Exception {
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.DECRYPT_MODE, key);

    byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
    byte[] decValue = c.doFinal(decodedValue);
    return new String(decValue);
  }
}