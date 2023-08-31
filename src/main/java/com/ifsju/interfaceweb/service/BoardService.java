package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.entity.Board;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.BoardRepository;
import com.ifsju.interfaceweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void save(BoardDto boardDto) throws Exception {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter()).build();

        boardRepository.save(board);
    }
}
