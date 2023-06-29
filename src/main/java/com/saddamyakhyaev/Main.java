package com.saddamyakhyaev;

import com.saddamyakhyaev.crypto.Crypto;
import com.saddamyakhyaev.crypto.RandomMaster;
import com.saddamyakhyaev.crypto.diffiehellman.DiffieHellmanCrypto;
import com.saddamyakhyaev.crypto.exception.CryptoException;
import com.saddamyakhyaev.crypto.exception.NotSetCryptoStrategyException;
import com.saddamyakhyaev.crypto.impl.RandomMasterImpl;
import com.saddamyakhyaev.crypto.impl.UserCarefulImpl;

public class Main {
    public static void main(String[] args) {

        RandomMaster randomMaster = new RandomMasterImpl();
        Crypto crypto = new DiffieHellmanCrypto(randomMaster);

        UserCarefulImpl userOleg = new UserCarefulImpl("Олег", crypto);
        UserCarefulImpl userNadya = new UserCarefulImpl("Надя", crypto);

        try {
            var messageForNadya = userOleg.messageEncrypt("Привет, Надя", userNadya);
            System.out.println(userNadya.messageDecrypt(messageForNadya, userOleg));
        } catch (CryptoException e) {
            e.printStackTrace();
        }
        
    }
}
