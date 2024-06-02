plugins {
    id("java")
    id("java-library")
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.shadow)
    alias(libs.plugins.runpaper)
}

allprojects {
    group = "ink.pmc.satellite"
    version = "1.0.0"

    repositories {
        mavenCentral()
        mavenLocal()
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://maven.nostal.ink/repository/maven-public"))
    }

    dependencies {
        compileOnly(rootProject.libs.bundles.kotlin)
        compileOnly(rootProject.libs.bundles.nightconfig)
        compileOnly(rootProject.libs.bundles.cloud)
        compileOnly(rootProject.libs.bundles.mccoroutine)
        compileOnly(rootProject.libs.paper.api)
        compileOnly(rootProject.libs.caffeine)
        compileOnly(rootProject.libs.catppuccin)
        compileOnly(rootProject.libs.dynmap)
        compileOnly(rootProject.libs.common.utils)
    }
}
