package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.List;
import java.util.NoSuchElementException;

public class ItineraryServiceImpl implements ItineraryService {
    TimetableService timetableService;

    public ItineraryServiceImpl(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @Override
    public LocalTime findNextDepartures(String departure, String destination, LocalTime time) {
        List<Line> lineList = timetableService.findLinesThrough(departure, destination);

        LocalTime theBestTime = null;

        for (Line line : lineList) {
            LocalTime theTime = timetableService.findArrivalTimes(line, departure)
                    .stream()
                    .filter(localTime -> localTime.isAfter(time))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException());

            if (theBestTime == null || theTime.isBefore(theBestTime)) {
                theBestTime = theTime;
            }
        }

        return theBestTime;
    }
}

