document.getElementById("workerRegisterForm").addEventListener("submit", async function(event) {
    event.preventDefault();

 
     const formData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        name: document.getElementById("name").value,
        address: document.getElementById("address").value,
        phoneNo: document.getElementById("phoneNo").value,
        work: document.getElementById("work").value,
        allTheStateWhereWork: document.getElementById("allTheStateWhereWork").value,
        chargePerDay: document.getElementById("chargePerDay").value,
        overAllRating: document.getElementById("overAllRating").value,
        email: document.getElementById("email").value
     };

     try 
     {
         const response = await fetch("http://localhost:8080/register/customer",{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
         });

         let result;
         const text = await response.text();

         if(text){
            result = JSON.parse(text);
         }
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