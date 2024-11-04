package com.example.graphql.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.graphql.domain.entity.SimpleCountry
import com.example.graphql.presentation.viewmodel.CountryViewModel

@Composable
fun CountryListScreen() {
    val viewModel: CountryViewModel = hiltViewModel()
    val state by viewModel.countries.collectAsState()
    var openDetailDialog by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(
            items = state
        ) { index, item ->
            ItemCountry(item,
                modifier = Modifier
                    .clickable {
                        openDetailDialog = item.code
                    })
            if (index < state.lastIndex) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        }
    }

    if (openDetailDialog.isNotEmpty()) {
        CountryDialog(openDetailDialog, viewModel) {
            openDetailDialog = ""
        }
    }
}

@Composable
private fun ItemCountry(item: SimpleCountry, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = item.emoji,
            modifier = Modifier.padding(end = 16.dp),
            fontSize = 20.sp
        )
        Column {
            Text(
                text = item.name,
                fontSize = 20.sp
            )
            Text(
                text = item.capital,
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CountryDialog(
    code: String,
    viewModel: CountryViewModel,
    onDismissRequest: (() -> Unit)
) {
    viewModel.selectCountry(code)
    val country by viewModel.countryDetail.collectAsState()

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = country?.emoji.orEmpty(),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = country?.name.orEmpty(),
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Continent: " + country?.continent,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Currency: " + country?.currency,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Capital: " + country?.capital,
                fontSize = 16.sp
            )
        }
    }
}
