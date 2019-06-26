package edu.iis.mto.bdd.trains.services;

import java.util.List;

import org.joda.time.LocalTime;

import edu.iis.mto.bdd.trains.model.Line;

public interface TimetableService {

    List<LocalTime> findArrivalTimes(Line line, String targetStation);

    List<Line> findLinesThrough(String departure, String destination);

    void scheduleArrivalTime(String line, LocalTime departureTime);

    LocalTime getArrivalTime(String travellingOnLine, String destination);

    void setUniversalDepartureTimes(List<LocalTime> universalDepartureTimes);
}
