package com.alpha.ZipRide.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomermobileno(long customermobileno);
    
    void deleteByCustomermobileno(long customermobileno);

    @Query("SELECT b FROM Booking b WHERE b.customer.customermobileno = :mobileno AND b.bookingstatus = 'completed'")
	List<Booking> findCompletedBookingsByMobile(@Param("mobileno") long mobileno);

    @Query("SELECT b FROM Booking b WHERE b.customer.customermobileno = :mobileno AND b.bookingstatus = 'booked'")
	List<Booking> findActiveBookingsByMobile(@Param("mobileno") long mobileno);


}

