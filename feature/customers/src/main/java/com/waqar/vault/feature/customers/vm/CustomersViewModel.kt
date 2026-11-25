package com.waqar.vault.feature.customers.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CustomersViewModel @Inject constructor(repo: CustomerRepository) : ViewModel() {
 val pager = repo.pagingCustomers().cachedIn(viewModelScope)
}