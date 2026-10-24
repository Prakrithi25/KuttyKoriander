package com.example.kuttykoriander.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuttykoriander.data.UserPreferences

val BrandBg = Color(0xFFFAF9F6)
val BrandBlack = Color(0xFF1A1A1A)
val BrandDark = Color(0xFF2C2C2C)
val BrandGray = Color(0xFF666666)
val BrandLightGray = Color(0xFFEFEEEA)
val BrandBorder = Color(0xFFDCD6CD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(onRegisterSuccess: () -> Unit, onBrowseMenu: () -> Unit) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Kutty Koriander",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = BrandBlack
                        )
                        Text(
                            text = "YOUR NEIGHBORHOOD KITCHEN",
                            fontSize = 8.sp,
                            color = BrandGray,
                            letterSpacing = 1.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = BrandBlack)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ShoppingBag, contentDescription = "Cart", tint = BrandBlack)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BrandBg)
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBrowseMenu() },
                color = BrandLightGray,
                border = BorderStroke(1.dp, BrandBorder)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Just browsing? Explore the menu →",
                        color = BrandBlack,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BrandBg)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Subtitle & Header
            Text(
                text = "A SEAT AT OUR TABLE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BrandGray,
                letterSpacing = 1.5.sp
            )
            Text(
                text = "Small kitchen.\nBig welcome.",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = BrandBlack,
                lineHeight = 38.sp
            )
            Text(
                text = "Freshly cooked, just around the corner. Let's get to know the person behind the order.",
                fontSize = 14.sp,
                color = BrandGray
            )

            // Kitchen Peek Illustration Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .border(1.dp, BrandBorder, RoundedCornerShape(8.dp))
                    .background(Color.White, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "🍳 🍽️ 🌿", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "A little peek into our kitchen",
                        fontSize = 12.sp,
                        color = BrandGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Text(
                text = "Made with love. And a little coriander. *",
                fontSize = 11.sp,
                color = BrandGray,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Form section title
            Text(
                text = "Your first hello",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = BrandBlack
            )

            // First Name
            Column(verticalArrangement = Arrangement.spacedN(4.dp)) {
                Text(text = "First name *", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = { Text("e.g. Maya", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandBlack,
                        unfocusedBorderColor = BrandBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            // Last Name
            Column(verticalArrangement = Arrangement.spacedN(4.dp)) {
                Text(text = "Last name *", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { Text("e.g. Raman", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandBlack,
                        unfocusedBorderColor = BrandBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            // Email Address
            Column(verticalArrangement = Arrangement.spacedN(4.dp)) {
                Text(text = "Email address *", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("you@example.com", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandBlack,
                        unfocusedBorderColor = BrandBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            // Privacy note
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "🔒", fontSize = 12.sp)
                Text(
                    text = "For your receipts, not a recipe for spam.",
                    fontSize = 12.sp,
                    color = BrandGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Register Button
            Button(
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
                    } else {
                        userPreferences.firstName = firstName
                        userPreferences.lastName = lastName
                        userPreferences.email = email
                        userPreferences.isLoggedIn = true
                        Toast.makeText(context, "Welcome to Kutty Koriander!", Toast.LENGTH_SHORT).show()
                        onRegisterSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandBlack)
            ) {
                Text(
                    text = "Register · come on in →",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Terms disclaimer
            Text(
                text = "By joining, you agree to our Terms & Privacy Policy.",
                fontSize = 11.sp,
                color = BrandGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Sign in footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already part of the table? ", fontSize = 13.sp, color = BrandGray)
                Text(
                    text = "Sign in",
                    fontSize = 13.sp,
                    color = BrandBlack,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onBrowseMenu() }
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
