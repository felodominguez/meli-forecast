package com.prueba.meli.utils;

import com.prueba.meli.model.Weather;
import com.prueba.meli.to.DayTO;

import java.math.BigDecimal;

public class WeatherUtils {


    /**
     * Calcula si planetas se encuentran alineados con el sol.
     * @param day
     * @return True si los planetas están alineados con el sol
     */
    public static boolean droughtDay(DayTO day) {
        return MathUtils.align(day.getVulcanos().getAngle(), day.getFerengis().getAngle(), day.getBetasoides().getAngle());

    }

    /**
     * Calcula la alineación de los 3 planetas en base a al determinante de la matriz [X1][Y1][1] [X2][Y2][1] [X3][Y3][1]
     * @param today
     * @return True si el determinante de la matriz es 0
     */
    public static boolean optimumDay(DayTO today) {
        return today.getDeterminant() == 0;
    }

    /**
     * Calcula si el punto (0,0) se encuentra contenido el el triángulo formado por las coordenadas de los puntos de los plaetas
     * @param today
     * @return True si el punto (0,0) se encuentra incluido dentro del triángulo.
     */
    public static boolean rainyDay(DayTO today) {
        return MathUtils.pointInside(0, 0, today.getVulcanos().getxPos(), today.getVulcanos().getyPos(), today.getFerengis().getxPos(), today.getFerengis().getyPos(), today.getBetasoides().getxPos(), today.getBetasoides().getyPos());

    }

    /**
     * Compara si hay un cambio en el signo del determinante de 2 días.
     * @param today
     * @param yesterday
     * @return True si el dia anterior no corresponde a sequía y si hay un cambio en el determinante de un día a otro
     */
    public static boolean optimumLastDay(DayTO today, DayTO yesterday) {
        return yesterday.getWeather() != Weather.SEQUIA && ((yesterday.getDeterminant() > 0 && today.getDeterminant() < 0) || (yesterday.getDeterminant() < 0 && today.getDeterminant() > 0));

    }

    /**
     * Calcula el perímetro del triángulo formado por las coordenadas de la ubicación de los planetas.
     * @param today
     * @return Perímetro del triangulo
     */
    public static Double getPerimeter(DayTO today) {
        return MathUtils.getPerimeter(today.getVulcanos().getxPos(), today.getVulcanos().getyPos()
                , today.getFerengis().getxPos(), today.getFerengis().getyPos()
                , today.getBetasoides().getxPos(), today.getBetasoides().getyPos());
    }


}
