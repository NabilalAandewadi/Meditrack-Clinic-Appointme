package com.airtribe.meditrack.repository;

import com.airtribe.meditrack.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, String> {
}