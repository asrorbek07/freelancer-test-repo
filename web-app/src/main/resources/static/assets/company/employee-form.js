// employee-form.js

document.addEventListener('DOMContentLoaded', function () {
    const autoCheckbox = document.getElementById('autoGenerate');
    const employeeNumberInput = document.getElementById('employeeNumber');

    if (autoCheckbox) {
        autoCheckbox.addEventListener('change', () => {
            if (autoCheckbox.checked) {
                // Generate a simple random number for demo purposes
                const generated = 'EMP' + Math.floor(Math.random() * 90000 + 10000);
                employeeNumberInput.value = generated;
                employeeNumberInput.readOnly = true;
                employeeNumberInput.setAttribute('readonly', 'readonly');
            } else {
                employeeNumberInput.value = '';
                employeeNumberInput.readOnly = false;
                employeeNumberInput.removeAttribute('readonly');
            }
        });
    }
});
