package com.devteria.organization_service.service;

import com.devteria.organization_service.dto.request.DepartmentCreationRequest;
import com.devteria.organization_service.dto.request.DepartmentUpdateRequest;
import com.devteria.organization_service.dto.response.DepartmentResponse;
import com.devteria.organization_service.entity.Company;
import com.devteria.organization_service.entity.Department;
import com.devteria.organization_service.exception.AppException;
import com.devteria.organization_service.exception.ErrorCode;
import com.devteria.organization_service.mapper.DepartmentMapper;
import com.devteria.organization_service.repository.CompanyRepository;
import com.devteria.organization_service.repository.DepartmentRepository;
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
public class DepartmentService {

    DepartmentRepository departmentRepository;
    CompanyRepository companyRepository;
    DepartmentMapper departmentMapper;

    /**
     * Tạo phòng ban mới
     */
    public DepartmentResponse createDepartment(DepartmentCreationRequest request) {
        log.info("In method createDepartment for companyId={}", request.getCompanyId());

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_FOUND));

        // Có thể kiểm tra trùng mã phòng ban nếu muốn
        if (departmentRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DEPARTMENT_EXISTED);
        }

        Department department = departmentMapper.toDepartment(request);
        department.setCompany(company);

        Department saved = departmentRepository.save(department);
        return departmentMapper.toDepartmentResponse(saved);
    }

    /**
     * Lấy danh sách tất cả phòng ban
     */
    public List<DepartmentResponse> getDepartments() {
        log.info("In method getDepartments");
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
    }

    /**
     * Lấy danh sách phòng ban theo ID công ty
     */
    public List<DepartmentResponse> getDepartmentsByCompanyId(String companyId) {
        log.info("In method getDepartmentsByCompanyId with companyId={}", companyId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_FOUND));

        return departmentRepository.findByCompany(company)
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
    }

    /**
     * Lấy chi tiết phòng ban theo ID
     */
    public DepartmentResponse getDepartmentById(String id) {
        log.info("In method getDepartmentById with id={}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        return departmentMapper.toDepartmentResponse(department);
    }

    /**
     * Cập nhật thông tin phòng ban
     */
    public DepartmentResponse updateDepartment(String id, DepartmentUpdateRequest request) {
        log.info("In method updateDepartment with id={}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

        departmentMapper.updateDepartment(department, request);

        Department updated = departmentRepository.save(department);
        return departmentMapper.toDepartmentResponse(updated);
    }

    /**
     * Xóa phòng ban theo ID
     */
    public void deleteDepartmentById(String id) {
        log.info("In method deleteDepartmentById with id={}", id);

        if (!departmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }

        departmentRepository.deleteById(id);
    }
}
