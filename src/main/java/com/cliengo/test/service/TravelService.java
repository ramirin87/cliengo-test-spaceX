package com.cliengo.test.service;

import com.cliengo.test.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@AllArgsConstructor
@Service
public class TravelService {
    private WebClient webClient;

    public TravelService() {
        this.webClient = WebClient.builder().baseUrl("https://api.spacexdata.com/v3").
                exchangeStrategies(ExchangeStrategies
                .builder()
                .codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(500 * 1024))
                .build()).build();
    }

    public List<Launch> getLaunchesWithRocketDetails() {
        LaunchSource[] launches = getLaunches();
        RocketSource[] rockets = getRockets();

        List<Launch> response = new ArrayList<>();
        assert launches != null;
        Arrays.stream(launches).toList().forEach(launch -> {
            response.add(new Launch(launch,
                    Arrays.stream(launch.getRocket().getSecondStage().getPayloads()).toList(),
                    getRocketSource(launch.getRocket().getRocketId(),rockets)));
        });

        return response;
    }

    private RocketShow getRocketSource(String id, RocketSource[] rockets) {
        var optRocket = Arrays.stream(rockets).filter(r -> r.getRocketId().equals(id)).findFirst();
        return optRocket.map(RocketShow::new).orElse(null);
    }

    public LaunchSource[] getLaunches() {
        try {
            return webClient.get()
                    .uri("/launches")
                    .retrieve()
                    .bodyToMono(LaunchSource[].class)
                    .block();

        }catch(Exception e){
            log.error(e.getMessage());
            return null;

        }
    }

    public RocketSource[] getRockets() {
        try {
            return webClient.get()
                    .uri("/rockets")
                    .retrieve()
                    .bodyToMono(RocketSource[].class)
                    .block();

        }catch(Exception e){
            log.error(e.getMessage());
            return null;

        }
    }

}
