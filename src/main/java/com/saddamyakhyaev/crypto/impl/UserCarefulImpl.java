package com.saddamyakhyaev.crypto.impl;

import com.saddamyakhyaev.crypto.Crypto;
import com.saddamyakhyaev.crypto.UserCareful;
import com.saddamyakhyaev.crypto.exception.CryptoException;
import com.saddamyakhyaev.crypto.exception.NotSetCryptoStrategyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCarefulImpl implements UserCareful {

    private String publicKey;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String privateKey;

    private final String login;
    private final Crypto crypto;

    public UserCarefulImpl(String login, Crypto crypto) {
        this.login = login;
        this.crypto = crypto;

        this.privateKey = crypto.getPrivateKey();
        this.publicKey = crypto.getPublicKey(this.privateKey);
    }

    @Override
    public byte[] messageEncrypt(String text, UserCareful toUser) throws CryptoException {
        if(crypto == null) {
            throw new NotSetCryptoStrategyException();
        }
        return crypto.encrypt(toUser.getPublicKey(), privateKey, text.getBytes());
    }

    @Override
    public String messageDecrypt(byte[] message, UserCareful fromUser) throws CryptoException {
        if(crypto == null) {
            throw new NotSetCryptoStrategyException();
        }
        return new String(crypto.decrypt(fromUser.getPublicKey(), privateKey, message));
    }


}
