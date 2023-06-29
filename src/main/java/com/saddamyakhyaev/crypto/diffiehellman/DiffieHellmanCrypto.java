package com.saddamyakhyaev.crypto.diffiehellman;

import com.saddamyakhyaev.crypto.Crypto;
import com.saddamyakhyaev.crypto.RandomMaster;
import com.saddamyakhyaev.crypto.exception.CryptoException;
import com.saddamyakhyaev.crypto.exception.MessageIsNotForThisUserException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * This class should implement the Diffie–Hellman key exchange protocol
 */
public class DiffieHellmanCrypto implements Crypto {


    public static final BigInteger PARAM = BigInteger.valueOf(12647123213L);
    public static final BigInteger PARAM_MOD = BigInteger.valueOf(126473L);
    public static final String KEY_SUFFIX = "Hello, Crypto World. I want to get to know you better";

    private final RandomMaster randomMaster;

    public DiffieHellmanCrypto(RandomMaster randomMaster) {
        this.randomMaster = randomMaster;
    }

    @Override
    public String getPublicKey(String privateKey) {
        return PARAM.modPow(new BigInteger(privateKey), PARAM_MOD)
                .toString();
    }

    @Override
    public String getPrivateKey() {
        return randomMaster.getRandomBigInteger()
                .toString();
    }

    @Override
    public byte[] encrypt(String publicKey, String privateKey, byte[] bytes) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(publicKey, privateKey));

            return cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                BadPaddingException |
                InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(String publicKey, String privateKey, byte[] bytes) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, getKey(publicKey, privateKey));

            return cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            throw new MessageIsNotForThisUserException();
        }
        return null;
    }


    private Key getKey(String publicKey, String privateKey) {
        var code = getCode(publicKey, privateKey);
        return new SecretKeySpec(code.concat(KEY_SUFFIX).substring(0, 32).getBytes(), "AES");
    }


    private String getCode(String publicKey, String privateKey) {
        return new BigInteger(publicKey).modPow(new BigInteger(privateKey), PARAM_MOD)
                .toString();
    }
}
