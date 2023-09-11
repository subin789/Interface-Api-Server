package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.service.BoardService;
import com.ifsju.interfaceweb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    // 글작성
    @PostMapping("/create")
    public ResponseEntity<BoardDto> create(@RequestBody Map<String, String> param) throws Exception {
        String title = param.get("title");
        String content = param.get("content");
        Long user_id = Long.parseLong(param.get("user_id"));

        BoardDto boardDto = new BoardDto(title, content, user_id);
        boardService.save(boardDto);

        return ResponseEntity.ok(boardDto);
    }

    // 전체 게시물 불러오기
    @GetMapping("/getAllBoards")
    public ResponseEntity<List<BoardDto>> getAllBoards() { return ResponseEntity.ok(boardService.getAllBoards()); }

    // 작성자 id로 게시물 불러오기
    @GetMapping("/findByUserId")
    public ResponseEntity<List<BoardDto>> findByUserId(@RequestParam("userId")Long id) throws Exception {
        return ResponseEntity.ok(boardService.findByUserId(id));
    }

    // 글 id로 게시물 불러오기
    @GetMapping("/findById")
    public ResponseEntity<BoardDto> findById(@RequestParam("id")Long id) throws Exception {
        return ResponseEntity.ok(boardService.findById(id));
    }

    // 글삭제
    @DeleteMapping("/delete")
    public ResponseEntity<BoardDto> delete(@RequestParam("id") Long id) throws Exception {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //글수정
    @PutMapping("/update")
    public ResponseEntity<BoardDto> update(@PathVariable("id") Long id, @RequestBody BoardDto updatedBoardDto) throws Exception {
        BoardDto updatedBoard = boardService.update(id, updatedBoardDto);

        return ResponseEntity.ok(updatedBoard);
    }

}
