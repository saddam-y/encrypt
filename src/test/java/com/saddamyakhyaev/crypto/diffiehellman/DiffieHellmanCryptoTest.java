package com.saddamyakhyaev.crypto.diffiehellman;

import com.saddamyakhyaev.crypto.Crypto;
import com.saddamyakhyaev.crypto.RandomMaster;
import com.saddamyakhyaev.crypto.exception.MessageIsNotForThisUserException;
import com.saddamyakhyaev.crypto.impl.RandomMasterImpl;
import com.saddamyakhyaev.crypto.impl.UserCarefulImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiffieHellmanCryptoTest {

    RandomMaster randomMaster;
    Crypto crypto;

    public DiffieHellmanCryptoTest() {
        this.randomMaster = new RandomMasterImpl();
        this.crypto = new DiffieHellmanCrypto(randomMaster);
    }

    @Test
    @DisplayName("The user message must be correctly decrypted")
    @SneakyThrows
    public void correctDecryptUserMessage() {

        var userAlex = new UserCarefulImpl("Alex", crypto);
        var userMaks = new UserCarefulImpl("Maks", crypto);
        var expectMessage = "Before that, the message will be encrypted, and after that it will be decrypted";

        var cryptMessage = userAlex.messageEncrypt(expectMessage, userMaks);
        var actualMessage = userMaks.messageDecrypt(cryptMessage, userAlex);

        assertEquals(actualMessage, expectMessage);
    }

    @Test
    @DisplayName("The user's message should not be decrypted because it is not his message")
    @SneakyThrows
    public void messageShouldNotBeDecryptedBecauseItIsNotHisMessage() {

        var userAlex = new UserCarefulImpl("Alex", crypto);
        var userMaks = new UserCarefulImpl("Maks", crypto);
        var userDany = new UserCarefulImpl("Dany", crypto);
        var expectMessage = "Before that, the message will be encrypted, and after that it will be decrypted if it works out";

        var cryptMessageForMaks = userAlex.messageEncrypt(expectMessage, userMaks);

        assertThrowsExactly(MessageIsNotForThisUserException.class, () -> userDany.messageDecrypt(cryptMessageForMaks, userAlex));
    }

}
