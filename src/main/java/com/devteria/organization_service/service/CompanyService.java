package com.devteria.organization_service.service;

import com.devteria.organization_service.dto.request.CompanyCreationRequest;
import com.devteria.organization_service.dto.request.CompanyUpdateRequest;
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

import java.util.List;

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

    public List<CompanyResponse> getCompanies() {
        log.info("In method getCompanyList");
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toCompanyResponse)
                .toList();
    }


    public CompanyResponse getCompanyById(String id) {
        log.info("In method getCompanyById with id={}", id);

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_FOUND));

        return companyMapper.toCompanyResponse(company);
    }

    /**
     * Xóa công ty theo ID
     */
    public void deleteCompanyById(String id) {
        log.info("In method deleteCompanyById with id={}", id);

        if (!companyRepository.existsById(id)) {
            throw new AppException(ErrorCode.COMPANY_NOT_FOUND);
        }

        companyRepository.deleteById(id);
    }

    public CompanyResponse updateCompany(String id, CompanyUpdateRequest request) {
        log.info("In method updateCompany with id={}", id);

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_FOUND));

        if (request.getName() != null) company.setName(request.getName());
        if (request.getEmail() != null) company.setEmail(request.getEmail());
        if (request.getPhoneNumber() != null) company.setPhoneNumber(request.getPhoneNumber());
        if (request.getTaxCode() != null) company.setTaxCode(request.getTaxCode());
        if (request.getRepresentative() != null) company.setRepresentative(request.getRepresentative());
        if (request.getRegistedAddress() != null) company.setRegistedAddress(request.getRegistedAddress());
        if (request.getAddress() != null) company.setAddress(request.getAddress());
        if (request.getUpdateRelationship() != null) company.setUpdateRelationship(request.getUpdateRelationship());
        if (request.getActive() != null) company.setActive(request.getActive());
        if (request.getExtraInfo() != null) company.setExtraInfo(request.getExtraInfo());

        Company updated = companyRepository.save(company);
        return companyMapper.toCompanyResponse(updated);
    }


    public boolean existsById(String companyId) {
        return companyRepository.existsById(companyId);
    }
}
