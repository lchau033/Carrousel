package com.example.lia.carrousel.UserManagementSystem;
import android.provider.BaseColumns;

public class ParentGardien {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ParentGardien() {
    }

    /* Inner class that defines the table contents */
    public static abstract class ParentGardienEntry implements BaseColumns {
        public static final String TABLE_NAME = "PARENT_GARDIEN";
        public static final String COLUMN_NAME_GARDIEN = "GARDIEN";
        public static final String COLUMN_NAME_PARENT = "PARENT";
    }
}