// ToDoアイテムの管理
let todos = [];
let currentFilter = 'all';

// DOM要素の取得
const todoInput = document.getElementById('todoInput');
const addBtn = document.getElementById('addBtn');
const todoList = document.getElementById('todoList');
const filterBtns = document.querySelectorAll('.filter-btn');
const clearCompletedBtn = document.getElementById('clearCompleted');
const clearAllBtn = document.getElementById('clearAll');
const totalCount = document.getElementById('totalCount');
const activeCount = document.getElementById('activeCount');
const completedCount = document.getElementById('completedCount');

// ローカルストレージからToDoを読み込む
function loadTodos() {
    const savedTodos = localStorage.getItem('todos');
    if (savedTodos) {
        todos = JSON.parse(savedTodos);
    }
    renderTodos();
}

// ローカルストレージにToDoを保存
function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
    renderTodos();
}

// ToDoを追加
function addTodo() {
    const text = todoInput.value.trim();
    if (text === '') {
        alert('タスクを入力してください');
        return;
    }

    const todo = {
        id: Date.now(),
        text: text,
        completed: false,
        createdAt: new Date().toISOString()
    };

    todos.push(todo);
    todoInput.value = '';
    saveTodos();
}

// ToDoを削除
function deleteTodo(id) {
    todos = todos.filter(todo => todo.id !== id);
    saveTodos();
}

// ToDoの完了状態を切り替え
function toggleTodo(id) {
    todos = todos.map(todo => 
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    saveTodos();
}

// ToDoを編集
function editTodo(id, newText) {
    if (newText.trim() === '') {
        deleteTodo(id);
        return;
    }
    todos = todos.map(todo => 
        todo.id === id ? { ...todo, text: newText.trim() } : todo
    );
    saveTodos();
}

// フィルターを適用
function getFilteredTodos() {
    switch (currentFilter) {
        case 'active':
            return todos.filter(todo => !todo.completed);
        case 'completed':
            return todos.filter(todo => todo.completed);
        default:
            return todos;
    }
}

// ToDoリストを描画
function renderTodos() {
    const filteredTodos = getFilteredTodos();
    
    if (filteredTodos.length === 0) {
        todoList.innerHTML = '<li class="empty-state">タスクがありません</li>';
    } else {
        todoList.innerHTML = filteredTodos.map(todo => `
            <li class="todo-item ${todo.completed ? 'completed' : ''}" data-id="${todo.id}">
                <input 
                    type="checkbox" 
                    class="todo-checkbox" 
                    ${todo.completed ? 'checked' : ''}
                    onchange="toggleTodo(${todo.id})"
                >
                <span class="todo-text">${escapeHtml(todo.text)}</span>
                <input 
                    type="text" 
                    class="edit-input" 
                    value="${escapeHtml(todo.text)}"
                    onblur="finishEdit(${todo.id}, this.value)"
                    onkeypress="handleEditKeypress(event, ${todo.id}, this.value)"
                >
                <div class="todo-actions">
                    <button class="action-btn edit-btn" onclick="startEdit(${todo.id})">編集</button>
                    <button class="action-btn delete-btn" onclick="deleteTodo(${todo.id})">削除</button>
                </div>
            </li>
        `).join('');
    }

    updateStats();
}

// HTMLエスケープ
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// 編集を開始
function startEdit(id) {
    const todoItem = document.querySelector(`[data-id="${id}"]`);
    todoItem.classList.add('editing');
    const editInput = todoItem.querySelector('.edit-input');
    editInput.focus();
    editInput.select();
}

// 編集を終了
function finishEdit(id, newText) {
    const todoItem = document.querySelector(`[data-id="${id}"]`);
    todoItem.classList.remove('editing');
    editTodo(id, newText);
}

// 編集時のEnterキー処理
function handleEditKeypress(event, id, value) {
    if (event.key === 'Enter') {
        finishEdit(id, value);
    }
}

// 統計を更新
function updateStats() {
    totalCount.textContent = `合計: ${todos.length}`;
    activeCount.textContent = `未完了: ${todos.filter(t => !t.completed).length}`;
    completedCount.textContent = `完了: ${todos.filter(t => t.completed).length}`;
}

// 完了済みを削除
function clearCompleted() {
    if (confirm('完了済みのタスクをすべて削除しますか？')) {
        todos = todos.filter(todo => !todo.completed);
        saveTodos();
    }
}

// すべて削除
function clearAll() {
    if (confirm('すべてのタスクを削除しますか？この操作は取り消せません。')) {
        todos = [];
        saveTodos();
    }
}

// フィルターボタンのイベント
filterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        filterBtns.forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        currentFilter = btn.dataset.filter;
        renderTodos();
    });
});

// イベントリスナー
addBtn.addEventListener('click', addTodo);
todoInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        addTodo();
    }
});
clearCompletedBtn.addEventListener('click', clearCompleted);
clearAllBtn.addEventListener('click', clearAll);

// 初期化
loadTodos();

