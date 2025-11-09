package com.devteria.organization_service.controller;

import com.devteria.organization_service.dto.request.DepartmentCreationRequest;
import com.devteria.organization_service.dto.request.DepartmentUpdateRequest;
import com.devteria.organization_service.dto.response.ApiResponse;
import com.devteria.organization_service.dto.response.DepartmentResponse;
import com.devteria.organization_service.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "department")
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 🟢 Tạo mới phòng ban
     */
    @PostMapping
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentCreationRequest request) {
        log.info("POST /api/department - createDepartment");
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.createDepartment(request))
                .build();
    }

    /**
     * 📋 Lấy danh sách tất cả phòng ban
     */
    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getAllDepartments() {
        log.info("GET /api/department - getAllDepartments");
        return ApiResponse.<List<DepartmentResponse>>builder()
                .result(departmentService.getDepartments())
                .build();
    }

    /**
     * 🔍 Lấy chi tiết phòng ban theo ID
     */
    @GetMapping("/{id}")
    public ApiResponse<DepartmentResponse> getDepartmentById(@PathVariable String id) {
        log.info("GET /api/department/{} - getDepartmentById", id);
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.getDepartmentById(id))
                .build();
    }

    /**
     * 🏢 Lấy danh sách phòng ban theo công ty
     */
    @GetMapping("/by-company/{companyId}")
    public ApiResponse<List<DepartmentResponse>> getDepartmentsByCompany(@PathVariable String companyId) {
        log.info("GET /api/department/by-company/{} - getDepartmentsByCompany", companyId);
        return ApiResponse.<List<DepartmentResponse>>builder()
                .result(departmentService.getDepartmentsByCompanyId(companyId))
                .build();
    }

    /**
     * ✏️ Cập nhật thông tin phòng ban
     */
    @PutMapping("/{id}")
    public ApiResponse<DepartmentResponse> updateDepartment(
            @PathVariable String id,
            @RequestBody DepartmentUpdateRequest request
    ) {
        log.info("PUT /api/department/{} - updateDepartment", id);
        return ApiResponse.<DepartmentResponse>builder()
                .result(departmentService.updateDepartment(id, request))
                .build();
    }

    /**
     * 🗑️ Xóa phòng ban theo ID
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDepartment(@PathVariable String id) {
        log.info("DELETE /api/department/{} - deleteDepartment", id);
        departmentService.deleteDepartmentById(id);
        return ApiResponse.<Void>builder()
                .message("Xóa phòng ban thành công")
                .build();
    }
}
