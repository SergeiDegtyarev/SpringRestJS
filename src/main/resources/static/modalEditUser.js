import getElementsById, {requestURL, getRolesFromForm} from './app.js'
import {printUsersTable} from "./usersTable.js";


const editUserForm = document.querySelector('#modalEdit');
const editUserPrefixId = "edit-user-field-";

editUserForm.addEventListener('submit', (e) => {
    e.preventDefault();

    fetch(requestURL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('edit-user-field-id').value,
            username: document.getElementById('edit-user-field-username').value,
            firstName: document.getElementById('edit-user-field-name').value,
            lastName: document.getElementById('edit-user-field-surname').value,
            email: document.getElementById('edit-user-field-email').value,
            age: document.getElementById('edit-user-field-age').value,
            password: document.getElementById('edit-user-field-password').value,
            roles: getRolesFromForm(document.getElementById('edit-user-field-roles'))
        })
    }).then(() => printUsersTable());
    $("#modalEdit").modal("hide");
})

document.addEventListener('click', e => {
    if (e.target.closest('#btn-edit-modal-call')) {
        getElementsById(e, editUserPrefixId);
        $("#modalEdit").modal("show");
    }
})