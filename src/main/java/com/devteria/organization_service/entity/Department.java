package com.devteria.organization_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /** Thời gian tạo phòng ban */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    /** Thời gian cập nhật gần nhất */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    /** Tên phòng ban */
    @Column(nullable = false, length = 150)
    private String name;

    /** Mô tả chi tiết phòng ban */
    @Column(length = 500)
    private String description;

    /** Trạng thái hoạt động (true = hoạt động, false = ngưng) */
    @Column(nullable = false)
    private Boolean active;

    /** Mã định danh phòng ban nội bộ (code riêng của phòng ban) */
    @Column(length = 50, unique = true)
    private String departmentCode;

    /** Người đứng đầu phòng ban (có thể là tên hoặc id nhân viên sau này) */
    @Column(length = 150)
    private String headOfDepartment;

    /** Số lượng nhân viên trong phòng ban */
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer staffCount;

    /**
     * Quan hệ: Nhiều phòng ban thuộc về một công ty
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
