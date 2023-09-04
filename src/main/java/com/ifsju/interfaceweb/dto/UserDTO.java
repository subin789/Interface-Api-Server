package com.ifsju.interfaceweb.dto;


import com.ifsju.interfaceweb.entity.User;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;


    public UserDTO(Long id, String email){
        this.id = id;
        this.email = email;
    }

}
