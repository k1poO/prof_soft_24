package com.example.finalproject.ui.screens.register_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.CustomButton
import com.example.finalproject.ui.components.CustomTextField
import com.example.finalproject.ui.components.TitleWithDescription
import com.example.finalproject.ui.theme.LocalCustomColors

@Composable
fun RegisterScreenContent(
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit,
    onLoginButtonClick: () -> Unit,
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is RegisterSideEffect.NavigateToHome -> onRegisterSuccess()
                is RegisterSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }

                is RegisterSideEffect.NavigateToAuth -> onLoginButtonClick()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColors.current.MainYellow)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val phoneNumber = remember { mutableStateOf("") }
        val name = remember { mutableStateOf("") }
        val surname = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Column {
                TitleWithDescription(
                    titleText = stringResource(id = R.string.register),
                    descriptionText = stringResource(id = R.string.please_enter_data)
                )
                CustomTextField(
                    paddingValues = PaddingValues(vertical = 12.dp),
                    text = name,
                    hint = stringResource(id = R.string.name)
                )
                CustomTextField(
                    text = surname,
                    hint = stringResource(id = R.string.surname)
                )
                CustomTextField(
                    paddingValues = PaddingValues(vertical = 12.dp),
                    text = phoneNumber,
                    hint = stringResource(id = R.string.phone_number)
                )
                CustomTextField(
                    text = password,
                    hint = stringResource(id = R.string.password)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(bottom = 6.dp)
        ) {
            CustomButton(
                isLoading = state.isLoading,
                text = stringResource(id = R.string.register),
                buttonColor = Color.Black,
                textColor = Color.White,
                onClick = {
                    viewModel.register(
                        phoneNumber = phoneNumber.value,
                        name = name.value,
                        surname = surname.value,
                        password = password.value,
                    )
                }
            )
            CustomButton(
                text = stringResource(id = R.string.login_with_number),
                onClick = {
                    viewModel.onLoginClicked()
                }
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenContentPreview() {
    RegisterScreenContent(
        onLoginButtonClick = {},
        onRegisterSuccess = {}
    )
}