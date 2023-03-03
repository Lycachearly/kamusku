package com.chearly.kamusku.databases;

import android.provider.BaseColumns;

public class DatabaseContact {
    static String TABLE_KAMUS_NAME = "kamus";

    static  final class  KamusColumns implements BaseColumns{
        static  String KAMUS_TITLE = "title";
        static  String KAMUS_DESCRIPTION = "decription";
    }
}
