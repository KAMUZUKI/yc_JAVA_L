package com.mu.utils;

public class Utils {
    public static String transferSize(long size) {
        if (size / 1024 / 1024 / 1024 / 1024 > 0) {
            return size / 1024 / 1024 / 1024 / 1024 + "T";
        } else if (size / 1024 / 1024 / 1024 > 0) {
            return size / 1024 / 1024 / 1024 + "G";
        } else if (size / 1024 / 1024 > 0) {
            return size / 1024 / 1024 + "M";
        } else if (size / 1024 > 0) {
            return size / 1024 + "k";
        } else {
            return size + "B";
        }
    }
}
