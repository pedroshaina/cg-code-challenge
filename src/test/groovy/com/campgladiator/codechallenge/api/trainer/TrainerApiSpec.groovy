package com.campgladiator.codechallenge.api.trainer

import com.campgladiator.codechallenge.api.ApiExceptionHandler
import com.campgladiator.codechallenge.entity.trainer.Trainer
import com.campgladiator.codechallenge.entity.trainer.TrainerNumberSequenceGenerator
import com.campgladiator.codechallenge.repository.trainer.TrainerRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import(ApiExceptionHandler)
@WebMvcTest(controllers = [TrainerRestController])
class TrainerApiSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    TrainerRepository mockTrainerRepository = Mock(TrainerRepository)

    @SpringBean
    TrainerNumberSequenceGenerator mockTrainerNumberSequenceGenerator = Mock(TrainerNumberSequenceGenerator)

    def "GET /trainer/{trainerNumber} - should return 404 given invalid trainerNumber"() {
        when:
        def response = mockMvc.perform(get("/trainer/{trainerNumber}", "invalid"))

        then:
        response.andExpect(status().isNotFound())

        and:
        1 * mockTrainerRepository.findOneByTrainerNumber(_) >> Optional.empty()
    }

    def "GET /trainer/{trainerNumber} - should return 200 with trainer details given valid trainerNumber"() {
        when:
        def response = mockMvc.perform(get("/trainer/{trainerNumber}", "TRAINER-000001"))

        then:
        response.andExpect(status().isOk())
            .andExpect(content().json(loadExpectedResponse("trainer-details")))

        and:
        1 * mockTrainerRepository.findOneByTrainerNumber(_) >> Optional.of(
                createTrainer("pedro@example.com", "2120981423", "Pedro", "Martins")
        )
    }

    def "POST /trainer - given invalid JSON should return 400"() {
        when:
        def response = mockMvc.perform(
            post("/trainer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loadRequest("invalid"))
        )

        then:
        response.andExpect(status().isBadRequest())
    }

    def "POST /trainer - given bad data should return 422"() {
        when:
        def response = mockMvc.perform(
                post("/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadRequest("bad-data"))
        )

        then:
        response.andExpect(status().isUnprocessableEntity())
    }

    def "POST /trainer - given valid request payload should return 201"() {
        when:
        def response = mockMvc.perform(
                post("/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loadRequest("valid"))
        )

        then:
        response.andExpect(status().isCreated())
            .andExpect(content().json(loadExpectedResponse("trainer-details")))

        and:
        1 * mockTrainerNumberSequenceGenerator.generate() >> "TRAINER-000001"
        1 * mockTrainerRepository.save(_) >>
                createTrainer("pedro@example.com", "2120981423", "Pedro", "Martins")
    }

    def createTrainer(email, phone, firstName, lastName) {
        final Trainer trainer = new Trainer()
        trainer.setId(1)
        trainer.setEmail(email)
        trainer.setPhone(phone)
        trainer.setFirstName(firstName)
        trainer.setLastName(lastName)
        trainer.setTrainerNumber("TRAINER-000001")
        return trainer
    }

    def loadExpectedResponse(file) {
        return new File("src/test/resources/trainer-api-test-data/response/${file}.json").getText()
    }

    def loadRequest(file) {
        return new File("src/test/resources/trainer-api-test-data/request/${file}.json").getText()
    }
}
