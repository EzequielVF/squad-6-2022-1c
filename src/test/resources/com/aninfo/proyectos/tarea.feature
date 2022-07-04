Feature: Request de tareas
  Scenario: Empleado hace POST a /proyectos/{id}/tareas
    Given un proyecto
    When el empleado agrega la tarea al proyecto
    Then la tarea se agrega al proyecto
    And se recibe un status code de 200

  Scenario: Empleado hace GET a /proyectos/{id}/tareas
    Given un proyecto con tareas
    When el empleado pide las tareas del proyecto
    Then se devuelven todas las tareas del proyecto
    And se recibe un status code de 200

  Scenario: Empleado hace DELETE a /proyectos/{id1}/tareas/{id2}
    Given un proyecto con tareas
    When el empleado borra una tarea del proyecto
    Then la tarea se borra del proyecto
    #And el empleado recibe un status code de 200