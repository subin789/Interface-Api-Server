package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {this.boardService = boardService;}


    @GetMapping("/getAllBoards")
    public ResponseEntity<List<BoardDto>> getAllBoards() { return ResponseEntity.ok(boardService.getAllBoards()); }

}
