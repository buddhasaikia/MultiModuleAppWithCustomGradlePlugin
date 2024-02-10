import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val platformBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitTestExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val uiTestJunit = "androidx.compose.ui:ui-test-junit4"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val androidxAppCompat ="androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    fun DependencyHandler.compose() {
        implementation(composeUi)
        implementation(activityCompose)
        implementation(composeUiGraphics)
        implementation(composeUiToolingPreview)
        implementation(composeUiTooling)
        implementation(composeMaterial3)
        debugImplementation(composeUiToolingPreview)
    }

    fun DependencyHandler.myLibrary() {
        implementation(project(":mylibrary"))
    }
}