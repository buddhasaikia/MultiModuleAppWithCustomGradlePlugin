plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("main-gradle-plugin"){
            id = "main-gradle-plugin"
            implementationClass = "MainGradlePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("com.android.tools.build:gradle:8.2.2")
}
