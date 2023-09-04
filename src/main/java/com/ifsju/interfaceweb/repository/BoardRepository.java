package com.ifsju.interfaceweb.repository;

import com.ifsju.interfaceweb.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
