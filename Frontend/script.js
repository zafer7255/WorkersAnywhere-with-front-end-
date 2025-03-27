document.getElementById("customerRegisterForm").addEventListener("submit", async function(event) {
            event.preventDefault(); // Prevent default form submission

            // Collecting form data
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
                // Sending data to backend API
                const response = await fetch("localhost:8080/register/customer", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(formData)
                });

                const result = await response.json();
                alert(result.message); // Show response message

                if (response.ok) {
                    // Redirect to login page after successful registration
                    window.location.href = "login.html";
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Registration failed. Try again.");
            }
        });