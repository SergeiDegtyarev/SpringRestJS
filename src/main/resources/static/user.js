const userLayout = document.getElementById("user-info-body");
const URL = "http://localhost:8080/api/user/"


// Код для заполнения таблицы пользователей
const printUserInfo = user => {
    if (user) {
        userLayout.innerHTML = `
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>${user.stringRoles}</td>
            </tr>
        `
        document.getElementById("admin-link").hidden = !user.stringRoles.includes("ADMIN")
    }
}

// Get authorized user
function authorizedUser() {
    fetch(URL, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res => res.json())
        .then(data => printUserInfo(data));
}

authorizedUser();