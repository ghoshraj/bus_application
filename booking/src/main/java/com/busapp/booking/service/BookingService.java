package com.busapp.booking.service;

import com.busapp.booking.entity.Booking;
import com.busapp.booking.model.BookingRequest;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest);
    
    Booking getBookingById(Integer id);
    
    List<Booking> getBookingsByUserId(Integer userId);
    
    Booking cancelBooking(Integer id);
}
