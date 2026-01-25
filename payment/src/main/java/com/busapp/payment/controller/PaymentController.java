package com.busapp.payment.controller;

import com.busapp.payment.entity.Payment;
import com.busapp.payment.model.PaymentInitiateRequest;
import com.busapp.payment.model.PaymentRequest;
import com.busapp.payment.model.PaymentVerifyRequest;
import com.busapp.payment.model.RefundRequest;
import com.busapp.payment.service.PaymentService;
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

    @PostMapping
    public ResponseEntity<Payment> processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.processPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@Valid @RequestBody PaymentInitiateRequest paymentRequest) {
        Payment payment = paymentService.initiatePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PostMapping("/verify")
    public ResponseEntity<Payment> verifyPayment(@Valid @RequestBody PaymentVerifyRequest verifyRequest) {
        Payment payment = paymentService.verifyPayment(verifyRequest);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/refund")
    public ResponseEntity<Payment> refundPayment(@Valid @RequestBody RefundRequest refundRequest) {
        Payment payment = paymentService.refundPayment(refundRequest);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsByBookingId(@PathVariable Integer bookingId) {
        List<Payment> payments = paymentService.getPaymentsByBookingId(bookingId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        Payment payment = paymentService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(payment);
    }
}
