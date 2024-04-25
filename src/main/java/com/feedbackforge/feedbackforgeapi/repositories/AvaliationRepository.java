package com.feedbackforge.feedbackforgeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.Avaliation;



@Repository
public interface AvaliationRepository extends JpaRepository<Avaliation, Long>{

    @Query("FROM Avaliation a WHERE a.user.id = :userId")
    List<Avaliation> findAllByUserId(Long userId);
    


}
