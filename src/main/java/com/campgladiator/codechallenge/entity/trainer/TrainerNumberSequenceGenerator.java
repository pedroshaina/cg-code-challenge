package com.campgladiator.codechallenge.entity.trainer;

import com.campgladiator.codechallenge.exception.TrainerNumberGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class TrainerNumberSequenceGenerator {
    private static final String SEQUENCE_PATTERN = "TRAINER-%06d";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainerNumberSequenceGenerator(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String generate() {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet("SELECT nextval('trainer_number_seq')");
        if (!rs.next()) {
            throw new TrainerNumberGenerationException("No value returned from trainer number sequence");
        }
        final Long nextSeq = rs.getLong(1);
        return String.format(SEQUENCE_PATTERN, nextSeq);
    }
}
