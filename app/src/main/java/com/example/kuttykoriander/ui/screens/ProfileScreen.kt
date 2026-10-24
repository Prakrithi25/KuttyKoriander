package com.example.kuttykoriander.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuttykoriander.data.UserPreferences

@Composable
fun ProfileScreen(onLogOut: () -> Unit) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    val firstName = userPreferences.firstName
    val lastName = userPreferences.lastName
    val email = userPreferences.email

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "LITTLE LEMON",
                color = PrimaryGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }

        // Header Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(PrimaryGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profile information",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // User info display
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "First name", fontSize = 12.sp, color = Color.Gray)
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color.LightGrade if false else Color.LightGray
                    )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "Last name", fontSize = 12.sp, color = Color.Gray)
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "Email", fontSize = 12.sp, color = Color.Gray)
                OutlinedTextField(
                    value = email,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Log out button
        Button(
            onClick = {
                userPreferences.clear()
                onLogOut()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow)
        ) {
            Text(
                text = "Log out",
                color = TextDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
