package ru.vsu.cs.zagorodnev_g_a.WuLine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import static java.lang.Math.*;


public class WuLine {

    void plot(GraphicsContext graphicsContext, double x, double y, double c) {
        int alpha = (int) (c * 255);
        graphicsContext.setFill(Color.rgb(0, 0, 0, alpha / 255.0));
        graphicsContext.fillRect(x, y, 1, 1);
    }

    int ipart(double x) {
        return (int) x;
    }

    double fpart(double x) {
        return x - floor(x);
    }

    double rfpart(double x) {
        return 1.0 - fpart(x);
    }

    public void drawLine(GraphicsContext graphicsContext,double x0, double y0, double x1, double y1) {

        boolean steep = abs(y1 - y0) > abs(x1 - x0);
        if (steep)
            drawLine(graphicsContext,y0, x0, y1, x1);

        if (x0 > x1)
            drawLine(graphicsContext,x1, y1, x0, y0);

        double dx = x1 - x0;
        double dy = y1 - y0;
        double gradient = dy / dx;

        // handle first endpoint
        double xEnd = round(x0);
        double yEnd = y0 + gradient * (xEnd - x0);
        double xGap = rfpart(x0 + 0.5);
        double xPxl1 = xEnd; // this will be used in the main loop
        double yPxl1 = ipart(yEnd);

        if (steep) {
            plot( graphicsContext,yPxl1, xPxl1, rfpart(yEnd) * xGap);
            plot(graphicsContext,yPxl1 + 1, xPxl1, fpart(yEnd) * xGap);
        } else {
            plot(graphicsContext,xPxl1, yPxl1, rfpart(yEnd) * xGap);
            plot(graphicsContext,xPxl1, yPxl1 + 1, fpart(yEnd) * xGap);
        }

        // first y-intersection for the main loop
        double intersection = yEnd + gradient;

        // handle second endpoint
        xEnd = round(x1);
        yEnd = y1 + gradient * (xEnd - x1);
        xGap = fpart(x1 + 0.5);
        double xPxl2 = xEnd; // this will be used in the main loop
        double yPxl2 = ipart(yEnd);

        if (steep) {
            plot(graphicsContext,yPxl2, xPxl2, rfpart(yEnd) * xGap);
            plot( graphicsContext,yPxl2 + 1, xPxl2, fpart(yEnd) * xGap);
        } else {
            plot( graphicsContext,xPxl2, yPxl2, rfpart(yEnd) * xGap);
            plot( graphicsContext,xPxl2, yPxl2 + 1, fpart(yEnd) * xGap);
        }

        // main loop
        for (double x = xPxl1 + 1; x <= xPxl2 - 1; x++) {
            if (steep) {
                plot(graphicsContext,ipart(intersection), x, rfpart(intersection));
                plot(graphicsContext,ipart(intersection) + 1, x, fpart(intersection));
            } else {
                plot(graphicsContext,x, ipart(intersection), rfpart(intersection));
                plot(graphicsContext,x, ipart(intersection) + 1, fpart(intersection));
            }
            intersection = intersection + gradient;
        }
    }
}