package com.antonid.chat.crypto;

public class CaesarCipher implements Cipher {

    public String encrypt(String input, String keyString) {
        checkKeyString(keyString);

        int key = Integer.valueOf(keyString);

        StringBuilder result = new StringBuilder();
        int len = input.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (input.charAt(x) + key);
            result.append(c);
        }
        return result.toString();
    }

    public String decrypt(String encrypted, String keyString) {
        checkKeyString(keyString);

        int key = Integer.valueOf(keyString);

        StringBuilder result = new StringBuilder();
        int len = encrypted.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (encrypted.charAt(x) - key);
            result.append(c);
        }
        return result.toString();
    }

    private void checkKeyString(String keyString) {
        if (keyString == null) {
            throw new IllegalArgumentException("Key must be not null.");
        }

        try {
            int key = Integer.valueOf(keyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Key string must be integer. Got: %s", keyString));
        }
    }

}
