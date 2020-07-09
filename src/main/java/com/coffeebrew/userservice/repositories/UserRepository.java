package com.coffeebrew.userservice.repositories;

import com.coffeebrew.userservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
