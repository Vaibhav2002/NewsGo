ext{
    set("hiltVersion", "2.44")
    set("lifecycleVersion", "2.5.1")
    set("composeVersion", "1.3.1")
    set("navVersion", "2.5.3")
    set("kotlinXSerialization", "1.4.1")
}

plugins {
    val kotlinVersion = "1.7.20"

    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version(kotlinVersion).apply(false)
    kotlin("multiplatform").version(kotlinVersion).apply(false)
    kotlin("plugin.serialization").version(kotlinVersion)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
