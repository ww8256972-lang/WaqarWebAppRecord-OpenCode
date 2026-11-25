pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }
dependencyResolutionManagement { repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS); repositories { google(); mavenCentral() } }
rootProject.name = "WaqarWebAppRecord"
include(":app",":core:common",":core:ui",":core:database",":core:security",":core:network",":core:sync",":domain",":data")
include(":feature:auth",":feature:dashboard",":feature:customers",":feature:profile",":feature:communication",":feature:projects",":feature:domains",":feature:search",":feature:bulk",":feature:followups",":feature:attachments",":feature:sync",":feature:reports",":feature:qrocr",":feature:admin",":feature:theme",":feature:security",":feature:performance",":feature:updates",":feature:excelimport")