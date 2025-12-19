package com.alpha.ZipRide.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.alpha.ZipRide.Entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    // Bookings of a specific customer
    List<Booking> findByCustomer_Customermobileno(long customermobileno);

    // Active booking of a customer
    @Query("""
        SELECT b FROM Booking b
        WHERE b.customer.customermobileno = :mobileNo
        AND LOWER(b.bookingstatus) = 'booked'
    """)
    Booking findActiveBookingByCustomerId(@Param("mobileNo") Long mobileNo);

    // All bookings of a vehicle
    List<Booking> findByVehicle_Vid(int vid);

    // Booking by vehicle and date
    Optional<Booking> findByVehicle_VidAndBookingdate(int vid, LocalDate bookingdate);

    // Completed bookings by driver mobile
    @Query("""
        SELECT b FROM Booking b
        WHERE b.vehicle.driver.drivermobileno = :mobileNo
        AND LOWER(b.bookingstatus) = 'completed'
    """)
    List<Booking> findCompletedBookingsByDriverMobile(@Param("mobileNo") long mobileNo);

    // Pending bookings by driver mobile
    @Query("""
        SELECT b FROM Booking b
        WHERE b.vehicle.driver.drivermobileno = :mobileNo
        AND LOWER(b.bookingstatus) = 'booked'
    """)
    List<Booking> findPendingBookingsByDriverMobile(@Param("mobileNo") long mobileNo);
}
