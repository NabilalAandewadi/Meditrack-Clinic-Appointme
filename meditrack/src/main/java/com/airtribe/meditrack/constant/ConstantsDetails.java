package com.airtribe.meditrack.constant;





public class ConstantsDetails {
    public static final double TAX_RATE = 0.1;
    public static final String CSV_FILE_PATH = "data/export.csv";

    // Static initialization block
    static {
        System.out.println("Constants initialized with tax rate: " + TAX_RATE);
    }
}