
# SpaceX Launch Summary API

This Spring Boot application provides an API endpoint that retrieves data from SpaceX's public APIs, processes it, and returns a summarized list of launches with rocket details.

## Table of Contents

- [Overview](#overview)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [Technologies](#technologies)
- [License](#license)

## Overview

The application calls two SpaceX API endpoints:

1. [Launches](https://api.spacexdata.com/v3/launches)
2. [Rockets](https://api.spacexdata.com/v3/rockets)

It combines the data from these APIs into a new data structure that includes information about each launch, its associated rocket, and its payloads.

## Endpoints

### `/summary-launches`

- **Method:** GET
- **Description:** Returns a list of SpaceX launches with summarized rocket and payload details.
- **Response Format:** JSON
- **Response Example:**
  ```json
  [
    {
      "flightNumber": 39,
      "missionName": "NROL-76",
      "rocket": {
        "rocketId": "falcon9",
        "rocketName": "Falcon 9",
        "description": "Falcon 9 is a two-stage rocket...",
        "images": [
          "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg",
          "https://farm4.staticflickr.com/3955/32915197674_eee74d81bb_b.jpg"
        ]
      },
      "payloads": [
        {
          "payloadId": "NROL-76",
          "manufacturer": "Boeing",
          "type": "Satellite"
        }
      ]
    }
  ]
  ```

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/spacex-launch-summary.git
   cd spacex-launch-summary
   ```

2. **Build the project:**
   Make sure you have [Maven](https://maven.apache.org/) and [Java 17+](https://adoptopenjdk.net/) installed.
   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **The API will be available at:**
   ```
   http://localhost:8080/summary-launches
   ```

## Usage

Once the application is running, you can access the endpoint to get the summarized SpaceX launch data:

- **GET** `http://localhost:8080/summary-launches`

Example request using `curl`:
```bash
curl -X GET http://localhost:8080/summary-launches
```

The application will return a JSON array with the launch details, associated rockets, and payloads.

## Testing

### Unit Tests

To run the unit tests:
```bash
./mvnw test
```

The tests cover:
- Successful retrieval of launches and rockets.
- Handling of error scenarios for both `/launches` and `/rockets` API calls.
  
### Sample Tests

The `TravelServiceTest` class provides test cases for the following:
- Successful response from both the `/launches` and `/rockets` APIs.
- Failure handling for each of the APIs (e.g., when `/launches` fails but `/rockets` succeeds, and vice versa).

## Technologies

- **Java 17+**
- **Spring Boot** (REST API)
- **WebClient** (to make API calls)
- **JUnit 5** (for unit testing)
- **Mockito** (for mocking dependencies in tests)
- **Gradle** (build tool)

