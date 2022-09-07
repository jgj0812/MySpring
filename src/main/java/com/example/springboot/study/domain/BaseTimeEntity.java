package com.example.springboot.study.domain;

/*
 * B013 domain.BaseTimeEntity.java
 *      @MappedSuperclass
 *          JPA Entity Class들이 BaseTimeEntity를 상속하는 경우에 필드 (createDate, modifiedDate)도 컬럼으로 인식하게 하라
 *
 *      @EntityListeners(AuditingEntityListener.class)
 *          BaseTimeEntity 클래스에 Auditing 기능을 포함시키겠다.
 *
 *      @CreatedDate
 *          Entity가 생성되어 저장될 때 시간이 자동으로 저장
 *
 *      @LastModifiedDate
 *          Entity가 값을 변경할 때 시간이 자동으로 저장
 *
 *      할 일 :
 *          Posts 클래스가 BaseTimeEntity 클래스를 상속하도록 변경하게 되면
 *              createDate, modifiedDate 필드가 추가되면서
 *              모든 Posts의 시간정보(생성, 변경)를 감시한다.
 *              추상클래스이기 때문에
 * */

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * C022 날짜 표현 형식을 내가 원하는 형태로 바꿔보기
 *      이것을 위해, 밑에 코드를 변경할 껀데,
 *      기록을 위해서 주석.
 * */
/*
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
*/

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private String createdDate;     // LocalDateTime -> String

    @LastModifiedDate
    private String modifiedDate;    // LocalDateTime -> String

    // 해당 엔티티를 저장하기 이전에 먼저 실행
    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
