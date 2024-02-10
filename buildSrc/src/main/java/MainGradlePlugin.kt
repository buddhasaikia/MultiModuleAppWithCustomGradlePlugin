import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.plugins.ExtensionContainerInternal
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugin(project)
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                compileSdkVersion(ProjectConfig.compileSdk)
                defaultConfig {
                    minSdk = ProjectConfig.minSdk
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                //composeOptions.kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtension
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = Versions.jvmTarget
                    }
                }
                //buildFeatures.apply { compose = true }
                val proguardFile = "proguard-rules.pro"
                when (this) {
                    is LibraryExtension -> defaultConfig {
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            proguardFile
                        )
                    }

                    is AppExtension -> buildTypes {
                        getByName("release") {
                            isMinifyEnabled = true
                            debuggable(false)
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                proguardFile
                            )
                        }
                        getByName("debug") {
                            isMinifyEnabled = false
                            debuggable(true)
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                proguardFile
                            )
                        }
                    }
                }
            }
        }
    }

    private fun applyPlugin(project: Project) {
        project.apply {
            plugin("org.jetbrains.kotlin.android")
        }
    }
}