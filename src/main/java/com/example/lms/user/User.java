package com.example.lms.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class User {
  @Id
  private String id;

  private String name;

  private String password;

  @ManyToOne
  @JoinColumn(name = "permission_id")
  private Permission permission;
}
