package com.photo.repository;


import com.photo.entity.PrintOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrintOrderRepository extends JpaRepository<PrintOrder, Long> {
    Optional<PrintOrder> findByOrderCode(String orderCode);
}
