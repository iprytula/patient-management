package com.pm.billingservice.repository;

import com.pm.billingservice.model.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingAccount, String> {
}
