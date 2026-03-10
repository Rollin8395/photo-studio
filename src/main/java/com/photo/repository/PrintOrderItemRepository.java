package com.photo.repository;


import com.photo.entity.PrintOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintOrderItemRepository extends JpaRepository<PrintOrderItem, Long> {
}
