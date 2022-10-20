package com.campgladiator.codechallenge.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition(
    info = @Info(
        title = "CampGladiator Code Challenge Api",
        description = "An API to create and retrieve trainers",
        version = "v1"
    )
)
@RequestMapping("/")
@Controller
public class ApiPortalController {

    @GetMapping
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
