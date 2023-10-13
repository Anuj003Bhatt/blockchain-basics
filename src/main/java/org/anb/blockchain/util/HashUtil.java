package org.anb.blockchain.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This is a utility class that implements hashing of objects using SHA-256 algorithm.
 */
public abstract class HashUtil {

    // Static getInstance method is called with hashing SHA
    private static final MessageDigest DIGEST;
    private static final String DIGEST_ALGORITHM = "SHA-256";

    static {
        try {
            DIGEST = MessageDigest.getInstance(DIGEST_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is the public function exposed used by others to generate hex SHA-256 strings.
     * @param input : A serializable object that is converted to bytes and then hexadecimal string.
     * @return SHA-256 String
     */
    public static String getSHA256(Serializable input) {
        byte[] digest = DIGEST.digest(getBytes(input));
        return toHexString(digest);
    }

    /**
     * Extract bytes from a serializable object input.
     *
     * @param input : Serializable object that is converted to bytes
     * @return : Array of bytes after serializing input.
     */
    private static byte[] getBytes(Serializable input) {
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
        ) {
            out.writeObject(input);
            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function converts the byte array to a positive big integer which is then converted to a hexadecimal string
     * adding '0' at the start if the length of converted hexadecimal string is less than 64 to make it equal to 64.
     *
     * @param hash : Byte array to convert to hexadecimal string
     * @return Hexadecimal string
     */
    private static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
