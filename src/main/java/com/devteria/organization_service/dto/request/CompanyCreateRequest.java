package com.devteria.organization_service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyCreateRequest {

    /** Tên công ty */
    private String name;

    /** Email liên hệ */
    private String email;

    /** Số điện thoại liên hệ */
    private String phoneNumber;

    /** Mã số thuế doanh nghiệp */
    private String taxCode;

    /** Người đại diện pháp lý của công ty */
    private String representative;

    /** Địa chỉ đăng ký kinh doanh */
    private String registedAddress;

    /** Địa chỉ thực tế (trụ sở chính) */
    private String address;

    /** Quyền mặc định được gán cho nhân viên khi được thêm mới */
    private String defaultRoleForStaff;

    /** Công ty có đang sử dụng module icare hay không */
    private Boolean isUseIcare;

    /** Trạng thái hoạt động */
    private Boolean active;

    /** Đã hoàn thành bước khởi tạo ban đầu chưa */
    private Boolean checkInit;

    /** Cho phép cập nhật quan hệ công ty - đơn vị con */
    private Boolean updateRelationship;

    /** Số lượng hợp đồng còn lại mà công ty được phép tạo */
    private Integer availableContract;

    /** Mã định danh nội bộ của công ty */
    private String companyCode;

    /** Tổng số nhân viên thuộc công ty */
    private Integer staffAmount;

    /** Dữ liệu mở rộng (JSON) dạng chuỗi */
    private String extraInfo;
}
