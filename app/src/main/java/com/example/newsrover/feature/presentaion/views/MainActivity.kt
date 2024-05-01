package com.example.newsrover.feature.presentaion.views

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsrover.ui.theme.NewsRoverTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsrover.feature.data.sources.models.NewsArticle
import com.example.newsrover.feature.data.sources.remote.RetrofitClient
import com.example.newsrover.feature.domain.repositories.NewsRepositoryImpl
import com.example.newsrover.feature.domain.usecases.GetNewsUseCase
import com.example.newsrover.feature.presentaion.utils.Constants.URL
import com.example.newsrover.feature.presentaion.utils.Preferences
import com.example.newsrover.feature.presentaion.utils.ScreenNavigation
import com.example.newsrover.feature.presentaion.viewModels.HomeScreenViewModel
import com.example.newsrover.feature.presentaion.viewModels.HomeScreenViewModelFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsRoverTheme {

                val newsRepositoryImpl = NewsRepositoryImpl(RetrofitClient)
                val getNewsUseCase = GetNewsUseCase(newsRepositoryImpl)
                val homeScreenViewModel: HomeScreenViewModel =
                    viewModel(factory = HomeScreenViewModelFactory(getNewsUseCase))
                homeScreenViewModel.fetchNews()

                var navHostController: NavHostController = rememberNavController()
                val context = LocalContext.current

                val isLoading by homeScreenViewModel.isLoading.collectAsState()
                val newsList = homeScreenViewModel.news
                SetUpNavController(navHostController, Modifier, newsList, isLoading, context)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsRoverTheme {

    }
}

@Composable
fun SetUpNavController(
    navHostController: NavHostController,
    modifier: Modifier,
    newsList: SnapshotStateList<NewsArticle>,
    isLoading: Boolean,
    context: Context,
) {
    NavHost(navHostController, startDestination = ScreenNavigation.HOME.route) {
        composable(route = ScreenNavigation.HOME.route) {
            HomeScreen(modifier, newsList, isLoading, navHostController, context)
        }
        composable(route = ScreenNavigation.WEBCLIENT.route) { backStackEntry ->
            WebClientView(Preferences.getString(context, URL, "")!!)
        }
    }

}


@Composable
fun HomeTopBar() {
    Text(
        text = "Home",
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun WebClientView(url: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        WebViewComponent(url)
    }

}


@Composable
fun HomeScreen(
    modifier: Modifier,
    newsList: List<NewsArticle>,
    isLoading: Boolean,
    navHostController: NavHostController,
    context: Context,
) {
    Column {
        HomeTopBar()
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Loading...")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),
                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            ) {
                items(newsList) { model ->
                    ListRow(
                        model = model,
                        modifier = modifier,
                        index = newsList.indexOf(model),
                        navHostController,
                        context
                    )

                }
            }
        }


    }

}


@Composable
fun ListRow(
    model: NewsArticle,
    modifier: Modifier,
    index: Int,
    navController: NavHostController,
    context: Context,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(12.dp)
            .background(Color.White, RoundedCornerShape(18.dp))
            .clickable(onClick = {
                Preferences.saveString(context, URL, model.url)
                navController.navigate(ScreenNavigation.WEBCLIENT.route)
            })
    ) {
        NewsCardRow(model, modifier)
    }
}

@Composable
fun NewsCardRow(model: NewsArticle, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = model.author ?: "",
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = model.title,
                fontSize = 15.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = model.publishedAt,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = model.source,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(model.urlToImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

    }

}


@Composable
fun WebViewComponent(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

    }, update = {
        it.loadUrl(url)
    })


}
