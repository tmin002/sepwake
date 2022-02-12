package com.tminimal.sepwake;

public class OSdetect {

    public enum OStype {
        macOS, Windows, Linux, Other
    }

    public static OStype detectOS() {
        String name = System.getProperty("os.name").toLowerCase();
        if (name.contains("nux")) {
            return OStype.Linux;
        } else if (name.contains("win")) {
            return OStype.Windows;
        } else if (name.contains("mac")) {
            return OStype.macOS;
        } else return OStype.Other;
    }
}
