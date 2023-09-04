package com.ifsju.interfaceweb.dto;

import com.ifsju.interfaceweb.entity.User;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;

    @Builder
    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

}
