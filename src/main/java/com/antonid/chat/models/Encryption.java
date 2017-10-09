package com.antonid.chat.models;

import javax.persistence.*;

@Entity
public class Encryption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToOne
    private Key key;

    public Encryption() {

    }

    public Encryption(Type type, Key key) {
        this.type = type;
        this.key = key;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @Entity
    public static class Key {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String privateKey;

        @Column
        private String publicKey;

        public Key() {

        }

        public Key(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public Key(String singleKey) {
            this.privateKey = singleKey;
            this.publicKey = singleKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

    public enum Type {
        CAESAR
    }

    @Override
    public String toString() {
        return "Encryption{" +
                "id=" + id +
                '}';
    }
}
