package com.ecommerce.customerservice.repositories;

import com.ecommerce.customerservice.models.UserDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<UserDetail, UUID> {

    Optional<UserDetail> findByUsername(String username);
}
