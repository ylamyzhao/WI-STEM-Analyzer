package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ACSControllerTest {

    private ACSController acsController;

    @Mock
    private ACSService acsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        acsController = new ACSController(acsService);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        String query = "CA";
        Result result = new Result("", "", "");
        result.setState("California");
        result.setDegreeEarnedByWomen("10");
        result.setDegreeEarnedByMen("100");

        String expectedResults = "In California, there are 100 STEM degrees earned by men and 10 STEM degrees earned by women.";

        when(acsService.getResults(query)).thenReturn(expectedResults);

        //when
        String actualResults = acsController.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        String query = "not a state";

        //when
        when(acsController.getResults(query)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found."));

        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> acsController.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }
}