package com.example.demo.constant;

public class ApiConstants {

    // ===== BASE PATH =====
    public static final String ADMIN_BASE_PATH = "/admin";
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String SUPER_ADMIN_BASE_PATH = "/super-admin";

    // ===== ENDPOINTS =====
    public static final String GET_ALL_USERS = "/getAllUser";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String ASSIGN_ROLES = "/assign-roles";

    // ===== TAGS =====
    public static final String ADMIN_TAG_DESC = "Admin endpoints";
    public static final String AUTH_TAG = "Auth";
    public static final String AUTH_TAG_DESC = "Authentication endpoints";
    public static final String SUPER_ADMIN_TAG = "Super Admin";
    public static final String SUPER_ADMIN_TAG_DESC = "Super-admin endpoints";

    // ===== SECURITY =====
    public static final String BEARER_AUTH = "bearerAuth";

    // ===== OPERATIONS =====
    public static final String GET_ALL_USERS_SUMMARY = "Get all users";
    public static final String GET_ALL_USERS_DESC = "Returns a list of users (without passwords)";
    public static final String ASSIGN_ROLES_SUMMARY = "Assign roles";
    public static final String ASSIGN_ROLES_DESC = "Assigns one or more roles to a user";
    public static final String REGISTER_SUMMARY = "Register";
    public static final String REGISTER_DESC = "Creates a new user account";
    public static final String LOGIN_SUMMARY = "Login";
    public static final String LOGIN_DESC = "Authenticates user and returns a JWT token";

    // ===== RESPONSE CODES =====
    public static final String OK_200 = "200";
    public static final String BAD_REQUEST_400 = "400";
    public static final String UNAUTHORIZED_401 = "401";
    public static final String FORBIDDEN_403 = "403";

    // ===== RESPONSE DESCRIPTIONS =====
    public static final String OK = "OK";
    public static final String REGISTER_SUCCESS = "Registered successfully";
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String BAD_REQUEST = "Validation error / already exists";
    public static final String UNAUTHORIZED = "Token is invalid or expired";
    public static final String FORBIDDEN = "You don't have access";
    public static final String ASSIGN_ROLE_SUCCESS = "Assign role successfully";

    // ===== SECURITY EXPRESSIONS =====
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_SUPER_ADMIN = "hasRole('SUPER_ADMIN')";


    private ApiConstants() {
    }
}
