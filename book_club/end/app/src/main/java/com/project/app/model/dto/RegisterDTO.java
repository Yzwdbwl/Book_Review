package com.project.app.model.dto;


import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String avatar;
    private String nickname;
    private String role;
    private String parentOf;
    private String classId;
}
