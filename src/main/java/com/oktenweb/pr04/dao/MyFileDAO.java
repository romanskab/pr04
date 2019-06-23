package com.oktenweb.pr04.dao;

import com.oktenweb.pr04.entity.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyFileDAO extends JpaRepository<MyFile, Integer> {
}
