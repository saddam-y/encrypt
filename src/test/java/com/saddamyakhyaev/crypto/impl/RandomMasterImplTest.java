package com.saddamyakhyaev.crypto.impl;

import com.saddamyakhyaev.crypto.RandomMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class RandomMasterImplTest {

    RandomMaster randomMaster;

    public RandomMasterImplTest() {
        this.randomMaster = new RandomMasterImpl();
    }

    @RepeatedTest(10)
    void getRandomBigInteger() {

        var actualValue = randomMaster.getRandomBigInteger();

        assertTrue(actualValue.compareTo(BigInteger.valueOf(RandomMasterImpl.LOWER)) > 0);
        assertTrue(actualValue.compareTo(BigInteger.valueOf(RandomMasterImpl.UPPER)) < 0);
    }
}
