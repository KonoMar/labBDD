package edu.iis.mto.bdd.trains.junit;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.ItineraryServiceImpl;
import edu.iis.mto.bdd.trains.services.TimetableService;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhenCalculatingArrivalTimes {
    @Mock
    private TimetableService timetableService;
    private Line line;
    private ItineraryServiceImpl itineraryService;

    @Before
    public void setUp() throws Exception {
        line = Line.named("Western").departingFrom("Emu Plains").withStations("Emu Plains",
                "Parramatta", "Town Hall",
                "North Richmond");
        itineraryService = new ItineraryServiceImpl(timetableService);

        when(timetableService.findLinesThrough("Emu Plains", "North Richmond"))
                .thenReturn(new LinkedList<Line>() {{
            add(line);
        }});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnTimeForEmuPlainsStation() {
        //g

        when(timetableService.findLinesThrough("Emu Plains", "North Richmond")).thenReturn(new LinkedList<Line>() {{
            add(line);
        }});
        when(timetableService.findArrivalTimes(line, "Emu Plains")).thenReturn(new LinkedList<LocalTime>() {{
            add(LocalTime.parse("10:10"));
        }});
        when(timetableService.findArrivalTimes(line, "North Richmond")).thenReturn(new LinkedList<LocalTime>() {{
            add(LocalTime.parse("12:10"));
        }});
        //w

        LocalTime localTime = itineraryService.findNextDepartures("Emu Plains", "North Richmond"
                ,"Western", LocalTime.parse("11:00"));
    }
}
