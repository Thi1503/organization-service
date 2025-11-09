package com.devteria.organization_service.mapper;

import com.devteria.organization_service.dto.request.CompanyCreationRequest;
import com.devteria.organization_service.dto.response.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import com.devteria.organization_service.entity.Company;

/**
 * Mapper tự động cho Company (MapStruct)
 * - Chuyển đổi giữa Entity ↔ DTO
 * - Tự sinh code trong target/generated-sources/annotations khi compile
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {

    /** Map từ request tạo mới sang entity */
    Company toCompany(CompanyCreationRequest request);

    /** Map từ entity sang response */
    CompanyResponse toCompanyResponse(Company company);

    /**
     * Cập nhật entity hiện có từ request (khi update)
     * Các trường null trong request sẽ được bỏ qua.
     */
    void updateCompany(@MappingTarget Company company, CompanyCreationRequest request);
}
