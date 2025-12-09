package com.alpha.ZipRide.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomermobileno(long customermobileno);
    
    void deleteByCustomermobileno(long customermobileno);

}
