plugins {
    id("com.android.library")
    id("main-gradle-plugin")
}

android {
    namespace = "com.example.mylibrary"
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.androidxAppCompat)
    implementation(Dependencies.material)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitTestExt)
    androidTestImplementation(Dependencies.espressoCore)
}