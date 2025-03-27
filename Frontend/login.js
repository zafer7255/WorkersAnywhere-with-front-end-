document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent form reload
    let role = document.getElementById("role").value.toUpperCase();
    const loginData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        role: document.getElementById("role").value.toUpperCase() // Convert role to uppercase
    };

    console.log(loginData)

    try {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(loginData)
        });

        console.log(response)

        let result;
        const text = await response.text(); // Read response as text
        if (text) {
            result = text.startsWith("{") ? JSON.parse(text) : text; // Parse JSON only if valid
        }

        console.log(text)
        console.log(result)

        if (response.status === 202) {  // Login success
            localStorage.setItem("token", result); // Store JWT token
            alert("Login successful!");
            if(role === "WORKER"){
                window.location.href = "worker_dashboard.html";
            } else{
                window.location.href = "customer_dashboard.html";
            }
        } else {
            alert(result || "Login failed. Please check your credentials.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
    }
});



//document.addEventListener("DOMContentLoaded", function () {
////    const token = localStorage.getItem("token");
////
////    if (!token) {
////        alert("You are not logged in!");
////        window.location.href = "login.html";
////        return;
////    }
////
////    // Assuming the username is stored in the token, decode it (JWT decoding can be done in backend)
////    const username = "User"; // Replace this with actual username extraction logic
////
////    document.getElementById("username").textContent = username;
////    document.getElementById("username-display").textContent = username;
////
////    document.getElementById("logout").addEventListener("click", function () {
////        localStorage.removeItem("token");
////        alert("Logged out successfully!");
////        window.location.href = "login.html";
////    });
//});
