package com.hriday.blogapis.repositories;

import com.hriday.blogapis.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
