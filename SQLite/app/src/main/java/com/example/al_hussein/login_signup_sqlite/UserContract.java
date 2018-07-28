package com.example.al_hussein.login_signup_sqlite;

public final class UserContract {

    private UserContract() {
    }

    public static class ContactEntry {
        public static final String TABLE_NAME = "user_info";
        public static final String CONTACT_ID = "user_id";
        public static final String NAME = "name";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
    }
}
