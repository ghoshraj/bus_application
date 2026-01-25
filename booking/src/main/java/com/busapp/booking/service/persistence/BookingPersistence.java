package com.busapp.booking.service.persistence;

import com.busapp.booking.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingPersistence {

    Booking saveBooking(Booking booking);
    
    Optional<Booking> getBookingById(Integer id);
    
    List<Booking> getBookingsByTravelerId(Integer travelerId);
}
