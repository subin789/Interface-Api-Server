package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.Board;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.BoardRepository;
import com.ifsju.interfaceweb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardService boardService;

    @BeforeEach
    void cleanUp(){
        boardRepository.deleteAll();
    }

    @Test
    void save() throws Exception {
        //given
        String title = "test title";
        String content = "test content";
        User writer = new User(1L, "eee@naver.com", "1234");
        userRepository.save(writer);
        String created_date = "2022-22-22";
        String modified_date = "2022-23-23";
        Board board = new Board(title, content, writer, created_date, modified_date);

        BoardDto boardDto = new BoardDto(board);

        //when
        boardService.save(boardDto);
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board1 = boardList.get(0);

        Assertions.assertThat(board1.getTitle()).isEqualTo(title);
        Assertions.assertThat(board1.getContent()).isEqualTo(content);
    }
}