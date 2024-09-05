package com.example.finalproject.ui.screens.auth_screen

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
fun AuthScreenContent(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterButtonClick: () -> Unit,
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is AuthSideEffect.NavigateToHome -> onLoginSuccess()
                is AuthSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }

                is AuthSideEffect.NavigateToRegister -> onRegisterButtonClick()
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
        val password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Column {
                TitleWithDescription(
                    titleText = stringResource(id = R.string.entrance),
                    descriptionText = stringResource(id = R.string.please_login)
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
                text = stringResource(id = R.string.login),
                buttonColor = Color.Black,
                textColor = Color.White,
                onClick = {
                    viewModel.login(phoneNumber.value, password.value)
                }
            )
            CustomButton(
                text = stringResource(id = R.string.register),
                onClick = {
                    viewModel.onRegisterClicked()
                }
            )
        }
    }
}

@Preview
@Composable
fun AuthScreenContentPreview() {
    AuthScreenContent(
        onRegisterButtonClick = {},
        onLoginSuccess = {},
        viewModel = hiltViewModel<AuthViewModel>()
    )
}