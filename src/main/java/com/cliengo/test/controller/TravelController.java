package com.cliengo.test.controller;

import com.cliengo.test.model.Launch;
import com.cliengo.test.service.TravelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class TravelController {

    private TravelService travelService;

    @GetMapping("/summary-launches")
    public List<Launch> getLaunchesWithRockets() {
        return travelService.getLaunchesWithRocketDetails();
    }

}
