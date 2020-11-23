
CREATE TABLE cursos(
    idCurso INT(10) PRIMARY KEY,
    Nombre VARCHAR(100),
    Duracion VARCHAR(100),
    Precio FLOAT(10,2),
    Vacantes INT(10),
    Disponibilidad VARCHAR(50),
    Fecha_inicio DATE,
    Fecha_final DATE
);

CREATE TABLE gastos(
    idGastos int(10) PRIMARY KEY,
    idCurso INT(10),
    Insumo VARCHAR(100),
    Precio FLOAT(10,2),
    Fecha DATE,
    Total_Aspirantes INT(10),
    Pago_Alumno VARCHAR(10),
    Ingreso_Curso FLOAT(10,2),
    FOREIGN KEY (idCurso) REFERENCES cursos(idCurso) 
);

CREATE TABLE alumnos(
    idAlumno INT(10) PRIMARY KEY,
    Nombre VARCHAR(100),
    Correo VARCHAR(100),
    Telefono VARCHAR(50),
    Perfil VARCHAR(100),
    Tipo VARCHAR(100)
);

CREATE TABLE profesor(
    idProfesor INT(10) PRIMARY KEY,
    Nombre VARCHAR(100),
    Correo VARCHAR(100),
    Telefono VARCHAR(50),
    Perfil VARCHAR(100)
);

CREATE TABLE A_profesor(
    idA_profesor INT(10) PRIMARY KEY,
    idProfesor INT(10),
    idCurso INT(10),
    FOREIGN KEY(idProfesor) REFERENCES profesor(idProfesor),
    FOREIGN KEY(idCurso) REFERENCES cursos(idCurso)
);

CREATE TABLE A_alumno(
    idA_alumno INT(10) PRIMARY KEY,
    idAlumno INT(10),
    idA_profesor INT(10),
    FOREIGN KEY(idAlumno) REFERENCES alumnos(idAlumno),
    FOREIGN KEY(idA_profesor) REFERENCES A_profesor(idA_profesor)
);

CREATE TABLE Asistencia(
    idAsistencia INT(10) PRIMARY KEY,
    idA_alumno INT(10),
    Fecha DATE,
    Entrada VARCHAR(50),
    Salida VARCHAR(50),
    Pago FLOAT(10,2),
    Observacion VARCHAR(150),
    FOREIGN KEY (idA_alumno) REFERENCES A_alumno(idA_alumno)
);