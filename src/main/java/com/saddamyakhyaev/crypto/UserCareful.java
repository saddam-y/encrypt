package com.saddamyakhyaev.crypto;

import com.saddamyakhyaev.crypto.exception.CryptoException;
import com.saddamyakhyaev.crypto.exception.NotSetCryptoStrategyException;

public interface UserCareful {
    String getPublicKey();
    byte[] messageEncrypt(String text, UserCareful toUser) throws CryptoException;
    String messageDecrypt(byte[] message, UserCareful fromUser) throws CryptoException;
}
