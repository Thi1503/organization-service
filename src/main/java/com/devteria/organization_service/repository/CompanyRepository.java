package com.devteria.organization_service.repository;

import com.devteria.organization_service.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,String> {
    boolean existsByName(String name);
}
