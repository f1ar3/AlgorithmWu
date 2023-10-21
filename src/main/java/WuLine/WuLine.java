package WuLine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import static java.lang.Math.*;


public class WuLine {

    void plot(GraphicsContext graphicsContext, double x, double y, double c) {
        graphicsContext.setFill(new Color(0f, 0f, 0f, (float)c));
        graphicsContext.fillOval((int) x, (int) y, 2, 2);
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
        double xend = round(x0);
        double yend = y0 + gradient * (xend - x0);
        double xgap = rfpart(x0 + 0.5);
        double xpxl1 = xend; // this will be used in the main loop
        double ypxl1 = ipart(yend);

        if (steep) {
            plot( graphicsContext,ypxl1, xpxl1, rfpart(yend) * xgap);
            plot(graphicsContext,ypxl1 + 1, xpxl1, fpart(yend) * xgap);
        } else {
            plot(graphicsContext,xpxl1, ypxl1, rfpart(yend) * xgap);
            plot(graphicsContext,xpxl1, ypxl1 + 1, fpart(yend) * xgap);
        }

        // first y-intersection for the main loop
        double intery = yend + gradient;

        // handle second endpoint
        xend = round(x1);
        yend = y1 + gradient * (xend - x1);
        xgap = fpart(x1 + 0.5);
        double xpxl2 = xend; // this will be used in the main loop
        double ypxl2 = ipart(yend);

        if (steep) {
            plot(graphicsContext,ypxl2, xpxl2, rfpart(yend) * xgap);
            plot( graphicsContext,ypxl2 + 1, xpxl2, fpart(yend) * xgap);
        } else {
            plot( graphicsContext,xpxl2, ypxl2, rfpart(yend) * xgap);
            plot( graphicsContext,xpxl2, ypxl2 + 1, fpart(yend) * xgap);
        }

        // main loop
        for (double x = xpxl1 + 1; x <= xpxl2 - 1; x++) {
            if (steep) {
                plot(graphicsContext,ipart(intery), x, rfpart(intery));
                plot(graphicsContext,ipart(intery) + 1, x, fpart(intery));
            } else {
                plot(graphicsContext,x, ipart(intery), rfpart(intery));
                plot(graphicsContext,x, ipart(intery) + 1, fpart(intery));
            }
            intery = intery + gradient;
        }
    }
}