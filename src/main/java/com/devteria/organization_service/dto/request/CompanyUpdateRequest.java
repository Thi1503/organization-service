package com.devteria.organization_service.dto.request;

import lombok.Data;

@Data
public class CompanyUpdateRequest {

    private String name;                // Tên công ty
    private String email;               // Email liên hệ
    private String phoneNumber;         // SĐT
    private String taxCode;             // Mã số thuế
    private String representative;      // Người đại diện
    private String registedAddress;     // Địa chỉ đăng ký KD
    private String address;             // Địa chỉ trụ sở chính
    private Boolean updateRelationship; // Có cho phép cập nhật đơn vị con
    private Boolean active;             // Trạng thái hoạt động
    private String extraInfo;           // JSON mở rộng (chuỗi)
}
