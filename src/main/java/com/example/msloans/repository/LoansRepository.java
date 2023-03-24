package com.example.msloans.repository;

import com.example.msloans.model.Loans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {
    @Query(value = "SELECT l from Loans l where l.customerId = :customerId")
    List<Loans> findByCustomerIdOrderByCreateDt(int customerId);
}
