package org.example.repo;

import org.example.domain.User;

import java.util.Optional;

public interface IUserRepo extends IRepository<Long, User> {
    Optional<User> getByUsername(String username);
}
