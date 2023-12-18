package com.dagboken.dagboken;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DiaryRepository extends CrudRepository<Diary, Integer>{

List<Diary> findByDateBetween(LocalDate startDate, LocalDate endDate);

@Query
("SELECT d FROM Diary d WHERE DATE(d.date) = CURRENT_DATE")
List<Diary> findMeToday();


}