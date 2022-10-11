package com.bpf.dynamicdatasource;

public class Module {
    public static void main(String[] args) {
        String a = "ABC1";
        String sc = "ABC1.1";
        String[] split = sc.split("\\.");
        String b = split[0];
//        String b = "ABC" + "1";
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
