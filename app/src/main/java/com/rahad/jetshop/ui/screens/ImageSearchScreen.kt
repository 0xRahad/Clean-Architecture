package com.rahad.jetshop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.rahad.jetshop.ui.viewmodel.ImageViewModel


@Composable
fun MainScreen(viewModel:ImageViewModel = hiltViewModel(), modifier: Modifier = Modifier){
    val state = viewModel.imageState.value
    val query = rememberSaveable { mutableStateOf("") }


    if (state.error.isNotBlank()){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(state.error)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Column {
            TextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    viewModel.updateQuery(it)
                },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = {
                        query.value = ""
                        viewModel.updateQuery("")
                    }) {
                        Icon(Icons.Rounded.Clear, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            state.data?.let {
                LazyColumn (
                    modifier = Modifier.padding(vertical = 10.dp)
                ){
                    items(it){
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        ){
                            SubcomposeAsyncImage(
                                model = it.imageUrl,
                                contentDescription = null,
                                loading = { Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ){
                                    CircularProgressIndicator(
                                        color = Color.DarkGray
                                    )
                                }},
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

    }



}

