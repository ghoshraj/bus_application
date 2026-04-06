package com.busapp.vehicle.service.impl;

import com.busapp.vehicle.entity.Seats;
import com.busapp.vehicle.entity.Vehicle;
import com.busapp.vehicle.enums.BookingStatus;
import com.busapp.vehicle.enums.BusType;
import com.busapp.vehicle.service.SeatService;
import com.busapp.vehicle.service.SeatStrategy;
import com.busapp.vehicle.service.persistence.SeatPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatPersistence seatPersistence;
    private final Map<String, SeatStrategy> seatStrategies;

    @Override
    public List<Seats> getSeatsByBusId(Integer busId) {
        return seatPersistence.getSeatsByVehicleId(busId);
    }

    @Override
    public List<Seats> createSeatsForVehicle(Vehicle vehicle, int totalSeats) {
        log.debug("Starting seat creation for vehicleId={}, totalSeats={}", vehicle.getId(), totalSeats);
        SeatStrategy strategy = seatStrategies.get(vehicle.getBusType().name());
        List<Seats> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            Seats seat = new Seats();
            seat.setVehicle(vehicle);
            seat.setSeatNumber(generateSeatNumber(vehicle.getBusType(), i));
            seat.setBookingStatus(BookingStatus.AVAILABLE);
            strategy.applySeatDetails(seat, i, totalSeats);
            seats.add(seat);
        }
        log.debug("Seat creation completed for vehicleId={}, totalCreated={}", vehicle.getId(), seats.size());
        return seats;
    }

    private String generateSeatNumber(BusType busType, int index) {

        return switch (busType) {
            case SEATER -> "A" + index;
            case SLEEPER -> {
                int sleeperIndex = (index + 1) / 2;
                yield (index % 2 == 0 ? "U" : "L") + sleeperIndex;
            }
            case MIXED -> "M" + index;
            default -> String.valueOf(index);
        };
    }
}
