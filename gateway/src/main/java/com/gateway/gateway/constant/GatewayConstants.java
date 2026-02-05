package com.gateway.gateway.constant;

public class GatewayConstants {

    private GatewayConstants() {
        // prevent instantiation
    }

    // JWT claim keys
    public static final String CLAIM_ROLES = "roles";

    public static final String ADMIN = "ADMIN";
    public static final String SUPER_ADMIN = "SUPER_ADMIN";
    public static final String BUS_DRIVER = "BUS_DRIVER";
    public static final String USER = "USER";

    // Role prefix
    public static final String ROLE_PREFIX = "ROLE_";

    // Logs
    public static final String LOG_JWT_CLAIMS = "JWT Claims: {}";
    public static final String LOG_EXTRACTED_ROLES = "Extracted roles from JWT: {}";
    public static final String LOG_NO_ROLES = "No roles found in JWT token";
    public static final String LOG_CONVERTED_AUTHORITIES = "Converted authorities: {}";
    public static final String LOG_ROLE_EXTRACTION_ERROR = "Error extracting roles from JWT";

    // Companies
    public static final String COMPANY_BY_ID = "/companies/{id}";
    public static final String COMPANY_REGISTRATION = "/companies/registration/**";
    public static final String COMPANY_APPLY = "/companies/apply";

    // Super Admin
    public static final String SUPER_ADMIN_ALL = "/super-admin/**";

    // Schedules
    public static final String SCHEDULE_ASSIGN_DRIVER = "/schedules/{id}/assign-driver";
    public static final String SCHEDULES = "/schedules";
    public static final String SCHEDULE_BY_ID = "/schedules/{id}";
    public static final String SCHEDULE_ROUTE = "/schedules/route/**";

    // Drivers
    public static final String DRIVERS = "/drivers";
    public static final String DRIVER_BY_ID = "/drivers/{id}";
    public static final String DRIVER_OPERATOR = "/drivers/operator/**";
    public static final String DRIVER_VEHICLE = "/drivers/vehicle/**";

    // Users
    public static final String USER_BY_ID = "/users/{id}";
    public static final String USER_PROFILE = "/users/profile";

    // Seats
    public static final String SEAT_LOCK = "/seats/lock";
    public static final String SEAT_UNLOCK = "/seats/unlock";
    public static final String SEAT_AVAILABILITY = "/seats/availability/**";
    public static final String SEAT_BUS = "/seats/bus/**";

    // Routes
    public static final String ROUTE_BY_ID = "/routes/{id}";
    public static final String ROUTE_SEARCH = "/routes/search";
    public static final String ROUTES = "/routes";

    // Buses
    public static final String BUS_BY_ID = "/buses/{id}";
    public static final String BUS_OPERATOR = "/buses/operator/**";
    public static final String BUSES = "/buses";

    // Payments
    public static final String PAYMENT_INITIATE = "/payments/initiate";
    public static final String PAYMENT_VERIFY = "/payments/verify";
    public static final String PAYMENT_REFUND = "/payments/refund";

    // Bookings
    public static final String BOOKINGS = "/bookings";
    public static final String BOOKING_BY_ID = "/bookings/{id}";
    public static final String BOOKING_USER = "/bookings/user/**";
    public static final String BOOKING_CANCEL = "/bookings/{id}/cancel";

    // Auth
    public static final String AUTH_ALL = "/auth/**";

    // Common Swagger
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String WEBJARS = "/webjars/**";
    public static final String API_DOCS = "/v3/api-docs/**";
    public static final String FAVICON = "/favicon.ico";

    // Auth Service Swagger
    public static final String AUTH_SWAGGER_UI = "/auth/swagger-ui/**";
    public static final String AUTH_API_DOCS = "/auth/v3/api-docs/**";

    // User Service Swagger
    public static final String USERS_SWAGGER_UI = "/users/swagger-ui/**";
    public static final String USERS_API_DOCS = "/users/v3/api-docs/**";

    // Vehicle Service Swagger
    public static final String VEHICLES_SWAGGER_UI = "/vehicles/swagger-ui/**";
    public static final String VEHICLES_API_DOCS = "/vehicles/v3/api-docs/**";

    // Route Service Swagger
    public static final String ROUTES_SWAGGER_UI = "/routes/swagger-ui/**";
    public static final String ROUTES_API_DOCS = "/routes/v3/api-docs/**";

    // Schedule Service Swagger
    public static final String SCHEDULES_SWAGGER_UI = "/schedules/swagger-ui/**";
    public static final String SCHEDULES_API_DOCS = "/schedules/v3/api-docs/**";

    // Seat Service Swagger
    public static final String SEATS_SWAGGER_UI = "/seats/swagger-ui/**";
    public static final String SEATS_API_DOCS = "/seats/v3/api-docs/**";

    // Booking Service Swagger
    public static final String BOOKINGS_SWAGGER_UI = "/bookings/swagger-ui/**";
    public static final String BOOKINGS_API_DOCS = "/bookings/v3/api-docs/**";

    // Payment Service Swagger
    public static final String PAYMENTS_SWAGGER_UI = "/payments/swagger-ui/**";
    public static final String PAYMENTS_API_DOCS = "/payments/v3/api-docs/**";

    // JWT properties
    public static final String JWT_SECRET_KEY_PROPERTY =
            "spring.security.oauth2.resourceserver.jwt.secret-key";

}
