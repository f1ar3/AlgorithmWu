package ru.vsu.cs.zagorodnev_g_a.WuLine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class WuLine {
//    private final Canvas canvas;
//    private final GraphicsContext gc;
//
//    public WuLine(Canvas canvas) {
//        this.canvas = canvas;
//        this.gc = canvas.getGraphicsContext2D();
//    }

    public void drawLine(GraphicsContext gc, double x0, double y0, double x1, double y1) {
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        if (steep) {
            // Swap x0, y0
            double temp = x0;
            x0 = y0;
            y0 = temp;
            // Swap x1, y1
            temp = x1;
            x1 = y1;
            y1 = temp;
        }
        if (x0 > x1) {
            // Swap x0, x1
            double temp = x0;
            x0 = x1;
            x1 = temp;
            // Swap y0, y1
            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        double dx = x1 - x0;
        double dy = y1 - y0;
        double gradient = dy / dx;

        double xend = Math.round(x0);
        double yend = y0 + gradient * (xend - x0);
        double xgap = 1 - fpart(x0 + 0.5);
        int xpxl1 = (int) xend;
        int ypxl1 = ipart(yend);

        if (steep) {
            plot(gc,ypxl1, xpxl1, rfpart(yend) * xgap);
            plot(gc,ypxl1 + 1, xpxl1, fpart(yend) * xgap);
        } else {
            plot(gc,xpxl1, ypxl1, rfpart(yend) * xgap);
            plot(gc,xpxl1, ypxl1 + 1, fpart(yend) * xgap);
        }

        double intery = yend + gradient;

        xend = Math.round(x1);
        yend = y1 + gradient * (xend - x1);
        xgap = fpart(x1 + 0.5);
        int xpxl2 = (int) xend;
        int ypxl2 = ipart(yend);

        if (steep) {
            plot(gc,ypxl2, xpxl2, rfpart(yend) * xgap);
            plot(gc,ypxl2 + 1, xpxl2, fpart(yend) * xgap);
        } else {
            plot(gc,xpxl2, ypxl2, rfpart(yend) * xgap);
            plot(gc,xpxl2, ypxl2 + 1, fpart(yend) * xgap);
        }

        for (int x = xpxl1 + 1; x < xpxl2; x++) {
            if (steep) {
                plot(gc,ipart(intery), x, rfpart(intery));
                plot(gc,ipart(intery) + 1, x, fpart(intery));
            } else {
                plot(gc, x, ipart(intery), rfpart(intery));
                plot(gc, x, ipart(intery) + 1, fpart(intery));
            }
            intery += gradient;
        }
    }

    private void plot(GraphicsContext gc, int x, int y, double c) {
        int alpha = (int) (c * 255);
        gc.setFill(Color.rgb(0, 0, 0, alpha / 255.0));
        gc.fillRect(x, y, 1, 1);
    }

    private int ipart(double x) {
        return (int) x;
    }

    private double fpart(double x) {
        return x - Math.floor(x);
    }

    private double rfpart(double x) {
        return 1 - fpart(x);
    }
}