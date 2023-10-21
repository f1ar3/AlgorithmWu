package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;


import WuLine.WuLine;
import javafx.scene.paint.Color;

public class HelloController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;
    @FXML
    private Slider sliderX0;
    @FXML
    private Slider sliderY0;
    @FXML
    private Slider sliderX1;
    @FXML
    private Slider sliderY1;
    WuLine wuLine = new WuLine();
//    double x0 = sliderX0.getValue();
//    double y0 = sliderY0.getValue();
//    double x1 = sliderX1.getValue();
//    double y1 = sliderY1.getValue();
    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        draw();

        sliderX0.valueProperty().addListener((observable, oldValue, newValue) -> {
            draw();
        });
        sliderX1.valueProperty().addListener((observable, oldValue, newValue) -> {
            draw();
        });
        sliderY0.valueProperty().addListener((observable, oldValue, newValue) -> {
            draw();
        });
        sliderY1.valueProperty().addListener((observable, oldValue, newValue) -> {
            draw();
        });
//        wuLine.drawLine(canvas.getGraphicsContext2D(), x0,y0,x1,y1);
    }

    private void draw(){
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0,0,canvas.getWidth(), canvas.getHeight());

        canvas.getGraphicsContext2D().save();

        double x0 = sliderX0.getValue();
        double y0 = sliderY0.getValue();
        double x1 = sliderX1.getValue();
        double y1 = sliderY1.getValue();
        wuLine.drawLine(canvas.getGraphicsContext2D(), x0,y0,x1,y1);
        wuLine.drawLine(canvas.getGraphicsContext2D(), 400,500,500,300);

        canvas.getGraphicsContext2D().restore();
    }

}