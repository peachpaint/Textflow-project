package org.example.textflowproject.domain.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(nullable = false, unique = true, length = 255)
  private String username;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(name="display_name", length=100)
  private String displayName;

  @Column(columnDefinition = "TEXT")
  private String bio;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private UserRole role;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // 비즈니스 메서드
  
  /**
   * 사용자 프로필 정보 수정
   */
  public void updateProfile(String displayName, String bio) {
    if (displayName != null && !displayName.isBlank()) {
      this.displayName = displayName;
    }
    if (bio != null) {
      this.bio = bio;
    }
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 비밀번호 변경
   */
  public void changePassword(String newPassword) {
    if (newPassword == null || newPassword.isBlank()) {
      throw new IllegalArgumentException("비밀번호는 비어있을 수 없습니다.");
    }
    this.password = newPassword;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 사용자 역할 변경
   */
  public void changeRole(UserRole newRole) {
    if (newRole == null) {
      throw new IllegalArgumentException("역할은 null일 수 없습니다.");
    }
    this.role = newRole;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 이메일 변경
   */
  public void changeEmail(String newEmail) {
    if (newEmail == null || newEmail.isBlank()) {
      throw new IllegalArgumentException("이메일은 비어있을 수 없습니다.");
    }
    this.email = newEmail;
    this.updatedAt = LocalDateTime.now();
  }

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
