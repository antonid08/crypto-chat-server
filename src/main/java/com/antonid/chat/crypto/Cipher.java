package com.antonid.chat.crypto;

public interface Cipher {

    String encrypt(String input, String key);

    String decrypt(String input, String key);
}
