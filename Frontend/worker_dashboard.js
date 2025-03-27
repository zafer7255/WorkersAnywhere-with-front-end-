document.addEventListener("DOMContentLoaded", function () {
    const workerDetailsDiv = document.getElementById("workerDetails");

    console.log("hello");
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
            const response = await fetch("http://localhost:8080/worker/detail", {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token"),
                    "Content-Type": "application/json"
                }
            });


            if (response.ok) {
                const worker = await response.json();
                workerDetailsDiv.innerHTML = `
                    <h2>Worker Details</h2>
                    <p><strong>Username:</strong> ${worker.username}</p>
                    <p><strong>Name:</strong> ${worker.name}</p>
                    <p><strong>Email:</strong> ${worker.email}</p>
                    <p><strong>Address:</strong> ${worker.address}</p>
                    <p><strong>AllTheStateWhereWork:</strong> ${worker.allTheStateWhereWork}</p>
                `;
            } else {
                customerDetailsDiv.innerHTML = `<p>Error: Unable to fetch details</p>`;
            }
        } catch (error) {
            console.error("Error fetching worker details:", error);
            customerDetailsDiv.innerHTML = `<p>Something went wrong!</p>`;
        }
    });

    // Logout function
    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token");
        window.location.href = "login.html";
    });
});
