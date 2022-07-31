package com.example.microservice.packagingAndDelivery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservice.packagingAndDelivery.entity.PackagingAndDeliveryCosting;

@Repository
public interface PackagingAndDeliveryRepository extends JpaRepository<PackagingAndDeliveryCosting, String> {

}
