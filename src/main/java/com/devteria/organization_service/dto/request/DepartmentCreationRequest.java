package com.devteria.organization_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentCreationRequest {
    private String name;                // Tên phòng ban
    private String description;         // Mô tả
    private String departmentCode;      // Mã phòng ban nội bộ
    private String headOfDepartment;    // Người đứng đầu phòng ban
    private Integer staffCount;         // Số lượng nhân viên (có thể 0 khi mới tạo)
    private Boolean active;             // Trạng thái hoạt động
    private String companyId;           // ID công ty mà phòng ban thuộc về
}
