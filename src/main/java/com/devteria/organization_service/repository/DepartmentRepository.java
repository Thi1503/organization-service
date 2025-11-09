package com.devteria.organization_service.repository;

import com.devteria.organization_service.entity.Company;
import com.devteria.organization_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByName(String name);

    List<Department> findByCompany(Company company);
}
