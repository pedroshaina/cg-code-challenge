package com.campgladiator.codechallenge.api.trainer;

import com.campgladiator.codechallenge.api.trainer.request.NewTrainerRequest;
import com.campgladiator.codechallenge.api.trainer.response.TrainerResponse;
import com.campgladiator.codechallenge.entity.trainer.Trainer;
import com.campgladiator.codechallenge.entity.trainer.TrainerNumberSequenceGenerator;
import com.campgladiator.codechallenge.exception.NotFoundException;
import com.campgladiator.codechallenge.repository.trainer.TrainerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerRestController implements TrainerApi {

    private final TrainerRepository trainerRepository;
    private final TrainerNumberSequenceGenerator trainerNumberSequenceGenerator;

    @Autowired
    public TrainerRestController(
        final TrainerRepository trainerRepository,
        final TrainerNumberSequenceGenerator trainerNumberSequenceGenerator) {

        this.trainerRepository = trainerRepository;
        this.trainerNumberSequenceGenerator = trainerNumberSequenceGenerator;
    }

    @Override
    public TrainerResponse createNewTrainer(final NewTrainerRequest requestPayload) {
        final Trainer entity = Trainer.fromJSON(
            requestPayload,
            trainerNumberSequenceGenerator.generate());

        final Trainer saved = trainerRepository.save(entity);

        return saved.toJSON();
    }

    @Override
    public TrainerResponse getTrainerDetails(final String trainerNumber) {
        final Optional<Trainer> trainerOptional = trainerRepository.findOneByTrainerNumber(trainerNumber);

        if (trainerOptional.isEmpty()) {
            throw new NotFoundException(String.format("No trainer with number %s was found", trainerNumber));
        }

        return trainerOptional.get().toJSON();
    }
}
