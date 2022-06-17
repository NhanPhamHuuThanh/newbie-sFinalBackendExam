package com.axonactive.backEndFinalExam.repository;

import com.axonactive.backEndFinalExam.entity.KeyCapSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KeycapSetRepo extends JpaRepository<KeyCapSet, Integer> {
    @Query(" from KeyCapSet where name = :name and status = 'INSTOCK' ")
    List<KeyCapSet> getAllInStock(@Param("name") String name);

    @Query(" from KeyCapSet where name = :name")
    List<KeyCapSet> getSoldUnits(@Param("name") String name);

    @Query("from  KeyCapSet k where k.name = :name " +
            "and k.importedDate = :date ")
    List<KeyCapSet> getAllKeyCapSetFromADayWithGivenName(@Param("name")String name,
                                                         @Param("date")LocalDate date
                                            );
  @Query("from  KeyCapSet k where " +
            "k.importedDate = :date ")
    List<KeyCapSet> getAllKeyCapSetFromADay(@Param("date")LocalDate date);

}
