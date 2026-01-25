package com.busapp.booking.service.impl;

import com.busapp.booking.entity.Booking;
import com.busapp.booking.enums.Status;
import com.busapp.booking.exception.BookingAlreadyCancelled;
import com.busapp.booking.exception.BookingNotFound;
import com.busapp.booking.exception.GlobalExceptionEnums;
import com.busapp.booking.model.BookingRequest;
import com.busapp.booking.service.BookingService;
import com.busapp.booking.service.persistence.BookingPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingPersistence bookingPersistence;

    @Override
    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setTravelerId(bookingRequest.getTravelerId());
        booking.setScheduleId(bookingRequest.getScheduleId());
        booking.setTotalAmount(bookingRequest.getTotalAmount());
        booking.setPnr(generatePNR());
        booking.setStatus(Status.CONFIRMED);
        booking.setCreatedAt(Instant.now());
        booking.setCreatedBy("");
        
        return bookingPersistence.saveBooking(booking);
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingPersistence.getBookingById(id)
                .orElseThrow(() -> new BookingNotFound(GlobalExceptionEnums.BOOKING_NOT_FOUND, id));
    }

    @Override
    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookingPersistence.getBookingsByTravelerId(userId);
    }

    @Override
    @Transactional
    public Booking cancelBooking(Integer id) {
        Booking booking = getBookingById(id);
        
        if (booking.getStatus() == Status.CANCELLED) {
            throw new BookingAlreadyCancelled(GlobalExceptionEnums.BOOKING_ALREADY_CANCELLED);
        }
        
        booking.setStatus(Status.CANCELLED);
        booking.setUpdatedAt(Instant.now());
        booking.setUpdatedBy("");
        
        return bookingPersistence.saveBooking(booking);
    }
    
    private String generatePNR() {
        return "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
