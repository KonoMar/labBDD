package edu.iis.mto.bdd.trains.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;

public class Line {

    private final String line;
    private final String departingFrom;
    private final List<String> stations;

    private Line(String line, String departingFrom) {
        this.line = line;
        this.departingFrom = departingFrom;
        this.stations = null;
    }

    private Line(String line, String departingFrom, List<String> stations) {
        this.line = line;
        this.departingFrom = departingFrom;
        this.stations = stations;
    }

    public String getDepartingFrom() {
        return departingFrom;
    }

    public String getLine() {
        return line;
    }

    public List<String> getStations() {
        return ImmutableList.copyOf(stations);
    }

    public Line withStations(String... stations) {
        return new Line(this.line, this.departingFrom, ImmutableList.copyOf(stations));
    }

    public static LineBuilder named(String lineName) {
        return new LineBuilder(lineName);
    }

    public static final class LineBuilder {

        private final String lineName;

        public LineBuilder(String lineName) {
            this.lineName = lineName;
        }

        public Line departingFrom(String station) {
            return new Line(lineName, station);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Line line1 = (Line) o;


        if (!Objects.equals(departingFrom, line1.departingFrom)) {
            return false;
        }
        if (!Objects.equals(line, line1.line)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + (departingFrom != null ? departingFrom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" + "line='" + line + '\'' + ", departingFrom='" + departingFrom + '\'' + '}';
    }
}
