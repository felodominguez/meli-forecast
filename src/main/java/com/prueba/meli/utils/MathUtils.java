package com.prueba.meli.utils;

import com.prueba.meli.model.Weather;
import com.prueba.meli.to.DayTO;

import java.math.BigDecimal;

public class MathUtils {

    /**
     * Dado un array de enteros que representan ángulos tomados sobre el eje
     * cartesiano, el método retorna ture si todos los puntos que pudieran
     * existir con dichos ángulos se encuentra todos alineados entre si. Para la
     * utilidad de la prueba el sol se encuentra en el cruce de los ejes X,Y
     *
     * @param pos
     * @return True si todos los puntos están alineados
     */
    public static boolean align(int... pos) {
        int start = pos[0];
        if (start >= 180) {
            start -= 180;
        }
        int next = 0;
        for (int i = 1; i < pos.length; i++) {
            if (pos[i] >= 180) {
                next = pos[i] - 180;
            } else {
                next = pos[i];
            }
            if (start != next) {
                return false;
            }
        }
        return true;
    }




    /**
     * Dada una matríz calcula su determinante.
     *
     * @param matriz
     * @return
     */
    public static double determinant(double[][] matriz) {
        assert matriz != null;
        assert matriz.length > 0;
        assert matriz.length == matriz[0].length;

        double determinante = 0.0;

        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Si la matriz es 1x1, el determinante es el elemento de la matriz
        if ((filas == 1) && (columnas == 1)) {
            return matriz[0][0];
        }

        int signo = 1;

        for (int columna = 0; columna < columnas; columna++) {
            // Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
            double[][] submatriz = getSubmatriz(matriz, filas, columnas,
                    columna);
            determinante = determinante + signo * matriz[0][columna] * determinant(submatriz);
            signo *= -1;
        }

        return determinante;
    }

    /**
     * Obtiene la matriz que resulta de eliminar la primera fila y la columna
     * que se pasa como parámetro.
     *
     * @param matriz   Matriz original
     * @param filas    Numero de filas de la matriz original
     * @param columnas Numero de columnas de la matriz original
     * @param columna  Columna que se quiere eliminar, junto con la fila=0
     * @return Una matriz de N-1 x N-1 elementos
     */
    public static double[][] getSubmatriz(double[][] matriz,
                                          int filas,
                                          int columnas,
                                          int columna) {
        double[][] submatriz = new double[filas - 1][columnas - 1];
        int contador = 0;
        for (int j = 0; j < columnas; j++) {
            if (j == columna) {
                continue;
            }
            for (int i = 1; i < filas; i++) {
                submatriz[i - 1][contador] = matriz[i][j];
            }
            contador++;
        }
        return submatriz;
    }

    /**
     * Calcula el perímetro de un triangulo.
     *
     * @param oneX
     * @param oneY
     * @param twoX
     * @param twoY
     * @param threeX
     * @param threeY
     * @return Perimetro del triangulo
     */
    public static Double getPerimeter(Double oneX, Double oneY, Double twoX, Double twoY, Double threeX, Double threeY) {
        return Math.hypot(twoX - oneX, twoY - oneY) + Math.hypot(twoX - threeX, twoY - threeY) + Math.hypot(oneX - threeX, oneY - threeY);
    }



    /**
     * Dadas las coordenadas de los puntos p, p0, p1 y p2 calcula si p esta
     * contenido dentro del triangulo p0 p1 p2
     *
     * @param px
     * @param py
     * @param p0x
     * @param p0y
     * @param p1x
     * @param p1y
     * @param p2x
     * @param p2y
     * @return Retorna true si el punto p esta contenido dentro del triángulo y
     * false si no lo está
     */
    public static boolean pointInside(double px, double py, double p0x, double p0y, double p1x, double p1y, double p2x, double p2y) {
        double dX = px - p2x;
        double dY = py - p2y;
        double dX21 = p2x - p1x;
        double dY12 = p1y - p2y;
        double D = dY12 * (p0x - p2x) + dX21 * (p0y - p2y);
        double s = dY12 * dX + dX21 * dY;
        double t = (p2y - p0y) * dX + (p0x - p2x) * dY;
        if (D < 0) {
            return s <= 0 && t <= 0 && s + t >= D;
        }
        return s >= 0 && t >= 0 && s + t <= D;
    }

    /**
     * Dadas las coordenadas de dos puntos retorna la distancia
     * @param oneX
     * @param oneY
     * @param twoX
     * @param twoY
     * @return Distancia entre los puntos (oneX,oneY) y (twoX,twoY)
     */
    public static double distance(Double oneX, Double oneY, Double twoX, Double twoY) {
        double px = oneX - twoX;
        double py = oneY - twoY;
        return Math.sqrt(px * px + py * py);
    }

    public static boolean alineados(Integer oneAngle, Integer oneDist, Integer twoAngle, Integer twoDist, Integer threeAngle, Integer threeDist) {
        BigDecimal yOne = new BigDecimal((Math.sin(Math.toRadians(oneAngle))) * oneDist);
        BigDecimal xOne = new BigDecimal((Math.cos(Math.toRadians(oneAngle))) * oneDist);
        BigDecimal yTwo = new BigDecimal((Math.sin(Math.toRadians(twoAngle))) * twoDist);
        BigDecimal xTwo = new BigDecimal((Math.cos(Math.toRadians(twoAngle))) * twoDist);
        BigDecimal yThree = new BigDecimal((Math.sin(Math.toRadians(threeAngle))) * threeDist);
        BigDecimal xThree = new BigDecimal((Math.cos(Math.toRadians(threeAngle))) * threeDist);

        if (((yOne.subtract(yTwo)).multiply((xThree.subtract(xOne))))
                .equals((yThree.subtract(yOne)).multiply((xOne.subtract(xTwo))))) {
            return true;
        } else {
            return false;
        }

//
    }
}
