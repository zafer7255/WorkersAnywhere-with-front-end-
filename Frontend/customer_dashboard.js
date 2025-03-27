document.addEventListener("DOMContentLoaded", function () {
    const customerDetailsDiv = document.getElementById("customerDetails");
    const token = localStorage.getItem("token");
    // Redirect if not logged in
    if (!token) {
        alert("You are not logged in!");
        window.location.href = "login.html";
    }

    console.log(token)

    // Fetch customer details
    document.getElementById("getDetailBtn").addEventListener("click", async function () {
        try {
            const response = await fetch("http://localhost:8080/customer/detail", {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token"),
                    "Content-Type": "application/json"
                }
            });

            console.log(response)

            if (response.ok) {
                const customer = await response.json();
                customerDetailsDiv.innerHTML = `
                    <h2>Customer Details</h2>
                    <p><strong>Username:</strong> ${customer.username}</p>
                    <p><strong>Name:</strong> ${customer.name}</p>
                    <p><strong>Email:</strong> ${customer.email}</p>
                    <p><strong>Address:</strong> ${customer.address}</p>
                    <p><strong>State:</strong> ${customer.state}</p>
                `;
            } else {
                customerDetailsDiv.innerHTML = `<p>Error: Unable to fetch details</p>`;
            }
        } catch (error) {
            console.error("Error fetching customer details:", error);
            customerDetailsDiv.innerHTML = `<p>Something went wrong!</p>`;
        }
    });

    // Logout function
    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        window.location.href = "login.html";
    });
});
