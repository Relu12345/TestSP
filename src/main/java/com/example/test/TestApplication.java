package com.example.test;

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

        for (Message m : messages) {
            if (m.getHouse().equals("atreides")) {
                String atreides = decodeMessage(m.getMessage(), m.getHouse());
                System.out.println("Casa atreides: " + atreides);
            } else if (m.getHouse().equals("harkonnen")) {
                String harkonnen = decodeMessage(m.getMessage(), m.getHouse());
                System.out.println("Casa harkonnen: " + harkonnen);
            } else {
                System.out.println("Casa " + m.getHouse() + " nu se afla printre mesaje!");
            }
        }
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
}