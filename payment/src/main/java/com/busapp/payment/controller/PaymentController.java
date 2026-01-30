package com.busapp.payment.controller;

import com.busapp.payment.entity.Payment;
import com.busapp.payment.model.*;
import com.busapp.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // -------------------- PROCESS PAYMENT --------------------

    @Operation(summary = "Process payment", description = "Processes a payment request and creates a payment record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment processed successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payment request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.processPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    // -------------------- INITIATE PAYMENT --------------------

    @Operation(summary = "Initiate payment", description = "Initiates a payment and generates a transaction reference")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment initiated successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid initiate payment request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@Valid @RequestBody PaymentInitiateRequest paymentRequest) {
        Payment payment = paymentService.initiatePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    // -------------------- VERIFY PAYMENT --------------------

    @Operation(summary = "Verify payment", description = "Verifies a payment using transaction details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment verified successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid verification request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/verify")
    public ResponseEntity<Payment> verifyPayment(@Valid @RequestBody PaymentVerifyRequest verifyRequest) {
        Payment payment = paymentService.verifyPayment(verifyRequest);
        return ResponseEntity.ok(payment);
    }

    // -------------------- REFUND PAYMENT --------------------

    @Operation(summary = "Refund payment", description = "Processes a refund for a completed payment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment refunded successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid refund request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/refund")
    public ResponseEntity<Payment> refundPayment(@Valid @RequestBody RefundRequest refundRequest) {
        Payment payment = paymentService.refundPayment(refundRequest);
        return ResponseEntity.ok(payment);
    }

    // -------------------- GET PAYMENT BY ID --------------------

    @Operation(summary = "Get payment by ID", description = "Fetch payment details using payment ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment fetched successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    // -------------------- GET PAYMENTS BY BOOKING --------------------

    @Operation(summary = "Get payments by booking ID", description = "Fetch all payments related to a booking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payments fetched successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Payment.class)))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsByBookingId(@PathVariable Integer bookingId) {
        List<Payment> payments = paymentService.getPaymentsByBookingId(bookingId);
        return ResponseEntity.ok(payments);
    }

    // -------------------- GET PAYMENT BY TRANSACTION --------------------

    @Operation(summary = "Get payment by transaction ID", description = "Fetch payment details using transaction ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Payment fetched successfully",
                    content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        Payment payment = paymentService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(payment);
    }
}
