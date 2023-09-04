package com.ifsju.interfaceweb.dto;

import com.ifsju.interfaceweb.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getWriter().getId();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }

    @Builder
    public BoardDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
