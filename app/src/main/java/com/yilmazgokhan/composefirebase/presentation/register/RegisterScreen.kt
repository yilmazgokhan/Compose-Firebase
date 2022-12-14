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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "personIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.onTriggerEvent(RegisterViewEvent.SetName(it))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.username,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "personIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.onTriggerEvent(RegisterViewEvent.SetUsername(it))
                    },
                    label = { Text(text = "Username") },
                    placeholder = { Text(text = "Username") },
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.phone,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "phoneIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.onTriggerEvent(RegisterViewEvent.SetPhone(it))
                    },
                    label = { Text(text = "Phone number") },
                    placeholder = { Text(text = "Phone number") },
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "emailIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.onTriggerEvent(RegisterViewEvent.SetEmail(it))
                    },
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "Email address") },
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.address,
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "addressIcon"
                        )
                    },
                    onValueChange = {
                        viewModel.onTriggerEvent(RegisterViewEvent.SetAddress(it))
                    },
                    label = { Text(text = "Address") },
                    placeholder = { Text(text = "Address") },
                )
                Spacer(modifier = Modifier.height(4.dp))

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
fun TermsAndConditionsSelection(state: RegisterViewState, viewModel: RegisterViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.termsCheck,
            onCheckedChange = { viewModel.onTriggerEvent(RegisterViewEvent.SetTermsCheck(it)) }
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
            onCheckedChange = { viewModel.onTriggerEvent(RegisterViewEvent.SetNewsletterCheck(it)) }
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