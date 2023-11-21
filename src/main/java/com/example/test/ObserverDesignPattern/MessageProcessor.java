package com.example.test.ObserverDesignPattern;

import com.example.test.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {
    private List<MessageObserver> observers = new ArrayList<>();

    public void addObserver(MessageObserver observer) {
        observers.add(observer);
    }

    public static String decodeMessage(String encodedMessage, String house) {
        StringBuilder decodedMessage = new StringBuilder();

        for (char c : encodedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                int diff = house.equalsIgnoreCase("atreides") ? 'J' - 'M' : 'O' - 'M';
                char decodedChar = (char) (c - diff);
                decodedMessage.append(decodedChar);
            } else {
                switch (c) {
                    case '^':
                        decodedMessage.append("a");
                        break;
                    case '`':
                        decodedMessage.append("c");
                        break;
                    case ')':
                        decodedMessage.append(",");
                        break;
                    case '"', '\u001D':
                        decodedMessage.append(" ");
                        break;
                    default:
                        decodedMessage.append(c);
                }
            }
        }
        return decodedMessage.toString();
    }

    public void processMessage(Message message) {
        String decodedMessage = decodeMessage(message.getMessage(), message.getHouse());
        for (MessageObserver observer : observers) {
            observer.onMessageDecoded(decodedMessage, message.getHouse());
        }
    }
}