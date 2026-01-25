package com.busapp.payment.service.persistence;

import com.busapp.payment.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentPersistence {

    Payment savePayment(Payment payment);
    
    Optional<Payment> getPaymentById(Integer id);
    
    List<Payment> getPaymentsByBookingId(Integer bookingId);
    
    Optional<Payment> getPaymentByTransactionId(String transactionId);
}
