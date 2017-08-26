package com.example.lia.carrousel.UserManagementSystem;

import android.provider.BaseColumns;

/**
 * Created by Lia on 2016-06-24.
 */
public class Comments {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Comments() {
    }

    /* Inner class that defines the table contents */
    public static abstract class CommentsEntry implements BaseColumns {
        public static final String TABLE_NAME = "Comments";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_GARDIEN = "gardien";
        public static final String COLUMN_NAME_ID = "commentId";
    }
}
