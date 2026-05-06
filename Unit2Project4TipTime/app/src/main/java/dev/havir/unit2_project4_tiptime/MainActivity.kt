package dev.havir.unit2_project4_tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havir.unit2_project4_tiptime.ui.theme.Unit2Project4TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit2Project4TipTimeTheme {
                TipTimeApp()
            }
        }
    }
}

@Composable
fun TipTimeApp() {
    var billAmount by rememberSaveable { mutableStateOf("") }
    var tipPercentage by rememberSaveable { mutableStateOf("") }
    var roundUp by rememberSaveable { mutableStateOf(false) }

    Surface(Modifier.fillMaxSize()) {
        TipTimeLayout(
            billAmountInput = billAmount,
            onBillAmountChange = { billAmount = it },
            tipPercentageInput = tipPercentage,
            onTipPercentageChange = { tipPercentage = it },
            roundUp = roundUp,
            onRoundTipChange = { roundUp = it },
        )
    }
}

@Composable
private fun TipTimeLayout(
    billAmountInput: String,
    onBillAmountChange: (String) -> Unit,
    tipPercentageInput: String,
    onTipPercentageChange: (String) -> Unit,
    roundUp: Boolean,
    onRoundTipChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val billAmount = billAmountInput.toDoubleOrNull() ?: 0.0
    val tipPercentage = tipPercentageInput.toDoubleOrNull() ?: 0.0
    val tipAmount = calculateTip(
        billAmount = billAmount,
        tipPercentage = tipPercentage,
        roundUp = roundUp
    )
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 32.dp,
            alignment = Alignment.CenterVertically,
        )
    ) {
        BillAmountRow(billAmountInput, onBillAmountChange)
        EditNumberField(
            value = tipPercentageInput,
            onValueChange = onTipPercentageChange,
            label = R.string.how_was_the_service,
            leadingIcon = R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
            )
        )
        RoundUpTipRow(roundTip = roundUp, onRoundTipChange = onRoundTipChange)
        Text(
            stringResource(R.string.tip_amount, tipAmount),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun BillAmountRow(
    billAmountInput: String,
    onBillAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(R.string.calculate_tip),
            modifier = Modifier.align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = billAmountInput,
            onValueChange = onBillAmountChange,
            label = R.string.bill_amount,
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next
            )
        )
    }
}

@Composable
private fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(leadingIcon), null) },
        keyboardOptions = keyboardOptions,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun RoundUpTipRow(
    roundTip: Boolean, onRoundTipChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(stringResource(R.string.round_up_tip))
        Switch(
            checked = roundTip,
            onCheckedChange = onRoundTipChange,
            modifier = Modifier.testTag("ROUNDUP_SWITCH")
        )
    }
}

@VisibleForTesting
internal fun calculateTip(
    billAmount: Double, tipPercentage: Double = 15.00, roundUp: Boolean
): String {
    val tip = (billAmount * tipPercentage) / 100
    val roundedTip = kotlin.math.ceil(tip).takeIf { roundUp }
    return NumberFormat.getCurrencyInstance().format(roundedTip ?: tip)
}

@Preview(showBackground = true)
@Composable
private fun TipTimePreview() {
    Unit2Project4TipTimeTheme {
        TipTimeApp()
    }
}
