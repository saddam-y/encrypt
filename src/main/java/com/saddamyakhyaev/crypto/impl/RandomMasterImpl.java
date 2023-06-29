package com.saddamyakhyaev.crypto.impl;

import com.saddamyakhyaev.crypto.RandomMaster;

import java.math.BigInteger;
import java.util.Random;

public class RandomMasterImpl implements RandomMaster {
    public static final long LOWER = 1000000;
    public static final long UPPER = Long.MAX_VALUE;

    @Override
    public BigInteger getRandomBigInteger() {
        var random = new Random();
        return BigInteger.valueOf(random.longs(RandomMasterImpl.LOWER, UPPER).findAny().getAsLong());
    }
}
