package com.devteria.organization_service.controller;

import com.devteria.organization_service.dto.request.CompanyCreationRequest;
import com.devteria.organization_service.dto.request.CompanyUpdateRequest;
import com.devteria.organization_service.dto.response.ApiResponse;
import com.devteria.organization_service.dto.response.CompanyResponse;
import com.devteria.organization_service.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "company")
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CompanyController {
    CompanyService companyService;

    @PostMapping
    ApiResponse<CompanyResponse> createCompany(@RequestBody  CompanyCreationRequest request) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.createCompany(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<CompanyResponse>> getCompanies() {
        return ApiResponse.<List<CompanyResponse>>builder()
                .result(companyService.getCompanies())
                .build();
    }

    @GetMapping("/{companyId}")
    ApiResponse<CompanyResponse> getCompany(@PathVariable("companyId") String userId) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.getCompanyById(userId))
                .build();
    }

    @DeleteMapping("/{companyId}")
    ApiResponse<String> deleteCompany(@PathVariable String companyId) {
        companyService.deleteCompanyById(companyId);
        return ApiResponse.<String>builder().result("Company has been deleted").build();
    }

    @PutMapping("/{companyId}")
    public ApiResponse<CompanyResponse> updateCompany(@PathVariable String companyId,
                                                         @RequestBody CompanyUpdateRequest request) {
        log.info("PUT /api/company/{} - updateCompany", companyId);
        CompanyResponse response = companyService.updateCompany(companyId, request);
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.getCompanyById(companyId))
                .build();
    }

    // Kiểm tra tồn tại
    @GetMapping("/{companyId}/exists")
    public ApiResponse<Boolean> existsCompany(@PathVariable("companyId") String companyId) {
        return ApiResponse.<Boolean>builder()
                .result(companyService.existsById(companyId))
                .build();
    }


}
