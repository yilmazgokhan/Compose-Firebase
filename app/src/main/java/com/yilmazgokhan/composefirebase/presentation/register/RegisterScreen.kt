package com.yilmazgokhan.composefirebase.presentation.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.ui.component.ButtonWithBorder
import com.yilmazgokhan.composefirebase.ui.component.DefaultTextField
import com.yilmazgokhan.composefirebase.ui.component.DefaultToolbar
import com.yilmazgokhan.composefirebase.ui.component.TextSecondary
import com.yilmazgokhan.composefirebase.ui.theme.Purple200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        DefaultToolbar(
            title = "Register",
            onBackPressClick = navigateToBack
        )
    }, bottomBar = { BottomBar(viewModel) }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "personIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetName(it))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                )
                Spacer(modifier = Modifier.height(4.dp))

                DefaultTextField(
                    modifier = Modifier.padding(vertical = 4.dp),
                    value = state.username,
                    iconVector = Icons.Default.Person,
                    iconText = "personIcon",
                    onValueChange = {
                        viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetUsername(it))
                    },
                    label = "Username",
                    placeholder = "Username",
                )

                DefaultTextField(
                    modifier = Modifier.padding(vertical = 4.dp),
                    value = state.phone,
                    iconVector = Icons.Default.Phone,
                    iconText = "phoneIcon",
                    onValueChange = {
                        viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetPhone(it))
                    },
                    label = "Phone",
                    placeholder = "Phone",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                )

                DefaultTextField(
                    modifier = Modifier.padding(vertical = 4.dp),
                    value = state.email,
                    iconVector = Icons.Default.Email,
                    iconText = "emailIcon",
                    onValueChange = {
                        viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetEmail(it))
                    },
                    label = "Email",
                    placeholder = "Email",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )

                DefaultTextField(
                    modifier = Modifier.padding(vertical = 4.dp),
                    value = state.address,
                    iconVector = Icons.Default.Home,
                    iconText = "addressIcon",
                    onValueChange = {
                        viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetAddress(it))
                    },
                    label = "Address",
                    placeholder = "Address",
                )

                GenderSelection()
                Spacer(modifier = Modifier.height(12.dp))

                TermsAndConditionsSelection(state, viewModel)
            }
        }
    }
}

@Composable
fun GenderSelection() {
    val radioOptions = listOf("Male", "Female")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        radioOptions.forEach { text ->
            Row(modifier = Modifier
                .selectable(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    modifier = Modifier.padding(2.dp),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                Text(
                    text = text,
                )
            }
        }
    }
}

@Composable
fun TermsAndConditionsSelection(
    state: RegisterViewModel.ViewState,
    viewModel: RegisterViewModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.termsCheck,
            onCheckedChange = {
                viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetTermsCheck(it))
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        TextSecondary(text = "I agree with terms and conditions")
    }
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.newsletterCheck,
            onCheckedChange = {
                viewModel.triggerEvent(RegisterViewModel.ViewEvent.SetNewsletterCheck(it))
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        TextSecondary(text = "I want to receive the newsletter")
    }
}

@Composable
fun BottomBar(viewModel: RegisterViewModel) {
    Box(modifier = Modifier.padding(4.dp)) {
        ButtonWithBorder(
            text = "Register",
            textColor = Color.White,
            borderColor = Purple200,
            backgroundColor = Purple200,
            click = { viewModel.temp() }
        )
    }
}