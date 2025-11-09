package com.devteria.organization_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    /** Tổng số nhân viên thuộc công ty */
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer staffAmount;

    /** Quyền mặc định được gán cho nhân viên khi được thêm mới */
    @Column(length = 100)
    private String defaultRoleForStaff;

    /** Công ty có đang sử dụng module icare hay không */
    @Column(nullable = false)
    private Boolean isUseIcare;

    /** Trạng thái hoạt động */
    @Column(nullable = false)
    private Boolean active;

    /** Đã hoàn thành bước khởi tạo ban đầu chưa */
    @Column(nullable = false)
    private Boolean checkInit;

    /** Tên công ty */
    @Column(nullable = false)
    private String name;

    /** Email liên hệ */
    @Column(length = 150)
    private String email;

    /** Số điện thoại liên hệ */
    @Column(length = 50)
    private String phoneNumber;

    /** Mã số thuế doanh nghiệp */
    @Column(length = 50)
    private String taxCode;

    /** Người đại diện pháp lý của công ty */
    @Column(length = 150)
    private String representative;

    /** Địa chỉ đăng ký kinh doanh */
    @Column(length = 255)
    private String registedAddress;

    /** Địa chỉ thực tế (trụ sở chính) */
    @Column(length = 255)
    private String address;

    /** Cho phép cập nhật quan hệ công ty - đơn vị con */
    @Column(nullable = false)
    private Boolean updateRelationship;

    /** Số lượng hợp đồng còn lại mà công ty được phép tạo */
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer availableContract;

    /** Mã định danh nội bộ của công ty */
    @Column(length = 50, unique = true)
    private String companyCode;

    /**
     * Trường dữ liệu động cho phép lưu các thông tin mở rộng (JSON)
     * Ví dụ: {"taxOffice":"Chi cục Thuế Hà Nội", "industry":"Phần mềm"}
     */
    @Column(columnDefinition = "JSON")
    private String extraInfo;
}
