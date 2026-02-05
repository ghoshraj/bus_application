package com.example.demo.constant;

public class ModelConstants {

    // ===== VALIDATION MESSAGES =====
    public static final String USER_ID_REQUIRED = "userId is required";
    public static final String ROLE_REQUIRED = "At least one role must be assigned";
    public static final String USERNAME_REQUIRED = "username is required";
    public static final String PASSWORD_REQUIRED = "password is required";
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

    // ===== SWAGGER EXAMPLES =====
    public static final String USERNAME_EXAMPLE = "Rajesh Ghosh";
    public static final String PASSWORD_EXAMPLE = "StrongPassword@123";
    public static final String USER_ID_EXAMPLE = "1";
    public static final String USER_NAME_EXAMPLE = "Raj";
    public static final String USER_EMAIL_EXAMPLE = "raj@example.com";
    public static final String USER_PHONE_EXAMPLE = "9876543210";
    public static final String USER_GENDER_EXAMPLE = "MALE";

    private ModelConstants() {

    }
}
