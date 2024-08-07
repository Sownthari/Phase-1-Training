
// Add Task form submission
document.getElementById('task-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const taskName = document.getElementById('task').value;
    const taskPriority = document.getElementById('priority').value;

    console.log(`Task Name: ${taskName}, Priority: ${taskPriority}`); 
    if (taskName.trim() === '' || !taskPriority) {
        alert('Please fill out all fields.');
        return;
    }

    addTask(taskName, taskPriority);
    document.getElementById('task-form').reset();
});

// Function to add a task
function addTask(taskName, taskPriority) {
    const taskTable = document.getElementById('task-table');

    if (!taskTable) {
        console.error('Task table element not found.'); 
        return;
    }

    const newRow = document.createElement('tr');
    newRow.classList.add('task');
    newRow.classList.add(priorityClass(taskPriority));
    newRow.dataset.priority = taskPriority;
    newRow.innerHTML = `
                <td>${taskName}</td>
                <td>${priorityText(taskPriority)}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteTask(this)">Delete</button>
                </td>
            `;

    taskTable.appendChild(newRow);
}

// Function to delete a task
function deleteTask(button) {
    const row = button.closest('tr');
    if (row) {
        row.remove();
    } else {
        console.error('Task row not found.'); 
    }
}

// Function to filter tasks based on priority
function filterTasks(priority) {
    const tasks = document.querySelectorAll('.task');
    tasks.forEach(task => {
        if (priority === 'All' || task.dataset.priority === priority) {
            task.style.display = '';
        } else {
            task.style.display = 'none';
        }
    });
}

// Function to convert priority code to text
function priorityText(priorityCode) {
    switch (priorityCode) {
        case 'P1': return 'High';
        case 'P2':
        case 'P3': return 'Medium';
        case 'P4': return 'Low';
        default: return '';
    }
}

// Function to get CSS class based on priority
function priorityClass(priorityCode) {
    switch (priorityCode) {
        case 'P1': return 'priority-p1';
        case 'P2': return 'priority-p2';
        case 'P3': return 'priority-p3';
        case 'P4': return 'priority-p4';
        default: return '';
    }
}

// Date/Time display
function updateTime() {
    const now = new Date();
    document.getElementById('current-date-time').textContent = now.toLocaleString();
}
setInterval(updateTime, 1000);

// Initial call to display date/time
updateTime();
