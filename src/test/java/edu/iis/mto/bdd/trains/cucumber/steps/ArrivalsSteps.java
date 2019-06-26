package edu.iis.mto.bdd.trains.cucumber.steps;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.ItineraryServiceImpl;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;

import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;

import java.util.List;

import static org.junit.Assert.*;

public class ArrivalsSteps {
    private String departure;
    private String destination;
    private LocalTime time;
    private String lineName;
    TimetableService timetableService = new InMemoryTimetableService();
    ItineraryServiceImpl itineraryService = new ItineraryServiceImpl(timetableService);
    private LocalTime resultTime;

    @Zakładając("^chcę się dostać z \"([^\"]*)\" do \"([^\"]*)\"$")
    public void chcęSięDostaćZDo(String departure, String destination) throws Throwable {
        this.departure = departure;
        this.destination = destination;
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        assertNotNull("nie ma takiego polączenia", lines);
        assertFalse(lines.isEmpty());
    }

    @I("^następny pociąg odjeżdża o \"([^\"]*)\" na linii \"([^\"]*)\"$")
    public void następnyPociągOdjeżdżaONaLinii(@Transform(JodaLocalTimeConverter.class) LocalTime time,
                                               String line) throws Throwable {
        this.time = time;
        lineName = line;
    }

    @Gdy("^zapytam o godzinę przyjazdu$")
    public void zapytamOGodzinęPrzyjazdu() {
        resultTime = itineraryService.findNextDepartures(departure, destination, lineName, time);

    }

    @Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: \"([^\"]*)\"$")
    public void powinienemUzyskaćNastępującySzacowanyCzasPrzyjazdu(@Transform(JodaLocalTimeConverter.class) LocalTime time) throws Throwable {
        assertTrue(time.equals(resultTime));
    }
}
