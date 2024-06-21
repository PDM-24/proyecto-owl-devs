package com.owldevs.taskme.constants

object Constants{
    //api service
    // ip kattia: 192.168.1.5 o 172.20.10.2
    // ip jc: 192.168.1.25
    const val BASE_URL = "http://192.168.1.5:3000"
    const val API_PATH = "/api"

    //post paths

    //register user
    const val POST_USER_PATH = "/users/register"

    //post a review
    const val POST_REVIEW_PATH = "/postReview"

    //post a task
    const val POST_TASK_PATH = "/postTask"

    //post a notification
    const val POST_NOTIFICATION_PATH = "/postNotification"

    //post a category
    const val POST_CATEGORY_PATH = "/postCategory"

    //get paths
    const val GET_USER_LOGIN_PATH = "/users/login"

    //api responses
    const val RESPONSE_SUCCESSFUL = "result"
    const val RESPONSE_ERROR = "message"


    //values of data classes
    const val ID_USUARIO = "id_usuario"
    const val TIPO = "tipo"
    const val MENSAJE = "mensaje"
    const val ESTADO = "estado"
    const val FECHA_CREACION = "fecha_creacion"
    const val NOMBRE = "nombre"
    const val ICONO = "icono"
    const val CATEGORIA = "categoria"
    const val CLIENTE_ID = "cliente_id"
    const val TASKER_ID = "tasker_id"
    const val FECHA = "fecha"
    const val HORA = "hora"
    const val UBICACION = "ubicacion"
    const val PRECIO = "precio"
    const val METODO_PAGO = "metodo_pago"
    const val NOMBRE_COMPLETO = "nombreCompleto"
    const val CORREO_ELECTRONICO = "correoElectronico"
    const val CONTRASENIA = "contrasenia"
    const val USUARIO_TASKER = "usuario_tasker"
    const val METODOS_PAGO = "tarjetas_asociadas"
    const val PERFIL_TASKER = "perfilTasker"
    const val HABILIDADES = "habilidades"
    const val GALERIA_TRABAJOS = "galeria_trabajos"
    const val NUMERO = "numero"
    const val FECHA_VENCIMIENTO = "fecha_vencimiento"
    const val NUMERO_CV = "numero_cv"
    const val TELFONO = "telfono"
    const val DESCRIPCION_PERSONAL = "descripcion_personal"
    const val FECHA_UNION = "fecha_unión"
    const val TRABAJOS_REALIZADOS = "trabajos_realizados"
    const val PROMEDIO_CALIFIACIONES = "calificacion"
    const val FOTO = "foto"
    const val ID_CATEGORIA = "id_categoria"
    const val URL = "url"
    const val DESCRIPCION = "descripcion"
    const val AUTOR_ID = "autor_id"
    const val CALIFICACION = "calificacion"
    const val TEXTO = "texto"

}