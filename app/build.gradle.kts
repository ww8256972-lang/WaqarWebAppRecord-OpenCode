plugins { id("com.android.application"); id("org.jetbrains.kotlin.android"); id("org.jetbrains.kotlin.plugin.compose"); id("com.google.dagger.hilt.android"); id("com.google.devtools.ksp"); id("com.google.gms.google-services") }
android {
 namespace="com.waqar.vault.app"; compileSdk=35
 defaultConfig { applicationId="com.waqar.vault"; minSdk=26; targetSdk=35; versionCode=1; versionName="1.0.0" }
 buildFeatures { compose=true }
 compileOptions { sourceCompatibility=JavaVersion.VERSION_17; targetCompatibility=JavaVersion.VERSION_17 }
 kotlinOptions { jvmTarget="17" }
}
dependencies {
 implementation(project(":core:common")); implementation(project(":core:ui")); implementation(project(":core:database")); implementation(project(":core:security")); implementation(project(":core:network")); implementation(project(":core:sync")); implementation(project(":domain")); implementation(project(":data"))
 for (m in listOf("auth","dashboard","customers","profile","communication","projects","domains","search","bulk","followups","attachments","sync","reports","qrocr","admin","theme","security","performance","updates","excelimport")) implementation(project(":feature:$m"))
 implementation("androidx.core:core-ktx:1.15.0"); implementation("androidx.activity:activity-compose:1.9.3")
 implementation("androidx.compose.ui:ui:1.7.5"); implementation("androidx.compose.material3:material3:1.3.1"); implementation("androidx.navigation:navigation-compose:2.8.4")
 implementation("androidx.hilt:hilt-navigation-compose:1.2.0"); implementation("com.google.dagger:hilt-android:2.52"); ksp("com.google.dagger:hilt-compiler:2.52")
 implementation("com.google.firebase:firebase-auth-ktx:23.1.0"); implementation("com.google.firebase:firebase-firestore-ktx:25.1.1"); implementation("com.google.firebase:firebase-storage-ktx:21.0.1"); implementation("com.google.firebase:firebase-appcheck-playintegrity:18.0.0")
}