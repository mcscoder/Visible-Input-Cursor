package com.example.visibleinputcursortest.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.visibleinputcursortest.ui.theme.VisibleInputCursorTestTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainApplication() {
    VisibleInputCursorTestTheme(darkTheme = false) {
        val bringIntoViewRequester1 = remember { BringIntoViewRequester() }
        val bringIntoViewRequester2 = remember { BringIntoViewRequester() }
        val coroutineScope = rememberCoroutineScope()

        var textFieldValue1 by remember { mutableStateOf(TextFieldValue()) }
        var textFieldValue2 by remember { mutableStateOf(TextFieldValue()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .border(1.dp, Color.Red, RectangleShape)
                .verticalScroll(rememberScrollState())
        ) {
            Surface(modifier = Modifier.border(1.dp, Color.Black, RectangleShape)) {
                BasicTextField(
                    value = textFieldValue1,
                    onValueChange = { textFieldValue1 = it },
                    textStyle = TextStyle(fontSize = 30.sp),
                    onTextLayout = {
                        var cursorRect = it.getCursorRect(textFieldValue1.selection.start)
                        coroutineScope.launch {
                            bringIntoViewRequester1.bringIntoView(cursorRect)
                        }
                    },
                    modifier = Modifier
                        .bringIntoViewRequester(bringIntoViewRequester1)
                        .fillMaxWidth()
                )
            }
            Surface(modifier = Modifier.border(1.dp, Color.Black, RectangleShape)) {
                BasicTextField(
                    value = textFieldValue2,
                    onValueChange = { textFieldValue2 = it },
                    textStyle = TextStyle(fontSize = 30.sp),
                    onTextLayout = {
                        var cursorRect = it.getCursorRect(textFieldValue2.selection.start)
                        coroutineScope.launch {
                            bringIntoViewRequester2.bringIntoView(cursorRect)
                        }
                    },
                    modifier = Modifier
                        .bringIntoViewRequester(bringIntoViewRequester2)
                        .fillMaxWidth()
                )
            }
        }
    }
}
