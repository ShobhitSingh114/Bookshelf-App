package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.BookShelf
import com.example.bookshelfapp.model.ImageLinks

@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier.size(400.dp))
        is BooksUiState.Success -> BookGridScreen(books = booksUiState.books,
            modifier = modifier,
//            .padding(
//                start = dimensionResource(R.dimen.padding_medium),
//                top = dimensionResource(R.dimen.padding_medium),
//                end = dimensionResource(R.dimen.padding_medium)
//            ),
            contentPadding = contentPadding
        )
        else -> ErrorScreen(retryAction = retryAction, modifier)
    }
}

@Composable
fun BookGridScreen(
    books: BookShelf,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
//        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
            .padding(horizontal = 2.dp)
        ,
        contentPadding = contentPadding
    ) {
        items(
            items = books.items,
            key = {
                // For key it = books.items
                it.id
            }
        ){
            // For BooksCard it = books.items
            BooksCard(
                bookImage = it.volumeInfo.imageLinks,
                modifier = modifier
                    .padding(2.dp)
//                    .fillMaxWidth()
//                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun BooksCard(bookImage: ImageLinks, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 200.dp, height = 300.dp),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(bookImage.thumbnail.replace("http", "https"))
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.books_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun LoadingScreenPreview() {
//    LoadingScreen(modifier = Modifier.size(200.dp))
//}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun BookGridScreenPreview() {
////    val mockData = List(10) {
////
////    }
//}