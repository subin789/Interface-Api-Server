package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.BoardDto;
import com.ifsju.interfaceweb.entity.Board;
import com.ifsju.interfaceweb.repository.BoardRepository;
import com.ifsju.interfaceweb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시물 저장
    @Transactional
    public void save(BoardDto boardDto) throws Exception {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(userRepository.findById(boardDto.getUserId())).build();

        boardRepository.save(board);
    }

    // 전체 게시물 불러오기
    @Transactional
    public List<BoardDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList) {
            boardDtoList.add(new BoardDto(board));
        }
        return boardDtoList;
    }

    // 작성자 id로 게시물 불러오기
    @Transactional
    public List<BoardDto> findByUserId(Long id) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();

        for(Board board : boardList) {
            if(board.getWriter().getId().equals(id)) {
                boardDtoList.add(new BoardDto(board));
            }
        }

        return boardDtoList;
    }

    // id로 게시물 불러오기
    @Transactional
    public BoardDto findById(Long id) throws EntityNotFoundException {
        BoardDto boardDto = BoardDto.builder()
                .board(boardRepository.findById(id).get()).build();

        return boardDto;
    }

    // 게시물 삭제
    @Transactional
    public void delete(Long id) throws EntityNotFoundException {
        Board board=boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물이 없습니다."));
        boardRepository.delete(board);
    }

    // 게시물 수정
    @Transactional
    public BoardDto update(Long id, BoardDto updatedBoardDto) throws EntityNotFoundException {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물이 없습니다."));

        Board updatedBoard = Board.builder()
                .id(board.getId())
                .title(updatedBoardDto.getTitle())
                .content(updatedBoardDto.getContent())
                .writer(board.getUserId())
                .build();

        updatedBoard = boardRepository.save(updatedBoard);

        return BoardDto.builder()
                .board(updatedBoard)
                .build();
    }



}
