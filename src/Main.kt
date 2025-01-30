import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Tarea(val id: Int, val descripcion: String) {
    var estado: Boolean = false
    var fechaRealizada: String? = null

    fun marcarComoRealizada() {
        if (!estado) {
            estado = true
            fechaRealizada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        }
    }

    override fun toString(): String {
        return "ID: $id - $descripcion - Estado: ${if (estado) "Realizada el $fechaRealizada" else "Pendiente"}"
    }
}

class ListaTareas {
    private val tareas = mutableListOf<Tarea>()
    private var contadorId = 1

    fun agregarTarea(descripcion: String) {
        tareas.add(Tarea(contadorId++, descripcion))
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun cambiarEstado(id: Int) {
        tareas.find { it.id == id }?.marcarComoRealizada()
    }

    fun mostrarTareas() {
        if (tareas.isEmpty()) println("No hay tareas.")
        else tareas.forEach { println(it) }
    }

    fun mostrarPendientes() {
        tareas.filter { !it.estado }.forEach { println(it) }
    }

    fun mostrarRealizadas() {
        tareas.filter { it.estado }.forEach { println(it) }
    }
}

fun main() {
    val listaTareas = ListaTareas()
    while (true) {
        println("\n1. Agregar tarea")
        println("2. Eliminar tarea")
        println("3. Cambiar estado de tarea")
        println("4. Mostrar todas las tareas")
        println("5. Mostrar tareas pendientes")
        println("6. Mostrar tareas realizadas")
        println("7. Salir")
        print("Seleccione una opción: ")

        when (readln().toIntOrNull()) {
            1 -> {
                print("Ingrese la descripción de la tarea: ")
                val descripcion = readln()
                listaTareas.agregarTarea(descripcion)
            }
            2 -> {
                print("Ingrese el ID de la tarea a eliminar: ")
                val id = readln().toIntOrNull()
                if (id != null) listaTareas.eliminarTarea(id)
            }
            3 -> {
                print("Ingrese el ID de la tarea a marcar como realizada: ")
                val id = readln().toIntOrNull()
                if (id != null) listaTareas.cambiarEstado(id)
            }
            4 -> listaTareas.mostrarTareas()
            5 -> listaTareas.mostrarPendientes()
            6 -> listaTareas.mostrarRealizadas()
            7 -> return
            else -> println("Opción no válida")
        }
    }
}
