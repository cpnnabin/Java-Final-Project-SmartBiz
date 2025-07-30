// SmartBiz Main JavaScript
document.addEventListener("DOMContentLoaded", function() {
    initializeTheme();
    initializeTooltips();
    initializeAnimations();
    initializeFormValidation();
    initializeTableFeatures();
    initializeModals();
});

// Theme Management
function initializeTheme() {
    const themeToggle = document.getElementById("themeToggle");
    const savedTheme = localStorage.getItem("theme") || "light";
    
    // Apply saved theme
    document.documentElement.setAttribute("data-theme", savedTheme);
    updateThemeToggleIcon(savedTheme);
    
    if (themeToggle) {
        themeToggle.addEventListener("click", function() {
            const currentTheme = document.documentElement.getAttribute("data-theme");
            const newTheme = currentTheme === "dark" ? "light" : "dark";
            
            document.documentElement.setAttribute("data-theme", newTheme);
            localStorage.setItem("theme", newTheme);
            updateThemeToggleIcon(newTheme);
            
            showToast(`Switched to ${newTheme} mode`, "success");
        });
    }
}

function updateThemeToggleIcon(theme) {
    const themeToggle = document.getElementById("themeToggle");
    if (themeToggle) {
        const icon = themeToggle.querySelector("i");
        if (icon) {
            icon.className = theme === "dark" ? "fas fa-sun" : "fas fa-moon";
        }
    }
}

