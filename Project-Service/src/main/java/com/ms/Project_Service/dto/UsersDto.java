package com.ms.Project_Service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UsersDto {

    private Long userId;

    private String UserName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // pour masque PW lors du la serialisation
    private String password;

    private String roleName;

}
