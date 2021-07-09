package com.android.initializr.utils

import java.lang.StringBuilder

fun parsePackageNameToFolders(packageName: String): String {
    val builder = StringBuilder()
    val folders = packageName.split(".")

    for (folder in folders) {
        builder.append("/$folder")
    }
    return builder.toString()
}