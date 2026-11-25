plugins { id("com.android.library"); id("org.jetbrains.kotlin.android") }
android { namespace="com.waqar.vault.data"; compileSdk=35; defaultConfig{minSdk=26}; compileOptions{sourceCompatibility=JavaVersion.VERSION_17;targetCompatibility=JavaVersion.VERSION_17}; kotlinOptions{jvmTarget="17"} }
dependencies { implementation(project(":core:common")); implementation(project(":core:database")); implementation(project(":core:network")); implementation(project(":core:sync")); implementation(project(":domain")); implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0") }
