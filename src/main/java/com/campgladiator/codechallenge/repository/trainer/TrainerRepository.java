package com.campgladiator.codechallenge.repository.trainer;

import com.campgladiator.codechallenge.entity.trainer.Trainer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findOneByTrainerNumber(final String trainerNumber);

}
