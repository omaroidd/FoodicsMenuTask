# Foodics Android Coding Challenge

This is the Android application developed for the Foodics coding challenge. The app allows users to browse categories, view products, and add products to an order.

## Features
- Browse categories and products
- Search products by name
- Add products to order and view total price/quantity
- Order data resets after viewing the order
- Integration with mock APIs (Mockaroo) using KTOR
- Data stored locally with Room
- Jetpack Compose UI with Material 3
- MVVM architecture
- KOIN for dependency injection
- Responsive design for multiple phone screen sizes (portrait mode only)

## Technologies
- Kotlin
- KTOR
- Room
- Jetpack Compose
- Material 3
- KOIN (Dependency Injection)
- MVVM Architecture

## API Mock Data
### Categories
```json

[
  { "id": "1", "name": "Breakfast" },
  { "id": "2", "name": "Lunch" }
]
````
### Products
```json
[
    {
        "id": 1,
        "name": "Coconut Curry Lentils",
        "description": "Lentils cooked in a coconut curry for a hearty meal.",
        "image": "https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg",
        "price": 5.79,
        "category": {
            "id": 2,
            "name": "Lunch"
        }
    },
    {
        "id": 2,
        "name": "Almond Flour Brownies",
        "description": "Deliciously rich brownies made with almond flour.",
        "image": "https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg",
        "price": 5.99,
        "category": {
            "id": 2,
            "name": "Lunch"
        }
    }
 ]
````
## Installation
1. Clone the repository:
````bash
git clone https://github.com/omaroidd/FoodicsMenuTask.git
````
2. Open in Android Studio
3. Build and run the application
   
## Screenshots
<img width="1527" height="3289" alt="Screenshot_20251211_211143" src="https://github.com/user-attachments/assets/3cdc6956-c68b-402e-9a3d-623a0b76acba" />
<br><br><br>
<img width="1527" height="3289" alt="Screenshot_20251211_211203" src="https://github.com/user-attachments/assets/bd3fed90-b082-4a7a-9caf-378a55694770" />


## License
MIT License
