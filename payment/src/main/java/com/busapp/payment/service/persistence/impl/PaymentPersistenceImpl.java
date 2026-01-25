package com.busapp.payment.service.persistence.impl;

import com.busapp.payment.entity.Payment;
import com.busapp.payment.repository.PaymentRepository;
import com.busapp.payment.service.persistence.PaymentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentPersistenceImpl implements PaymentPersistence {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsByBookingId(Integer bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    @Override
    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }
}
