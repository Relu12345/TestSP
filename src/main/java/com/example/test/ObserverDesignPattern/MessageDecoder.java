package com.example.test.ObserverDesignPattern;

public class MessageDecoder implements MessageObserver {
    @Override
    public void onMessageDecoded(String decodedMessage, String house) {
        System.out.println("Casa " + house + ": " + decodedMessage);
    }
}