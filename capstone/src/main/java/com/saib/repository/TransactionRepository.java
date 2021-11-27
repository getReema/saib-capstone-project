package com.saib.repository;


import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saib.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction,Long> {

}
