buildscript {
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.android.library") version "8.1.2" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}