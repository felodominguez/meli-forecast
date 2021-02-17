package com.prueba.meli.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.decimal4j.util.DoubleRounder;

import java.io.Serializable;

/**
 * @author FD
 * Clase que representa los planetas
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanetTO implements Serializable {

    private Integer angle;
    private Integer sunDistance;
    private Integer advance;
    private Double xPos;
    private Double yPos;

    public PlanetTO(Integer angle, Integer sunDistance,Integer advance) {
        this.angle = angle;
        this.sunDistance = sunDistance;
        this.advance=advance;
        this.calculatePosition();
    }

    public PlanetTO(Integer angle, Integer sunDistance) {
        this.angle = angle;
        this.sunDistance = sunDistance;

    }

    public PlanetTO(Integer angle, Double xPos, Double yPos) {
        this.angle = angle;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //    public PlanetTO(Integer angle, Integer sunDistance, Double xPos, Double yPos) {
//        this.angle = angle;
//        this.sunDistance = sunDistance;
//        this.xPos = xPos;
//        this.yPos = yPos;
//    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getSunDistance() {
        return sunDistance;
    }

    public void setSunDistance(Integer sunDistance) {
        this.sunDistance = sunDistance;
    }

    public Double getxPos() {
        return xPos;
    }

    public void setxPos(Double xPos) {
        this.xPos = xPos;
    }

    public Double getyPos() {
        return yPos;
    }

    public void setyPos(Double yPos) {
        this.yPos = yPos;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }

    public void calculate(){
        next();
        calculatePosition();
    }

    private void next() {
        if (angle > 0) {
            this.angle += this.advance;
            if (this.angle >= 360) {
                this.angle -= 360;
            }
        } else {
            this.angle += this.advance;
            if (this.angle < 0) {
                this.angle = 360 + this.angle;
            }
        }
    }

    private void calculatePosition() {

        double radians = Math.toRadians(this.angle);
        this.yPos = DoubleRounder.round(Math.sin(radians), 12) * this.sunDistance;
        this.xPos = DoubleRounder.round(Math.cos(radians), 12) * this.sunDistance;
    }


    public String printStatus(long day, String name) {
        return (name + " D:" + day + " A:" + this.angle + " X:" + this.xPos + " Y:" + this.yPos);
    }

    public String log() {
        return ("Angle:"+this.angle +" Distance:" + this.sunDistance + " X:" + this.xPos + " Y:" + this.yPos);
    }

}
