package com.ifsju.interfaceweb.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;

    @Builder
    public UserDTO(Long id, String email){
        this.id = id;
        this.email = email;
    }

}
