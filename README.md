# Waqar Web & App Record
Pure native Android (Kotlin + Jetpack Compose) enterprise customer/project record vault.
## Stack
Kotlin 2.1, Jetpack Compose, Material 3, Hilt, Room + SQLCipher 4, Firebase, C++20 NDK, WorkManager, Paging 3, FTS5.
## Build
./gradlew assembleDebug
## Notes
- Fresh install contains zero customer records (Zero-Dummy-Data policy).
- Super Admin username alias: waqar (password provisioned via Firebase Auth; never hardcoded).
- No payment/EMI/billing modules.
- Release builds via GitHub Actions (see .github/workflows/release-apk.yml).
See PRD_IMPLEMENTATION_MATRIX.md for requirement traceability.