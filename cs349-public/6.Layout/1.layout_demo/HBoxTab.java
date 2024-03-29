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

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HBoxTab extends Tab {

    final HBox hbox = new HBox(10); // 10px spacing
    final Button okBtn = new Button("OK");
    final Button cancelBtn = new Button("Cancel");

    public HBoxTab(String text) {
        this.setText(text);
        init();
    }

    public void init() {

        final Label descLbl = new Label("Description:");
        final TextArea desc = new TextArea();
        desc.setPrefColumnCount(10);
        desc.setPrefRowCount(3);
        desc.setWrapText(true);

        hbox.getChildren().addAll(descLbl, desc, okBtn, cancelBtn);
        hbox.getStyleClass().add("source");

        BorderPane root = new BorderPane(hbox);

        final CheckBox hGrowCbx = new CheckBox("TextArea Hgrow");

        hGrowCbx.setSelected(false);
        hGrowCbx.setOnAction(e
                -> growHorizontal(desc, hGrowCbx.isSelected()));

        CheckBox maxSizeButtonCbx = new CheckBox("Button Max Size");
        maxSizeButtonCbx.setOnAction(e -> maxSizeButton(maxSizeButtonCbx.isSelected()));

        CheckBox fillHeightCbx = new CheckBox("Fill Height");
        // HBox's fillHeight is true by default.
        fillHeightCbx.setSelected(true);

        // Add an event handler to the CheckBox, so the user can set the
        // fillHeight property using the CheckBox
        fillHeightCbx.setOnAction(e
                -> hbox.setFillHeight(fillHeightCbx.isSelected()));



        ChoiceBox<Pos> alignmentCBox = new ChoiceBox<>();
        alignmentCBox.getItems().addAll(
                Pos.BASELINE_CENTER, Pos.BASELINE_LEFT, Pos.BASELINE_RIGHT,
                Pos.BOTTOM_CENTER, Pos.BOTTOM_LEFT, Pos.BOTTOM_RIGHT,
                Pos.CENTER, Pos.CENTER_LEFT, Pos.CENTER_RIGHT,
                Pos.TOP_CENTER, Pos.TOP_LEFT, Pos.TOP_RIGHT);
        alignmentCBox.getSelectionModel().select(hbox.getAlignment());

        // Add ChangeListeners to track change in selected index and item. Only
        // one listener is necessary if you want to track change in selection
        alignmentCBox.getSelectionModel().selectedItemProperty().addListener(this::itemChanged);
        alignmentCBox.getSelectionModel().selectedIndexProperty().addListener(this::indexChanged);

        Label alignmentLabel = new Label("Alignment");
        HBox controlGrp = new HBox(alignmentLabel, alignmentCBox,
                fillHeightCbx, hGrowCbx, maxSizeButtonCbx);
        controlGrp.getStyleClass().add("control");
        controlGrp.setAlignment(Pos.CENTER_LEFT);
        root.setTop(controlGrp);

        this.setContent(root);
    }

    void maxSizeButton(boolean stretch) {
        if (stretch) {
            // Let the Cancel button expand vertically
            okBtn.setMaxHeight(Double.MAX_VALUE);
            cancelBtn.setMaxHeight(Double.MAX_VALUE);
        } else {
            okBtn.setMaxHeight(okBtn.getPrefHeight());
            cancelBtn.setMaxHeight(cancelBtn.getPrefHeight());
        }

    }

    void growHorizontal(TextArea desc, boolean grow) {
        if (grow) {
            HBox.setHgrow(desc, Priority.ALWAYS);
        } else {
            HBox.setHgrow(desc, Priority.NEVER);
        }
    }

    // A change listener to track the change in selected item
    public void itemChanged(ObservableValue<? extends Pos> observable,
            Pos oldValue,
            Pos newValue) {
//        System.out.println("Itemchanged: old = " + oldValue + ", new = " + newValue);
        hbox.setAlignment(newValue);
    }

    // A change listener to track the change in selected index
    public void indexChanged(ObservableValue<? extends Number> observable,
            Number oldValue,
            Number newValue) {
//        System.out.println("Indexchanged: old = " + oldValue + ", new = " + newValue);
    }

}
