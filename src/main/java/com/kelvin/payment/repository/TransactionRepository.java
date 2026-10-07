package com.kelvin.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelvin.payment.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
