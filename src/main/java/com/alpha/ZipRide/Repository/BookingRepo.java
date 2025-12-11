package com.alpha.ZipRide.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

}

