package com.BankingApplication.BankApplication.util;

import org.springframework.stereotype.Service;

import java.util.Random;

public class IdGeneratorService {

    private static final long MIN = 10000000000L; // smallest 11-digit number
    private static final long MAX = 99999999999L; // largest 11-digit number

    public static long generateId() {
        Random random = new Random();
        return MIN + ((long) (random.nextDouble() * (MAX - MIN)));
    }
}

