document.addEventListener("DOMContentLoaded", function () {
    // DELETE confirmation
    const deleteButtons = document.querySelectorAll(".delete-btn");
    deleteButtons.forEach(button => {
        button.addEventListener("click", function (e) {
            const name = this.dataset.employeeName || "this employee";
            const confirmDelete = confirm(`Are you sure you want to delete ${name}?`);
            if (!confirmDelete) {
                e.preventDefault();
            }
        });
    });

    // Live search
    const searchInput = document.getElementById("employeeSearch");
    const rows = document.querySelectorAll(".employee-table tbody tr");

    if (searchInput) {
        searchInput.addEventListener("input", function () {
            const value = this.value.toLowerCase();
            rows.forEach(row => {
                const rowText = row.textContent.toLowerCase();
                row.style.display = rowText.includes(value) ? "" : "none";
            });
        });
    }

    // Add data-label for mobile
    const headers = document.querySelectorAll(".employee-table thead th");
    rows.forEach(row => {
        row.querySelectorAll("td").forEach((td, index) => {
            if (headers[index]) {
                td.setAttribute("data-label", headers[index].innerText);
            }
        });
    });
});
