package dev.havir.unit3_project5_thirtydays

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit3_project5_thirtydays.data.Tip
import dev.havir.unit3_project5_thirtydays.data.TipsDataSource
import dev.havir.unit3_project5_thirtydays.ui.theme.Unit3Project5ThirtyDaysTheme

@Composable
fun ThirtyDaysList(
    tips: List<Tip>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
        modifier = modifier.testTag("THIRTY_DAYS_LIST")
    ) {
        itemsIndexed(tips) { index, tip ->
            ThirtyDaysItem(
                tip = tip,
                dayNumber = index + 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ThirtyDaysItem(
    tip: Tip, dayNumber: Int, modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ElevatedCard(
        modifier = modifier
            .clickable(onClick = { expanded = !expanded })
            .testTag("THIRTY_DAYS_CARD")
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium,
                    )
                )
        ) {
            Text(
                stringResource(R.string.day_number, dayNumber),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                stringResource(tip.titleStringResId),
                style = MaterialTheme.typography.titleLarge
            )
            Image(
                painterResource(tip.imageDrawableResId),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(tip.titleStringResId),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
            )
            if (expanded) Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    stringResource(tip.descriptionStringResId),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    stringResource(tip.authorStringResId),
                    style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic)
                )
            }
        }
    }
}

@Preview
@Composable
fun ThirtyDaysListPreview() {
    val tips = TipsDataSource.tips
    Unit3Project5ThirtyDaysTheme {
        ThirtyDaysList(tips = tips, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun ThirtyDaysItemPreview() {
    val tip = TipsDataSource.tips.first()
    Unit3Project5ThirtyDaysTheme {
        ThirtyDaysItem(
            tip = tip, dayNumber = 1, modifier = Modifier.fillMaxWidth()
        )
    }
}
