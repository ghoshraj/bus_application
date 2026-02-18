package com.example.demo.constant;

public class ModelConstants {

    // ===== VALIDATION MESSAGES =====
    public static final String USER_ID_REQUIRED = "userId is required";
    public static final String ROLE_REQUIRED = "At least one role must be assigned";
    public static final String USERNAME_REQUIRED = "username is required";
    public static final String PASSWORD_REQUIRED = "password is required";
    public static final String PHONE_REQUIRED = "phone number is required";
    public static final String GENDER_REQUIRED = "gender is required";
    public static final String PHONE_INVALID = "phone number must be valid";
    public static final String EMAIL_REQUIRED = "email is required";
    public static final String EMAIL_INVALID = "email must be valid";
    public static final String NAME_REQUIRED = "name is required";

    // ===== TABLE NAMES =====
    public static final String USER_TABLE = "users";
    public static final String USER_ROLES_TABLE = "user_roles";

    // ===== COLUMN NAMES =====
    public static final String USER_ID = "user_id";
    public static final String ROLE = "role";

    // ===== SWAGGER SCHEMA DESCRIPTIONS =====
    public static final String USERNAME_DESC = "Email/username";
    public static final String PASSWORD_DESC = "Password";
    public static final String USER_ROLES_DESC = "Assigned roles";
    public static final String UNAUTHORIZED_DESC = "Unauthorized error response";
    public static final String FORBIDDEN_DESC = "Forbidden error response";
    public static final String BAD_REQUEST_DESC = "Bad request error response";

    // ===== SWAGGER EXAMPLES =====
    public static final String USERNAME_EXAMPLE = "Rajesh Ghosh";
    public static final String PASSWORD_EXAMPLE = "StrongPassword@123";
    public static final String USER_ID_EXAMPLE = "1";
    public static final String USER_EMAIL_EXAMPLE = "raj@example.com";
    public static final String USER_PHONE_EXAMPLE = "9876543210";
    public static final String USER_GENDER_EXAMPLE = "MALE";
    public static final String TOKEN_EXAMPLE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
    public static final String LOGIN_MESSAGE_EXAMPLE = "Login successful";

    // ===== TIMESTAMP EXAMPLE =====
    public static final String TIMESTAMP_EXAMPLE = "2026-02-05T14:45:59Z";

    private ModelConstants() {

    }
}
