package com.example.kuttykoriander.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuttykoriander.data.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogOut: () -> Unit,
    onTabSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    var firstName by remember { mutableStateOf(userPreferences.firstName) }
    var lastName by remember { mutableStateOf(userPreferences.lastName) }
    var email by remember { mutableStateOf(userPreferences.email) }
    var phone by remember { mutableStateOf(userPreferences.phone) }
    var spiceLevel by remember { mutableStateOf(userPreferences.spiceLevel) }
    var kitchenLoop by remember { mutableStateOf(userPreferences.kitchenLoop) }

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
                    IconButton(onClick = { onTabSelected("home") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = BrandBlack)
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
            NavigationBar(containerColor = BrandBg, tonalElevation = 8.dp) {
                NavigationBarItem(
                    selected = false,
                    onClick = { onTabSelected("home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onTabSelected("home") },
                    icon = { Icon(Icons.Default.RestaurantMenu, contentDescription = "Menu") },
                    label = { Text("Menu") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.ReceiptLong, contentDescription = "Orders") },
                    label = { Text("Orders") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = BrandBlack, selectedTextColor = BrandBlack)
                )
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
            // Header
            Text(
                text = "YOUR CORNER OF THE KITCHEN",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BrandGray,
                letterSpacing = 1.5.sp
            )
            Text(
                text = "Personal information",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = BrandBlack
            )
            Text(
                text = "The little details that make it yours.",
                fontSize = 14.sp,
                color = BrandGray
            )

            // Profile Avatar Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, BrandBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(BrandLightGray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = "Avatar", tint = BrandBlack, modifier = Modifier.size(28.dp))
                        }
                        Column {
                            Text(
                                text = "$firstName $lastName".ifBlank { "Krithya Prasanna" },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandBlack
                            )
                            Text(
                                text = "Officially one of the regulars.",
                                fontSize = 12.sp,
                                color = BrandGray
                            )
                        }
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = BrandBlack)
                    }
                }
            }

            // Form Fields
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // First Name
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "First name", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
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
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Last name", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
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
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Email address", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
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

                // Phone number
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Phone number", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BrandBlack)
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
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
            }

            // Spice Level Selector Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, BrandBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "How's your spice?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlack
                        )
                        Text(text = "🌶️", fontSize = 16.sp)
                    }
                    Text(
                        text = "Your usual heat. Change it with any order.",
                        fontSize = 12.sp,
                        color = BrandGray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Spice level segmented buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(BrandLightGray, RoundedCornerShape(8.dp))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val levels = listOf("Mild", "Medium", "Fiery")
                        levels.forEach { level ->
                            val isSelected = spiceLevel == level
                            Button(
                                onClick = { spiceLevel = level },
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) BrandBlack else Color.Transparent,
                                    contentColor = if (isSelected) Color.White else BrandGray
                                ),
                                elevation = if (isSelected) ButtonDefaults.buttonElevation(2.dp) else ButtonDefaults.buttonElevation(0.dp)
                            ) {
                                Text(
                                    text = if (isSelected) "✓ $level" else level,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Kitchen loop checkbox
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { kitchenLoop = !kitchenLoop }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Checkbox(
                    checked = kitchenLoop,
                    onCheckedChange = { kitchenLoop = it },
                    colors = CheckboxDefaults.colors(checkedColor = BrandBlack)
                )
                Column {
                    Text(
                        text = "Keep me in the kitchen loop",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = BrandBlack
                    )
                    Text(
                        text = "Seasonal specials and the occasional good thing.",
                        fontSize = 12.sp,
                        color = BrandGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Save changes button
            Button(
                onClick = {
                    userPreferences.firstName = firstName
                    userPreferences.lastName = lastName
                    userPreferences.email = email
                    userPreferences.phone = phone
                    userPreferences.spiceLevel = spiceLevel
                    userPreferences.kitchenLoop = kitchenLoop
                    Toast.makeText(context, "Changes saved successfully!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandBlack)
            ) {
                Text(
                    text = "Save changes",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Log out button
            OutlinedButton(
                onClick = {
                    userPreferences.clear()
                    onLogOut()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandBlack),
                border = BorderStroke(1.dp, BrandBorder)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Log out",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "🚪", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
