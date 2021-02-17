package com.prueba.meli.to;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParametersTO {

    private Date released;
    private String version;
    //    private Connection connection;
    private List<String> protocols;
    private Map<String, String> users;
    private Boolean logData;

    private Integer initVulcanos;
    private Integer initFerengis;
    private Integer initBetasoides;
    private Integer year;
    private Integer avanceVulcanos;
    private Integer avanceFerengis;
    private Integer avanceBetasoides;
    private Integer distanceVulcanos;
    private Integer distanceFerengis;
    private Integer distanceBetasoides;

    public Date getReleased() {
        return released;
    }

    public String getVersion() {
        return version;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    public Connection getConnection() {
//        return connection;
//    }
//
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public List<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<String> protocols) {
        this.protocols = protocols;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    public Integer getInitVulcanos() {
        return initVulcanos;
    }

    public void setInitVulcanos(Integer initVulcanos) {
        this.initVulcanos = initVulcanos;
    }

    public Integer getInitFerengis() {
        return initFerengis;
    }

    public void setInitFerengis(Integer initFerengis) {
        this.initFerengis = initFerengis;
    }

    public Integer getInitBetasoides() {
        return initBetasoides;
    }

    public void setInitBetasoides(Integer initBetasoides) {
        this.initBetasoides = initBetasoides;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAvanceVulcanos() {
        return avanceVulcanos;
    }

    public void setAvanceVulcanos(Integer avanceVulcanos) {
        this.avanceVulcanos = avanceVulcanos;
    }

    public Integer getAvanceFerengis() {
        return avanceFerengis;
    }

    public void setAvanceFerengis(Integer avanceFerengis) {
        this.avanceFerengis = avanceFerengis;
    }

    public Integer getAvanceBetasoides() {
        return avanceBetasoides;
    }

    public void setAvanceBetasoides(Integer avanceBetasoides) {
        this.avanceBetasoides = avanceBetasoides;
    }

    public Integer getDistanceVulcanos() {
        return distanceVulcanos;
    }

    public void setDistanceVulcanos(Integer distanceVulcanos) {
        this.distanceVulcanos = distanceVulcanos;
    }

    public Integer getDistanceFerengis() {
        return distanceFerengis;
    }

    public void setDistanceFerengis(Integer distanceFerengis) {
        this.distanceFerengis = distanceFerengis;
    }

    public Integer getDistanceBetasoides() {
        return distanceBetasoides;
    }

    public void setDistanceBetasoides(Integer distanceBetasoides) {
        this.distanceBetasoides = distanceBetasoides;
    }

    public Boolean getLogData() {
        return logData;
    }

    public void setLogData(Boolean logData) {
        this.logData = logData;
    }

    @Override
    public String toString() {
        return new StringBuilder()
//                .append(String.format("Version: %s\n", version))
//                .append(String.format("Released: %s\n", released))
//                .append( format( "Connecting to database: %s\n", connection ) )
//                .append(String.format("Supported protocols: %s\n", protocols))
                .append(String.format("LogData: %s\n", logData))
//                .append(String.format("Users: %s\n", users))
                .append(String.format("Vulcanos initial angle: %s\n", initVulcanos))
                .append(String.format("Ferengis initial angle: %s\n", initFerengis))
                .append(String.format("Betasoides initial angle: %s\n", initBetasoides))
                .append(String.format("Total years: %s\n", year))
                .append(String.format("Vulcanos daily avance: %s\n", avanceVulcanos))
                .append(String.format("Ferengis avance: %s\n", avanceFerengis))
                .append(String.format("Betasoides avance: %s\n", avanceBetasoides))
                .append(String.format("Vulcanos distance: %s\n", distanceVulcanos))
                .append(String.format("Ferengis distance: %s\n", distanceFerengis))
                .append(String.format("Betasoides distance: %s\n", distanceBetasoides))

                .toString();


    }
}
