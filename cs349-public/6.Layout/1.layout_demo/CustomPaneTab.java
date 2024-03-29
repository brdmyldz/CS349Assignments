/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomPaneTab extends Tab {

    final double BAR_WIDTH = 20;
    final double MAX_BAR_HEIGHT = 500;

    final long oneSecond = 1_000_000_000L;
    final long halfSecond = oneSecond / 2;
    final long thirdSecond = oneSecond / 3;
    final long forthSecond = oneSecond / 4;
    final long fifthSecond = oneSecond / 5;
    final long sixthSecond = oneSecond / 6;
    final long seventhSecond = oneSecond / 7;
    final long eighthSecond = oneSecond / 8;
    final long ninthSecond = oneSecond / 9;

    final CustomPane customPane = new CustomPane();
    Spinner updateSpinner;
    AnimationTimer timer;

    public CustomPaneTab(String text) {
        this.setText(text);
        init();
    }

    public void init() {

        int width = 50;
        customPane.getChildren().addAll(
                new Bar(width, MAX_BAR_HEIGHT, Color.RED),
                new Bar(width, MAX_BAR_HEIGHT * 0.7, Color.GREEN),
                new Bar(width, MAX_BAR_HEIGHT * 0.1, Color.AQUAMARINE),
                new Bar(width, MAX_BAR_HEIGHT * 0.3, Color.SKYBLUE),
                new Bar(width, MAX_BAR_HEIGHT * 0.5, Color.BROWN),
                new Bar(width, MAX_BAR_HEIGHT * 0.9, Color.CORNFLOWERBLUE),
                new Bar(width, MAX_BAR_HEIGHT * 0.8, Color.BEIGE)
        );

        BorderPane root = new BorderPane(customPane);

        customPane.getStyleClass().add("source");

        Label updateSpinnerLabel = new Label("Updates Per Second");
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9);
        updateSpinner = new Spinner(svf);
        updateSpinner.setPrefWidth(70);

        CheckBox animateCbx = new CheckBox("Animate");
        animateCbx.setOnAction(e -> setAnimate(animateCbx.isSelected()));
        CheckBox rightCbx = new CheckBox("Button Right");
        rightCbx.setOnAction(e -> setAnimate(rightCbx.isSelected()));
        CheckBox bottomCbx = new CheckBox("Button Bottom");
        bottomCbx.setOnAction(e -> setAnimate(bottomCbx.isSelected()));
        CheckBox resetPositionCbx = new CheckBox("Button Reset Position");
        resetPositionCbx.setOnAction(e -> setAnimate(resetPositionCbx.isSelected()));


        HBox controlGrp = new HBox(animateCbx, updateSpinnerLabel, updateSpinner);
        controlGrp.getStyleClass().add("control");
        controlGrp.setAlignment(Pos.CENTER_LEFT);
        root.setTop(controlGrp);
        this.setContent(root);

        timer = new AnimationTimer() {
            private long nextUpdate = 0;
            private long nextSecond = 0;
            private int framesPerSecond = 0;

            @Override
            public void handle(long startNanos) {
                framesPerSecond++;
                int update = (int)updateSpinner.getValue();

                switch (update) {
                    case 1:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + oneSecond;
                        }
                        break;
                    case 2:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + halfSecond;
                        }
                        break;
                    case 3:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + thirdSecond;
                        }
                        break;
                    case 4:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + forthSecond;
                        }
                        break;
                    case 5:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + fifthSecond;
                        }
                        break;
                    case 6:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + sixthSecond;
                        }
                        break;
                    case 7:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + seventhSecond;
                        }
                        break;
                    case 8:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + eighthSecond;
                        }
                        break;
                    case 9:
                        if (startNanos > nextUpdate) {
                            updateData();
                            nextUpdate = startNanos + ninthSecond;
                        }
                        break;
                }



                if (startNanos > nextSecond) {
                    System.out.println("fps: " + framesPerSecond);
                    framesPerSecond = 0;
                    nextSecond = startNanos + 1_000_000_000L;
//                    System.err.println("Value = " + updateSpinner.getValue());
                }
            }
        };
    }

    void setAnimate(boolean animate) {
        if (animate) {
            timer.start();
        } else {
            timer.stop();
        }
    }

    void updateData() {

        List<Node> chidlren = new ArrayList<>(customPane.getChildren());
        for (Node c : chidlren) {
            Bar bar = (Bar)c;
            bar.rect.setHeight(Math.random() * MAX_BAR_HEIGHT);
        }

    }

    class Bar extends Group {

        double height;
        double width;
        Paint color = Color.BLACK;
        Rectangle rect;

        Bar(double width, double height, Paint color) {
            this.width = width;
            this.height = height;
            this.color = color;
            rect = new Rectangle(width, height, color);
            rect.setArcHeight(20);
            rect.setArcWidth(20);
            rect.setEffect(new DropShadow());
            this.getChildren().add(rect);
        }

    }

}

