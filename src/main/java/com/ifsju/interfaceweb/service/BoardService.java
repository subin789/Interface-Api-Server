package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.Board;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.BoardRepository;
import com.ifsju.interfaceweb.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(BoardDto boardDto) throws Exception {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter()).build();

        boardRepository.save(board);
    }

    @Transactional
    public List<BoardDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList) {
            boardDtoList.add(new BoardDto(board));
        }
        return boardDtoList;
    }
}
