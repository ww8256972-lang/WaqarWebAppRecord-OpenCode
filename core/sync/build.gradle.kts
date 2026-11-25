plugins { id("com.android.library"); id("org.jetbrains.kotlin.android") }
android { namespace="com.waqar.vault.sync"; compileSdk=35; defaultConfig{minSdk=26}; compileOptions{sourceCompatibility=JavaVersion.VERSION_17;targetCompatibility=JavaVersion.VERSION_17}; kotlinOptions{jvmTarget="17"} }
dependencies { implementation(project(":core:common")); implementation(project(":core:database")); implementation(project(":core:network")); implementation("androidx.work:work-runtime-ktx:2.10.0"); implementation("com.google.firebase:firebase-firestore-ktx:25.1.1") }
