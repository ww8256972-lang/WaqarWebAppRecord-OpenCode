package com.waqar.vault.app
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.waqar.vault.feature.admin.ui.AdminScreen
import com.waqar.vault.feature.attachments.ui.AttachmentsScreen
import com.waqar.vault.feature.auth.ui.AuthScreen
import com.waqar.vault.feature.bulk.ui.BulkScreen
import com.waqar.vault.feature.communication.ui.CommunicationScreen
import com.waqar.vault.feature.customers.ui.CustomersScreen
import com.waqar.vault.feature.dashboard.ui.DashboardScreen
import com.waqar.vault.feature.domains.ui.DomainsScreen
import com.waqar.vault.feature.excelimport.ui.ExcelImportScreen
import com.waqar.vault.feature.followups.ui.FollowupsScreen
import com.waqar.vault.feature.performance.ui.PerformanceScreen
import com.waqar.vault.feature.profile.ui.ProfileScreen
import com.waqar.vault.feature.projects.ui.ProjectsScreen
import com.waqar.vault.feature.qrocr.ui.QrocrScreen
import com.waqar.vault.feature.reports.ui.ReportsScreen
import com.waqar.vault.feature.search.ui.SearchScreen
import com.waqar.vault.feature.security.ui.SecurityScreen
import com.waqar.vault.feature.sync.ui.SyncScreen
import com.waqar.vault.feature.theme.ui.ThemeScreen
import com.waqar.vault.feature.updates.ui.UpdatesScreen
@Composable
fun AppNavGraph(nav: NavHostController) {
 NavHost(navController = nav, startDestination = "login") {
  composable("login") { AuthScreen(onSuccess = { nav.navigate("dashboard") { popUpTo("login") { inclusive = true } } }) }
  composable("dashboard") { DashboardScreen(onOpenExcel = { nav.navigate("excel") }) }
  composable("excel") { ExcelImportScreen() }
  composable("customers") { CustomersScreen() }
  composable("projects") { ProjectsScreen() }
  composable("domains") { DomainsScreen() }
  composable("followups") { FollowupsScreen() }
  composable("reports") { ReportsScreen() }
  composable("search") { SearchScreen() }
  composable("bulk") { BulkScreen() }
  composable("communication") { CommunicationScreen() }
  composable("attachments") { AttachmentsScreen() }
  composable("sync") { SyncScreen() }
  composable("qrocr") { QrocrScreen() }
  composable("admin") { AdminScreen() }
  composable("theme") { ThemeScreen() }
  composable("security") { SecurityScreen() }
  composable("performance") { PerformanceScreen() }
  composable("updates") { UpdatesScreen() }
  composable("profile") { ProfileScreen() }
 }
}