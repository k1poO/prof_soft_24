package com.example.finalproject.ui.components

import android.text.BoringLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.navigation.NavigationItem
import com.example.finalproject.ui.theme.LocalCustomColors

@Composable
fun BottomBar(
    items: List<NavigationItem>,
    selected: (item: NavigationItem) -> Boolean,
    currentRoute: String? = null,
    onClick: (item: NavigationItem) -> Unit = {}
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalCustomColors.current.GrayT33)
            .height(1.dp)
    )
    Row(
        Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {

        items.forEach { item ->
            val selectedItem = selected(item)
            BottomIcon(item = item, isSelected = selectedItem) {
                    onClick(item)
            }
        }
    }

}

@Composable
fun BottomIcon(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) {
        LocalCustomColors.current.ItemChoosenGray
    } else {
        Color.Transparent
    }
    Box(
        modifier = Modifier
            .size(height = 40.dp, width = 62.dp)
            .background(
                color,
                RoundedCornerShape(33.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(id = item.icon), contentDescription = null)
    }
}
