package com.saddamyakhyaev.crypto.exception;

public class MessageIsNotForThisUserException extends CryptoException {
    public MessageIsNotForThisUserException() {
        super("Message is not for this user Message");
    }
}
