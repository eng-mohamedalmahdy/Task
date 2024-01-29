// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

buildscript {

    repositories {
        // other repositories...
        mavenCentral()
    }
    dependencies {
        val nav_version = "2.7.6"

        // other plugins...
        classpath (libs.hilt.android.gradle.plugin)
        classpath (libs.androidx.navigation.safe.args.gradle.plugin)
    }
}