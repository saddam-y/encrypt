package com.saddamyakhyaev.crypto;

import com.saddamyakhyaev.crypto.exception.CryptoException;
import com.saddamyakhyaev.crypto.exception.MessageIsNotForThisUserException;

public interface Crypto {
    String getPublicKey(String privateKey);
    String getPrivateKey();
    byte[] encrypt(String publicKey, String privateKey, byte[] bytes);
    byte[] decrypt(String publicKey, String privateKey, byte[] bytes) throws CryptoException;
}
