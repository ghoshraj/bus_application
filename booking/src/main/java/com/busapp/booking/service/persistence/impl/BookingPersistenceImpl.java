package com.busapp.booking.service.persistence.impl;

import com.busapp.booking.entity.Booking;
import com.busapp.booking.repository.BookingRepository;
import com.busapp.booking.service.persistence.BookingPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingPersistenceImpl implements BookingPersistence {

    private final BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getBookingsByTravelerId(Integer travelerId) {
        return bookingRepository.findByTravelerId(travelerId);
    }
}
