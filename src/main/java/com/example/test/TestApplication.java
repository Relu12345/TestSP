package com.example.test;

import com.example.test.ObserverDesignPattern.MessageDecoder;
import com.example.test.ObserverDesignPattern.MessageProcessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        List<Message> messages = new ArrayList<>();

        try {
            messages = new ObjectMapper().readValue(
                    new URL("file:src/messages.json"),
                    new TypeReference<>() {
                    }
            );
        } catch (Exception e) {
            System.err.print(e);
        }

        MessageProcessor messageProcessor = new MessageProcessor();
        messageProcessor.addObserver(new MessageDecoder());

        for (Message m : messages) {
            messageProcessor.processMessage(m);
        }
    }
}