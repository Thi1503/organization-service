package com.devteria.organization_service.repository;

import com.devteria.organization_service.entity.Company;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
    boolean existsByName(String name);
}
