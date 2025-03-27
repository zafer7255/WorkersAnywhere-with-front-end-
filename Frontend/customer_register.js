document.getElementById("customerRegisterForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const formData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        address: document.getElementById("address").value,
        pinCode: document.getElementById("pinCode").value,
        phoneNo: document.getElementById("phoneNo").value,
        state: document.getElementById("state").value
    };

    try {
        const response = await fetch("http://localhost:8080/register/customer", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });
         console.log(response)
        // ✅ Check if response has content before parsing
        let result;
        const text = await response.text(); // Read response as text
        if (text) {
            result = JSON.parse(text); // Convert to JSON only if text exists
        }
        console.log(result)

        if (response.ok) {
            alert(result?.message || "Verify Your Email");
            window.location.href = "login.html"; // Redirect after success
        } else {
            alert(result?.message || "Registration failed!");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
    }
});
