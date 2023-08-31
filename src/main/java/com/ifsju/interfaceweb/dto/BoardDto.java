package com.ifsju.interfaceweb.dto;

import com.ifsju.interfaceweb.entity.Board;
import com.ifsju.interfaceweb.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private User writer;
    private String created_date;
    private String modified_date;

    @Builder
    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.created_date = board.getCreated_date();
        this.modified_date = board.getModified_date();
    }
}
