package com.cashkaro.cashkaro.adapter;

import java.io.Serializable;

public class SectionOrRow<H, V> implements Serializable {

    private V row;

    private H section;

    private boolean isRow;

    public SectionOrRow(H section, V row) {

        this.section = section;
        this.row = row;
        this.isRow = row != null;
    }

    public V getRow() {

        return row;
    }

    public H getSection() {

        return section;
    }

    public boolean isRow() {

        return isRow;
    }

    public void setRow(V row) {
        this.row = row;
    }

    public void setSection(H section) {
        this.section = section;
    }
}
