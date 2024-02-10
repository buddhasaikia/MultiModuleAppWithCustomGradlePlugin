import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class DemoPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                compileSdkVersion(ProjectConfig.compileSdk)
                defaultConfig {
                    targetSdk = ProjectConfig.targetSdk
                    minSdk = ProjectConfig.minSdk
                    versionCode = 1
                    versionName = "0.0.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                val proguardFile = "proguard-rules.pro"
                when (this) {
                    is LibraryExtension -> defaultConfig {
                        consumerProguardFiles(proguardFile)
                    }
                    is AppExtension -> buildTypes {
                        getByName("release") {
                            isMinifyEnabled = true
                            isShrinkResources = true
                            debuggable(false)
                            proguardFile(proguardFile)
                        }
                        getByName("debug") {
                            isMinifyEnabled = false
                            isShrinkResources = false
                            debuggable(true)
                            proguardFile(proguardFile)
                        }
                    }
                }
                composeOptions.kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtension
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = Versions.jvmTarget
                    }
                }
            }
        }
    }
}