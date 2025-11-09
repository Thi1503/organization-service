package com.devteria.organization_service.mapper;

import com.devteria.organization_service.dto.request.DepartmentCreationRequest;
import com.devteria.organization_service.dto.request.DepartmentUpdateRequest;
import com.devteria.organization_service.dto.response.DepartmentResponse;
import com.devteria.organization_service.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "company.id", source = "companyId")
    Department toDepartment(DepartmentCreationRequest request);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "companyName", source = "company.name")
    DepartmentResponse toDepartmentResponse(Department department);

    void updateDepartment(@MappingTarget Department department, DepartmentUpdateRequest request);
}
