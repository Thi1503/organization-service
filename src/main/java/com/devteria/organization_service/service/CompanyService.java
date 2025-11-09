package com.devteria.organization_service.service;

import com.devteria.organization_service.dto.request.CompanyCreationRequest;
import com.devteria.organization_service.dto.response.CompanyResponse;
import com.devteria.organization_service.entity.Company;
import com.devteria.organization_service.exception.AppException;
import com.devteria.organization_service.exception.ErrorCode;
import com.devteria.organization_service.mapper.CompanyMapper;
import com.devteria.organization_service.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CompanyService {
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;

    public CompanyResponse createCompany(CompanyCreationRequest request) {
        if (companyRepository.existsByName(request.getName())) throw new AppException(ErrorCode.COMPANY_EXISTED);

        Company company = companyMapper.toCompany(request);

        return companyMapper.toCompanyResponse(companyRepository.save(company));
    }
}
