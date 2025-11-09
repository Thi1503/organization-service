package com.devteria.organization_service.mapper;

import com.devteria.organization_service.dto.request.CompanyCreationRequest;
import com.devteria.organization_service.dto.response.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import com.devteria.organization_service.entity.Company;


@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toCompany(CompanyCreationRequest request);

    CompanyResponse toCompanyResponse(Company company);

    void updateCompany(@MappingTarget Company company, CompanyCreationRequest request);
}
