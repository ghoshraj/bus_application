package com.example.demo.constant;

public final class SecurityConstants {

    // ===== API PATHS =====
    public static final String AUTH_BASE = "/auth/**";
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String API_DOCS = "/v3/api-docs/**";

    public static final String SUPER_ADMIN_API = "/super-admin/**";
    public static final String ADMIN_API = "/admin/**";

    // ===== ROLES =====
    public static final String ROLE_SUPER_ADMIN = "SUPER_ADMIN";
    public static final String ROLE_ADMIN = "ADMIN";

    // ===== FILTER =====
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";

    // ==== USER_DETAILS ====
    public static final String ROLE = "ROLE_";
    public static final String ROLES = "roles";


    // Prevent instantiation
    private SecurityConstants() {
    }
}

