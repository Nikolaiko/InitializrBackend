package com.android.initializr.builders.gradleScriptBuilders.defaultValues

val testDependencies = listOf<String>(
        "testImplementation \"junit:junit:4.12\"",
        "androidTestImplementation \"androidx.test.ext:junit:1.1.1\"",
        "androidTestImplementation \"androidx.test.espresso:espresso-core:3.2.0\""
)

val systemDependencies = listOf<String>(
        "implementation fileTree(dir: \"libs\", include: [\"*.jar\"])",
        "implementation \"org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION\""
)

val coreDependencies = listOf<String>(
        "implementation \"androidx.core:core-ktx:1.3.0\"",
        "implementation \"androidx.appcompat:appcompat:1.1.0\"",
        "implementation \"androidx.constraintlayout:constraintlayout:1.1.3\""
)