// Toast Notifications
function showToast(message, type = "info", duration = 3000) {
    const toastContainer = getOrCreateToastContainer();
    const toastId = "toast-" + Date.now();
    
    const toastHTML = `
        <div id="${toastId}" class="toast align-items-center text-bg-${type} border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="fas fa-${getToastIcon(type)} me-2"></i>
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;
    
    toastContainer.insertAdjacentHTML("beforeend", toastHTML);
    
    const toastElement = document.getElementById(toastId);
    const toast = new bootstrap.Toast(toastElement, { delay: duration });
    toast.show();
    
    // Remove toast element after it's hidden
    toastElement.addEventListener("hidden.bs.toast", function() {
        toastElement.remove();
    });
}

function getOrCreateToastContainer() {
    let container = document.getElementById("toast-container");
    if (!container) {
        container = document.createElement("div");
        container.id = "toast-container";
        container.className = "toast-container position-fixed bottom-0 end-0 p-3";
        container.style.zIndex = "1055";
        document.body.appendChild(container);
    }
    return container;
}

function getToastIcon(type) {
    const icons = {
        success: "check-circle",
        danger: "exclamation-triangle",
        warning: "exclamation-circle",
        info: "info-circle"
    };
    return icons[type] || "info-circle";
}

// Initialize Bootstrap Tooltips
function initializeTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

// Smooth Animations
function initializeAnimations() {
    // Fade in animation for cards
    const cards = document.querySelectorAll('.card');
    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        setTimeout(() => {
            card.style.transition = 'all 0.5s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, index * 100);
    });
    
    // Hover effects for buttons
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(button => {
        button.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-2px)';
        });
        
        button.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
}

// Form Validation
function initializeFormValidation() {
    const forms = document.querySelectorAll('.needs-validation');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                showToast("Please fill in all required fields correctly", "danger");
            }
            form.classList.add('was-validated');
        });
    });
    
    // Real-time validation
    const inputs = document.querySelectorAll('input, select, textarea');
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateField(this);
        });
        
        input.addEventListener('input', function() {
            if (this.classList.contains('is-invalid')) {
                validateField(this);
            }
        });
    });
}

function validateField(field) {
    const isValid = field.checkValidity();
    field.classList.remove('is-valid', 'is-invalid');
    field.classList.add(isValid ? 'is-valid' : 'is-invalid');
    
    // Show/hide custom error message
    const errorDiv = field.parentNode.querySelector('.invalid-feedback');
    if (errorDiv) {
        errorDiv.style.display = isValid ? 'none' : 'block';
    }
}

// Table Features
function initializeTableFeatures() {
    // Search functionality
    const searchInputs = document.querySelectorAll('.table-search');
    searchInputs.forEach(input => {
        input.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const table = this.closest('.card').querySelector('table tbody');
            const rows = table.querySelectorAll('tr');
            
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(searchTerm) ? '' : 'none';
            });
        });
    });
    
    // Sort functionality
    const sortableHeaders = document.querySelectorAll('.sortable');
    sortableHeaders.forEach(header => {
        header.addEventListener('click', function() {
            sortTable(this);
        });
    });
}

function sortTable(header) {
    const table = header.closest('table');
    const tbody = table.querySelector('tbody');
    const rows = Array.from(tbody.querySelectorAll('tr'));
    const columnIndex = Array.from(header.parentNode.children).indexOf(header);
    const isAscending = header.classList.contains('sort-asc');
    
    // Remove existing sort classes
    header.parentNode.querySelectorAll('th').forEach(th => {
        th.classList.remove('sort-asc', 'sort-desc');
    });
    
    // Add new sort class
    header.classList.add(isAscending ? 'sort-desc' : 'sort-asc');
    
    // Sort rows
    rows.sort((a, b) => {
        const aValue = a.children[columnIndex].textContent.trim();
        const bValue = b.children[columnIndex].textContent.trim();
        
        // Try to parse as numbers
        const aNum = parseFloat(aValue);
        const bNum = parseFloat(bValue);
        
        if (!isNaN(aNum) && !isNaN(bNum)) {
            return isAscending ? bNum - aNum : aNum - bNum;
        } else {
            return isAscending ? bValue.localeCompare(aValue) : aValue.localeCompare(bValue);
        }
    });
    
    // Reorder rows in DOM
    rows.forEach(row => tbody.appendChild(row));
}

// Modal Management
function initializeModals() {
    // Auto-focus first input in modals
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        modal.addEventListener('shown.bs.modal', function() {
            const firstInput = this.querySelector('input, select, textarea');
            if (firstInput) {
                firstInput.focus();
            }
        });
    });
}

// Utility Functions
function formatCurrency(amount) {
    return new Intl.NumberFormat('en-IN', {
        style: 'currency',
        currency: 'INR'
    }).format(amount);
}

function formatDate(date) {
    return new Intl.DateTimeFormat('en-IN').format(new Date(date));
}

function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Loading States
function showLoading(element) {
    const originalContent = element.innerHTML;
    element.innerHTML = '<span class="loading"></span> Loading...';
    element.disabled = true;
    return originalContent;
}

function hideLoading(element, originalContent) {
    element.innerHTML = originalContent;
    element.disabled = false;
}

// Confirmation Dialogs
function confirmAction(message, callback) {
    if (confirm(message)) {
        callback();
    }
}

// Export functions for global use
window.SmartBiz = {
    showToast,
    formatCurrency,
    formatDate,
    showLoading,
    hideLoading,
    confirmAction,
    debounce
};

// Auto-save form data
function initializeAutoSave() {
    const forms = document.querySelectorAll('form[data-autosave]');
    
    forms.forEach(form => {
        const formId = form.getAttribute('data-autosave');
        
        // Load saved data
        const savedData = localStorage.getItem(`autosave-${formId}`);
        if (savedData) {
            const data = JSON.parse(savedData);
            Object.keys(data).forEach(key => {
                const field = form.querySelector(`[name="${key}"]`);
                if (field) {
                    field.value = data[key];
                }
            });
        }
        
        // Save data on input
        const saveData = debounce(() => {
            const formData = new FormData(form);
            const data = {};
            for (let [key, value] of formData.entries()) {
                data[key] = value;
            }
            localStorage.setItem(`autosave-${formId}`, JSON.stringify(data));
        }, 1000);
        
        form.addEventListener('input', saveData);
        
        // Clear saved data on successful submit
        form.addEventListener('submit', () => {
            localStorage.removeItem(`autosave-${formId}`);
        });
    });
}

// Initialize auto-save when DOM is loaded
document.addEventListener("DOMContentLoaded", initializeAutoSave);

