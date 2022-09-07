import {requestURL} from "./app.js";

const allUsersTable = document.getElementById("all users table-body");

// Код для заполнения таблицы пользователей
let renderUsersTable = data => {
    console.log(data)
    if (data.length > 0) {
        let tableHTML = "";
        data.forEach(user => {
            tableHTML += `
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>${user.stringRoles}</td>
                <td>
                    <button type="button" class="btn btn-info" id="btn-edit-modal-call" data-toggle="modal" 
                        data-target="modal-edit" data-id="${user.id}">Edit
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" id="btn-delete-modal-call" data-toggle="modal"
                        data-target="modal-delete" data-id="${user.id}">Delete
                    </button>
                </td>
            <tr>`
        })
        allUsersTable.innerHTML = tableHTML;
    }
}

export function printUsersTable() {
    fetch(requestURL, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res => res.json())
        .then(data => renderUsersTable(data));
}

printUsersTable();