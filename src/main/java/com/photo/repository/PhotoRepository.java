package com.photo.repository;


import com.photo.entity.PhotoAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoAsset, Long> {
}