plugins {
    id("java")
    id("java-library")
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.shadow)
    alias(libs.plugins.runpaper)
    alias(libs.plugins.resource.factory)
}

fun kotlin(s: String): String {
    return "org.jetbrains.kotlin.$s"
}

allprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin(kotlin("jvm"))
        plugin(kotlin("plugin.serialization"))
        plugin("com.github.johnrengelman.shadow")
    }

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
        compileOnly(rootProject.libs.bundles.ktor)
        compileOnly(rootProject.libs.paper.api)
        compileOnly(rootProject.libs.caffeine)
        compileOnly(rootProject.libs.catppuccin)
        compileOnly(rootProject.libs.dynmap)
        compileOnly(rootProject.libs.common.utils)
    }
}

dependencies {
    implementation(project(":api"))
}

bukkitPluginYaml {
    main = "ink.pmc.satellite.Satellite"
    author = "Nostal Yuu"
    apiVersion = "1.20"
    depend.add("common-utils")
    // depend.add("dynmap")
}
