Feature: Request de proyectos
  Scenario: Empleado hace POST a /proyectos
    Given soy un empleado
    When el empleado agrega un proyecto
    Then el empleado recibe un status code de 200
    And el proyecto se agrega

  Scenario: Empleado hace GET a /proyectos
    Given soy un empleado
    When el empleado pide todos los proyectos
    Then el empleado recibe un status code de 200
    And se devuelven todos los proyectos

  Scenario: Empleado hace DELETE a /proyectos/{id} sin tareas
    Given soy un empleado
    When el empleado borra un proyecto sin tareas
    Then el empleado recibe un status code de 200
    And el proyecto se borra

  Scenario: Empleado hace PUT a /proyectos/{id}
    Given soy un empleado
    When el empleado cambia el nombre de un proyecto a "Nuevo Proyecto"
    Then el empleado recibe un status code de 200
    And el nombre del proyecto cambia

  Scenario: Empleadoo hace DELETE a /proyectos/{id} a un proyecto que no existe
  Given soy un empleado
  When el empleado borra un proyecto que no existe
  Then el empleado recibe un status code de 404

  #Scenario: Empleado hace DELETE a /proyectos/{id} con tareas
    #Given soy un empleado
    #When el empleado borra un proyecto con tareas
    #Then el empleado recibe un status code de 200
    #And las tareas se borran
    #And el proyecto se borra
