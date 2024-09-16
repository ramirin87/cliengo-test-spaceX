package com.cliengo.test.service;

import com.cliengo.test.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TravelServiceTest {

    @InjectMocks
    private TravelService travelService;


    @Test
    void getLaunchesWithRocketDetails_Success() {
        Mockito.doReturn(getMockLaunchSources()).when(travelService).getLaunches();

        Mockito.doReturn(getMockRocketSources()).when(travelService).getRockets();

        var launchesWithDetails = travelService.getLaunchesWithRocketDetails();

        assertNotNull(launchesWithDetails);
        assertEquals(1, launchesWithDetails.size());
        assertEquals("Falcon 9", launchesWithDetails.get(0).getRocket().getRocketName());
        assertEquals("NROL-76", launchesWithDetails.get(0).getPayloads().get(0).getPayloadId());
    }

    @Test
    void getLaunchesWithRocketDetails_FailureInLaunches() {

        // Act
        List<Launch> launchesWithDetails = travelService.getLaunchesWithRocketDetails();

        // Assert
        assertNotNull(launchesWithDetails);
        assertTrue(launchesWithDetails.isEmpty()); // Expecting empty response due to failure
    }

    @Test
    void getLaunchesWithRocketDetails_FailureInRockets() {
        // Arrange
        Mockito.doReturn(getMockLaunchSources()).when(travelService).getLaunches();

        // Act
        List<Launch> launchesWithDetails = travelService.getLaunchesWithRocketDetails();

        // Assert
        assertNotNull(launchesWithDetails);
        assertEquals(1, launchesWithDetails.size());
        assertNull(launchesWithDetails.get(0).getRocket()); // Rocket details are missing due to failure
    }

    // Helper methods to create mock data
    private LaunchSource[] getMockLaunchSources() {
        LaunchSource launchSource = new LaunchSource();
        launchSource.setFlightNumber(39);
        launchSource.setMissionName("NROL-76");

        Rocket rocket = new Rocket();
        rocket.setRocketId("falcon9");

        SecondStage secondStage = new SecondStage();
        Payload payloadSource = new Payload();
        payloadSource.setPayloadId("NROL-76");
        payloadSource.setManufacturer("Boeing");
        payloadSource.setType("Satellite");

        secondStage.setPayloads(new Payload[]{payloadSource});
        rocket.setSecondStage(secondStage);
        launchSource.setRocket(rocket);

        return new LaunchSource[]{launchSource};
    }

    private RocketSource[] getMockRocketSources() {
        RocketSource rocketSource = new RocketSource();
        rocketSource.setRocketId("falcon9");
        rocketSource.setRocketName("Falcon 9");
        rocketSource.setDescription("Falcon 9 is a two-stage rocket...");
        rocketSource.setImages(List.of("image1", "image2"));

        return new RocketSource[]{rocketSource};
    }
}