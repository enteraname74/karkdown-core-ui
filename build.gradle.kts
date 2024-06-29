plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
}

description = "Core UI elements of the app"
group = "com.github.enteraname74.karkdowncoreui"
version = "0.1.0"

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}


kotlin {
    jvmToolchain(17)
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}