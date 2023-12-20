package com.dagboken.dagboken;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;


public interface DiaryRepository extends JpaRepository<Diary, Integer>{


@Query
("SELECT d FROM Diary d WHERE DATE(d.date) = CURRENT_DATE")
List<Diary> findMeToday();

@Query
("SELECT d FROM Diary d WHERE DATE(d.date) BETWEEN :startDate AND :endDate ORDER BY d.date")
List<Diary> findDiaryBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}