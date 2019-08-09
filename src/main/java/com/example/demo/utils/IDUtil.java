package com.example.demo.utils;

public class IDUtil {

    private static SnowFlakeIdWorker snowFlakeIdWorker;


    private static SnowFlakeIdWorker init() {
        if (snowFlakeIdWorker != null) {
            return snowFlakeIdWorker;
        } else {
            snowFlakeIdWorker = new SnowFlakeIdWorker(1, 1);
            return snowFlakeIdWorker;

        }
    }

    public static String generate() {
        String prefix = "Fast-";
        return prefix + init().nextId();
    }


}
