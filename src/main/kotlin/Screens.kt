class Screens {
    val archives = mutableListOf<Archive>()
    fun mainMenu(){
        while (true){
            println("\nСписок архивов:")
            println("0. Создать архив")
            archives.forEachIndexed { index, archive ->  println("${index + 1}. ${archive.title}")}
            println("${archives.size + 1}. Выход")

            println("Введите номер для выбора опции:")
            val input = readLine()?.trim()?.toIntOrNull()
            if (input != null){
                when {
                    input == 0 -> archives.add(createArchive())
                    input in 1..archives.size -> selectArchive(archives[input - 1])
                    input == archives.size + 1 -> return
                    else -> println("Не верное число.")
                }
            } else {
                println("Введите число из списка.")
            }
        }
    }

    private fun selectArchive(archive: Archive) {
        while (true) {
            println("\nАрхив: ${archive.title}")
            println("0. Добавить заметку")
            if (!archive.notes.isEmpty()){
                archive.notes.forEachIndexed { index, note ->
                    println("${index + 1}. ${note.title}")
                }
            }
            println("${archive.notes.size + 1}. Назад")
            val input = readLine()?.trim()?.toIntOrNull()
            if (input != null){
                when {
                    input == 0 -> archive.notes.add(createNote(archive))
                    input in 1..archive.notes.size -> selectNote(archive.notes[input - 1])
                    input == archive.notes.size + 1 -> return
                    else -> println("Не верное число.")
                }
            } else {
                println("Введите число из списка.")
            }
        }
    }

    private fun selectNote(note: Note) {
        while (true){
            println("\nЗаметка: ${note.title}")
            println("Текст: ${note.text}")
            println("1. Редактировать текст заметки")
            println("2. Возврат")
            print("Выберите опцию: ")
            val input = readLine()?.trim()
            if (!input.isNullOrEmpty()) {
                when(input){
                    "1" -> editNoteText(note)
                    "2" -> return
                    else -> println("Не верное число.")
                }
            } else {
                println("Введите число из списка.")
            }
        }
    }

    private fun editNoteText(note: Note) {
        println("Введите текст заметки:")
        val inputText = readLine()?.trim()
        if (!inputText.isNullOrEmpty()) {
            note.text = note.text + inputText
            println("Заметка '${note.title}' изменена.")
        } else {
            println("Заметка не изменена.")
        }
    }

    private fun createNote(archive: Archive) : Note {
        while (true){
            println("Введите название заметки:")
            val input = readLine()?.trim()
            println("Введите текст заметки:")
            val inputText = readLine()?.trim()
            if (!input.isNullOrEmpty() && !inputText.isNullOrEmpty()) {
                println("Заметка '$input' создана.")
                return Note(input, inputText)
            } else {
                println("Название и содержание заметки не может быть пустыми.")
            }
        }
    }

    private fun createArchive() : Archive {
        while (true){
            println("Введите название архива:")
            val input = readLine()?.trim()
            if (!input.isNullOrEmpty()) {
                println("Архив '$input' создан.")
                return Archive(input)
            } else {
                println("Название архива не может быть пустым.")
            }
        }
    }
}
