package org.milianz.imomarketbackend.Domain.Repsotories;

import org.milianz.imomarketbackend.Domain.Entities.User;

import java.util.Optional;
import java.util.UUID;

public interface iUserRepository extends iGenericRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
