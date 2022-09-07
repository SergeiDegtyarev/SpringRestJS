import {requestURL} from "./app.js";
import getElementsById from './app.js'
import {printUsersTable} from "./usersTable.js";


const deleteUserForm = document.querySelector('#modalDelete')
const deleteUserPrefixId = "delete-user-field-"

deleteUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();
    const userId = document.getElementById("delete-user-field-id").value;
    fetch(requestURL + userId, {
        method: 'DELETE'
    }).then(() => printUsersTable());
    $("#modalDelete").modal("hide")
})

document.addEventListener('click', e => {
    if (e.target.closest('#btn-delete-modal-call')) {
        getElementsById(e, deleteUserPrefixId);
        $("#modalDelete").modal("show");
    }
})