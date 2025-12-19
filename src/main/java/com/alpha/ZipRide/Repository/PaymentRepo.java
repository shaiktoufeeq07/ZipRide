package com.alpha.ZipRide.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
