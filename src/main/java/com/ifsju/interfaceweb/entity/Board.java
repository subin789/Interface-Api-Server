package com.ifsju.interfaceweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @Column
    private String created_date;

    @Column
    private String modified_date;

    @Builder
    public Board(String title, String content, User writer, String created_date, String modified_date) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.created_date = created_date;
        this.modified_date = modified_date;
    }
}
