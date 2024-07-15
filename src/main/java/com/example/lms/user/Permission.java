package com.example.lms.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Permission {
  @Id
  @Column(name = "permissionId")
  private Integer permission_id;

  private String name;
}
