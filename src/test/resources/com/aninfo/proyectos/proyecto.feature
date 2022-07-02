Feature: Request de proyectos
  Scenario: Empleado hace POST a /proyectos
    Given soy un empleado
    When el empleado agrega un proyecto
    Then el empleado recibe un status code de 200
    And el proyecto se agrega

  #Scenario: Empleado hace GET a /proyectos
  #  Given soy un empleado
  #  When el empleado pide todos los proyectos
  #  Then el empleado recibe un status code de 200
  #  And se devuelven todos los proyectos
