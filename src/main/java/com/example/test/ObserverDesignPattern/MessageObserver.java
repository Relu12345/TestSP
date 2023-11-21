package com.example.test.ObserverDesignPattern;

public interface MessageObserver {
    void onMessageDecoded(String decodedMessage, String house);
}
