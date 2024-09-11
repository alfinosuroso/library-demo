plugins {
    alias(libs.plugins.android.library)
    id("org.jetbrains.kotlin.android") version "2.0.20"
    id("maven-publish")
}

android {
    namespace = "io.github.alfinosuroso"
    compileSdk = 34
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    defaultConfig {
        minSdk = 24
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.android.volley:volley:1.2.1")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //    For retrofit
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")

    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "io.github.alfinosuroso"
                artifactId = "mylibrary"
                version = "1.0.0"

                pom {
                    packaging = "aar"
                    name.set("MyLibrary")
                    description.set("MyLibrary: Library for Android Application")
                    url.set("https://github.com/alfinosuroso/library-demo.git")
                    inceptionYear.set("2024")


                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("AlfinoSuroso")
                            name.set("Alfino Suroso")
                            email.set("alfinosuroso@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git@github.com:alfinosuroso/library-demo")
                        developerConnection.set("scm:git@github.com:alfinosuroso/library-demo.git")
                        url.set("https://github.com/alfinosuroso/library-demo.git")
                    }
                }
            }
        }
    }
}