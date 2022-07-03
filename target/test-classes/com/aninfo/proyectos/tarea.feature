Feature: Request de tareas
  Scenario: Empleado hace POST a /proyectos/{id}/tareas
    Given soy un empleado
    When el empleado agrega una tarea a un proyecto
    Then el empleado recibe un status code de 200
    And la tarea se agrega al proyecto con id ?

  Scenario: Empleado hace GET a /proyectos/{id}/tareas
    Given soy un empleado
    When el empleado pide las tareas del proyecto con id {id}
    Then el empleado recibe un status code de 200
    And se devuelven todas las tareas del proyecto con id {id}

  Scenario: Empleado hace DELETE a /proyectos/{id1}/tareas/{id2}
    Given soy un empleado
    When el empleado borra la tarea con id {id2} del proyecto con id {id1}
    Then el empleado recibe un status code de 200
    And se borra la tarea con id {id2} del proyecto con id {id1}