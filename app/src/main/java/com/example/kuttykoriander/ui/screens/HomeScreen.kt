package com.example.kuttykoriander.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kuttykoriander.data.MenuItem
import com.example.kuttykoriander.data.fetchMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onTabSelected: (String) -> Unit
) {
    var menuItems by remember { mutableStateOf<List<MenuItem>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("all") }
    var deliveryMode by remember { mutableStateOf(true) } // true = Delivery, false = Pickup

    LaunchedEffect(Unit) {
        val items = withContext(Dispatchers.IO) {
            fetchMenu()
        }
        menuItems = items.ifEmpty {
            // Fallback curated Kutty Koriander menu items if network fails
            listOf(
                MenuItem(1, "Coriander chicken bowl", "Tender grilled chicken, herb rice, crunchy slaw & a squeeze of lime.", "249", "chicken.jpg", "mains"),
                MenuItem(2, "Paneer pepper fry", "Golden paneer tossed with cracked pepper, curry leaves & onions.", "219", "paneer.jpg", "mains"),
                MenuItem(3, "Homestyle dal & rice", "Slow-simmered yellow lentils, steamed rice & a little ghee on top.", "179", "dal.jpg", "mains")
            )
        }
    }

    val filteredItems = menuItems.filter { item ->
        val matchesSearch = item.title.contains(searchQuery, ignoreCase = true) ||
                item.description.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == "all" || item.category.equals(selectedCategory, ignoreCase = true)
        matchesSearch && matchesCategory
    }

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
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = BrandBlack)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BrandBg)
            )
        },
        bottomBar = {
            Column {
                // Floating Basket Bar
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = BrandBlack,
                    shadowElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "2 items · ₹468",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Plus taxes & delivery",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { }
                        ) {
                            Text(
                                text = "View basket",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "→", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }

                // Bottom Navigation Bar
                NavigationBar(containerColor = BrandBg, tonalElevation = 8.dp) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { onTabSelected("home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = BrandBlack, selectedTextColor = BrandBlack)
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
                        selected = false,
                        onClick = onProfileClick,
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") }
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BrandBg),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Location Header Bar
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = BrandLightGray
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "CHENNAI · YOUR LOCAL TABLE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandGray,
                            letterSpacing = 1.sp
                        )
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(Color(0xFF2E7D32), RoundedCornerShape(50))
                            )
                            Text(
                                text = "OPEN NOW",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        }
                    }
                }
            }

            // Hero Card Section
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, BrandBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Kutty\nKoriander",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandBlack,
                                lineHeight = 28.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "A little neighborhood kitchen serving big-hearted, homestyle food. Made fresh, made for you.",
                                fontSize = 12.sp,
                                color = BrandGray,
                                lineHeight = 16.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🍲", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Small batches. Happy bellies.",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = BrandBlack
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        // Kitchen photo placeholder box
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .border(1.dp, BrandBorder, RoundedCornerShape(8.dp))
                                .background(BrandLightGray, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "🍲", fontSize = 28.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Kitchen photo",
                                    fontSize = 9.sp,
                                    color = BrandGray
                                )
                            }
                        }
                    }
                }
            }

            // Delivery / Pickup Segmented Tabs & Address
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(BrandLightGray, RoundedCornerShape(8.dp))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { deliveryMode = true },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (deliveryMode) Color.White else Color.Transparent,
                                contentColor = if (deliveryMode) BrandBlack else BrandGray
                            ),
                            elevation = if (deliveryMode) ButtonDefaults.buttonElevation(2.dp) else ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text(text = "Delivery · 30–40 min", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { deliveryMode = false },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!deliveryMode) Color.White else Color.Transparent,
                                contentColor = if (!deliveryMode) BrandBlack else BrandGray
                            ),
                            elevation = if (!deliveryMode) ButtonDefaults.buttonElevation(2.dp) else ButtonDefaults.buttonElevation(0.dp)
                        ) {
                            Text(text = "Pickup · 15 min", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(text = "📍", fontSize = 12.sp)
                            Text(
                                text = "Deliver to Home · Adyar, Chennai",
                                fontSize = 12.sp,
                                color = BrandGray
                            )
                        }
                        Text(
                            text = "Change",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlack,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { }
                        )
                    }
                }
            }

            // Search Bar
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("What are you craving today?", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = BrandGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandBlack,
                        unfocusedBorderColor = BrandBorder,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    singleLine = true
                )
            }

            // Fresh from our kitchen section & Category Chips
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Fresh from our kitchen 🍲",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlack
                        )
                    }

                    // Category Filter Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        val categories = listOf("all", "mains", "sides", "sweets", "drinks")
                        items(categories) { category ->
                            val isSelected = selectedCategory == category
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedCategory = category },
                                label = { Text(category.replaceFirstChar { it.uppercase() }) },
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = BrandLightGray,
                                    labelColor = BrandBlack,
                                    selectedContainerColor = BrandBlack,
                                    selectedLabelColor = Color.White
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true,
                                    selected = isSelected,
                                    borderColor = BrandBorder,
                                    selectedBorderColor = BrandBlack
                                )
                            )
                        }
                    }
                }
            }

            // Chef's Little Nudge Banner
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, BrandBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = "👨‍🍳", fontSize = 20.sp)
                        Column {
                            Text(
                                text = "CHEF'S LITTLE NUDGE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandGray
                            )
                            Text(
                                text = "Try our coriander chicken bowl today.",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = BrandBlack
                            )
                        }
                    }
                }
            }

            // Menu Header & Dish Items List
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "THE LUNCH LINE-UP",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandGray,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "${filteredItems.size} dishes",
                        fontSize = 11.sp,
                        color = BrandGray
                    )
                }
            }

            items(filteredItems) { item ->
                KuttyKorianderMenuItemCard(item = item)
            }

            // Footer allergy note
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A little note: tell us about allergies at checkout.",
                        fontSize = 11.sp,
                        color = BrandGray,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun KuttyKorianderMenuItemCard(item: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BrandBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            ) {
                // Tag badge if available
                if (item.id == 1) {
                    Text(
                        text = "HOUSE FAVOURITE",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD32F2F),
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                } else if (item.id == 2) {
                    Text(
                        text = "VEGETARIAN",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32),
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                } else {
                    Text(
                        text = "COMFORT FOOD",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2),
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }

                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = BrandBlack
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    fontSize = 12.sp,
                    color = BrandGray,
                    maxLines = 2,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "₹${item.price.toIntOrNull()?.let { it * 25 } ?: 249}", // converted to Indian Rupees to match mockup
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = BrandBlack
                    )

                    Button(
                        onClick = { },
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BrandLightGray),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        border = BorderStroke(1.dp, BrandBorder)
                    ) {
                        Text(
                            text = "ADD +",
                            color = BrandBlack,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Dish Image Box
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, BrandBorder, RoundedCornerShape(8.dp))
                    .background(BrandLightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "🍲", fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Dish Image",
                        fontSize = 8.sp,
                        color = BrandGray
                    )
                }
            }
        }
    }
}
