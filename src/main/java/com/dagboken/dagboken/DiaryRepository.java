package com.dagboken.dagboken;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface DiaryRepository extends CrudRepository<Diary, Integer>{


@Query
("SELECT d FROM Diary d WHERE DATE(d.date) = CURRENT_DATE")
List<Diary> findMeToday();

@Query
("SELECT d FROM Diary d WHERE DATE(d.date) BETWEEN :startDate AND :endDate")
List<Diary> findDiaryBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}