package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

public interface ItineraryService {
    LocalTime findNextDepartures(String travellingOnLine, String destination, LocalTime time);
}
