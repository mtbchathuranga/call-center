package com.wavenet.call.center;

import org.springframework.util.SerializationUtils;

import java.nio.charset.StandardCharsets;


public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.setId(1);
        a.setName("Buddhika");
        byte[] serialize = SerializationUtils.serialize(a);
        String s = new String(serialize, StandardCharsets.UTF_8);
        System.out.println(serialize.toString());
        System.out.println(s);
    }
}


