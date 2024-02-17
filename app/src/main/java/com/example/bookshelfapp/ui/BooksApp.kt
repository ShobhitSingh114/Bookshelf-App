package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.screens.BooksViewModel
import com.example.bookshelfapp.ui.screens.HomeScreen

@Composable
fun BooksApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BooksAppTopBar() }
    ) {
        val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
        HomeScreen(
            booksViewModel.booksUiState,
            retryAction = booksViewModel::getBooks,
            contentPadding = it
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksAppTopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { 
            Text(text = stringResource(id = R.string.app_name), color = Color.White)
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
    )
}

@Preview(showSystemUi = true)
@Composable
fun BooksAppPreview() {
    BooksApp()
}