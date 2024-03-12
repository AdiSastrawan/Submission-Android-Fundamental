package com.adisastrawan.mysearchsubmission.ui.detail
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adisastrawan.mysearchsubmission.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun setup() {
        userRepository = mock(UserRepository::class.java)
        detailViewModel = DetailViewModel(userRepository)
    }

    @Test
    fun testUpdateToFavorite(){
        val username = "AdiSastrawan"
        `when`(userRepository.isUserFavorited(username)).thenReturn(false)
        detailViewModel.updateToFavorite(username)
        verify(userRepository).updateToFavorite(username)
    }
}