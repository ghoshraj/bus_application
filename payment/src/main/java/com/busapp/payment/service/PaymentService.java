package com.busapp.payment.service;

import com.busapp.payment.entity.Payment;
import com.busapp.payment.model.PaymentInitiateRequest;
import com.busapp.payment.model.PaymentRequest;
import com.busapp.payment.model.PaymentVerifyRequest;
import com.busapp.payment.model.RefundRequest;

import java.util.List;

public interface PaymentService {

    Payment processPayment(PaymentRequest paymentRequest);
    
    Payment initiatePayment(PaymentInitiateRequest paymentRequest);
    
    Payment verifyPayment(PaymentVerifyRequest verifyRequest);
    
    Payment refundPayment(RefundRequest refundRequest);
    
    Payment getPaymentById(Integer id);
    
    List<Payment> getPaymentsByBookingId(Integer bookingId);
    
    Payment getPaymentByTransactionId(String transactionId);
}
