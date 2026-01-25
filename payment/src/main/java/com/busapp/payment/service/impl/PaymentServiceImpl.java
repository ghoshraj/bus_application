package com.busapp.payment.service.impl;

import com.busapp.payment.entity.Payment;
import com.busapp.payment.entity.Refund;
import com.busapp.payment.enums.Status;
import com.busapp.payment.exception.GlobalExceptionEnums;
import com.busapp.payment.exception.PaymentAlreadyExists;
import com.busapp.payment.exception.PaymentNotPending;
import com.busapp.payment.exception.PaymentNotSuccessful;
import com.busapp.payment.exception.PaymentNotFound;
import com.busapp.payment.model.PaymentInitiateRequest;
import com.busapp.payment.model.PaymentRequest;
import com.busapp.payment.model.PaymentVerifyRequest;
import com.busapp.payment.model.RefundRequest;
import com.busapp.payment.repository.RefundRepository;
import com.busapp.payment.service.PaymentService;
import com.busapp.payment.service.persistence.PaymentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentPersistence paymentPersistence;
    private final RefundRepository refundRepository;

    @Override
    @Transactional
    public Payment processPayment(PaymentRequest paymentRequest) {
        // Check if payment already exists for this booking
        List<Payment> existingPayments = paymentPersistence.getPaymentsByBookingId(paymentRequest.getBookingId());
        boolean hasSuccessfulPayment = existingPayments.stream()
                .anyMatch(p -> p.getStatus() == Status.SUCCESS);
        
        if (hasSuccessfulPayment) {
            throw new PaymentAlreadyExists(GlobalExceptionEnums.PAYMENT_ALREADY_EXISTS, 
                    paymentRequest.getBookingId());
        }
        
        // Create new payment
        Payment payment = new Payment();
        payment.setBookingId(paymentRequest.getBookingId());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setTransactionId(generateTransactionId());
        payment.setStatus(Status.PENDING);
        payment.setCreatedAt(Instant.now());
        payment.setCreatedBy("");
        
        // Simulate payment processing (in real scenario, this would call payment gateway)
        // For now, we'll set it to SUCCESS
        payment.setStatus(Status.SUCCESS);
        
        return paymentPersistence.savePayment(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentPersistence.getPaymentById(id)
                .orElseThrow(() -> new PaymentNotFound(GlobalExceptionEnums.PAYMENT_NOT_FOUND, id));
    }

    @Override
    public List<Payment> getPaymentsByBookingId(Integer bookingId) {
        return paymentPersistence.getPaymentsByBookingId(bookingId);
    }

    @Override
    public Payment getPaymentByTransactionId(String transactionId) {
        return paymentPersistence.getPaymentByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFound(GlobalExceptionEnums.PAYMENT_NOT_FOUND_BY_TRANSACTION_ID, transactionId));
    }

    @Override
    @Transactional
    public Payment initiatePayment(PaymentInitiateRequest paymentRequest) {
        // Check if payment already exists for this booking
        List<Payment> existingPayments = paymentPersistence.getPaymentsByBookingId(paymentRequest.getBookingId());
        boolean hasSuccessfulPayment = existingPayments.stream()
                .anyMatch(p -> p.getStatus() == Status.SUCCESS);
        
        if (hasSuccessfulPayment) {
            throw new PaymentAlreadyExists(GlobalExceptionEnums.PAYMENT_ALREADY_EXISTS, 
                    paymentRequest.getBookingId());
        }
        
        // Create new payment with PENDING status
        Payment payment = new Payment();
        payment.setBookingId(paymentRequest.getBookingId());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setTransactionId(generateTransactionId());
        payment.setStatus(Status.PENDING);
        payment.setCreatedAt(Instant.now());
        payment.setCreatedBy("");
        
        return paymentPersistence.savePayment(payment);
    }

    @Override
    @Transactional
    public Payment verifyPayment(PaymentVerifyRequest verifyRequest) {
        Payment payment = getPaymentByTransactionId(verifyRequest.getTransactionId());
        
        if (payment.getStatus() != Status.PENDING) {
            throw new PaymentNotPending(GlobalExceptionEnums.PAYMENT_NOT_PENDING);
        }
        
        // Simulate payment verification (in real scenario, this would call payment gateway)
        payment.setStatus(Status.SUCCESS);
        payment.setUpdatedAt(Instant.now());
        payment.setUpdatedBy("");
        
        return paymentPersistence.savePayment(payment);
    }

    @Override
    @Transactional
    public Payment refundPayment(RefundRequest refundRequest) {
        Payment payment = getPaymentById(refundRequest.getPaymentId());
        
        if (payment.getStatus() != Status.SUCCESS) {
            throw new PaymentNotSuccessful(GlobalExceptionEnums.PAYMENT_NOT_SUCCESSFUL);
        }
        
        // Create refund record
        Refund refund = new Refund();
        refund.setPaymentId(payment.getId());
        refund.setAmount(payment.getAmount());
        refund.setTransactionId(generateTransactionId());
        refund.setStatus(Status.SUCCESS);
        refund.setCreatedAt(Instant.now());
        refund.setCreatedBy("");
        refundRepository.save(refund);
        
        // Update payment status to REFUNDED
        payment.setStatus(Status.REFUNDED);
        payment.setUpdatedAt(Instant.now());
        payment.setUpdatedBy("");
        
        return paymentPersistence.savePayment(payment);
    }
    
    private String generateTransactionId() {
        return "TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase().replace("-", "");
    }
}
