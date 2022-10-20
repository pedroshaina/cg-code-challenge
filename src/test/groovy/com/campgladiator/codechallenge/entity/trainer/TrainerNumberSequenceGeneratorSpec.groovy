package com.campgladiator.codechallenge.entity.trainer

import com.campgladiator.codechallenge.exception.TrainerNumberGenerationException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.rowset.SqlRowSet
import spock.lang.Specification

class TrainerNumberSequenceGeneratorSpec extends Specification {

    JdbcTemplate mockJdbcTemplate = Mock(JdbcTemplate)

    TrainerNumberSequenceGenerator trainerNumberSequenceGenerator = new TrainerNumberSequenceGenerator(mockJdbcTemplate)

    def "should throw TrainerNumberGenerationException if sequence returns no value"() {
        given:
        def mockSqlRowSet = Mock(SqlRowSet)

        when:
        trainerNumberSequenceGenerator.generate()

        then:
        thrown(TrainerNumberGenerationException)

        and:
        1 * mockJdbcTemplate.queryForRowSet(_) >> mockSqlRowSet
        1 * mockSqlRowSet.next() >> false
    }

    def "should generate trainer number from sequence successfully"() {
        given:
        def mockSqlRowSet = Mock(SqlRowSet)

        when:
        def trainerNumber = trainerNumberSequenceGenerator.generate()

        then:
        trainerNumber == "TRAINER-000001"

        and:
        1 * mockJdbcTemplate.queryForRowSet(_) >> mockSqlRowSet
        1 * mockSqlRowSet.next() >> true
        1 * mockSqlRowSet.getLong(_) >> 1L
    }
}
