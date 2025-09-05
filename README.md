Manual de uso — Sistema de Nómina (JavaFX)

Este documento explica cómo usar la aplicación (usuario final) y muestra un ejemplo de ejecución. No necesitas conocer detalles técnicos del proyecto.

Requisitos

Java instalado (JRE/JDK 17 o superior).

JavaFX incluido en la app (si te entregaron un .exe/.app/.jar ejecutable, basta con doble clic).

Un archivo CSV con los colaboradores a calcular.

Cómo iniciar la aplicación
Opción A: Doble clic

Localiza el archivo entregado (por ejemplo, NominaApp.jar o instalador).

Doble clic para abrir. Se mostrará la ventana principal con una tabla vacía y los botones Cargar, Calcular, Exportar y Salir.

Opción B: Desde NetBeans (si te lo dieron como proyecto)

Abre el proyecto en NetBeans.

Presiona Run (F6).

Se abrirá la misma ventana principal.

Uso paso a paso

Cargar (importar CSV)

Pulsa Cargar.

Selecciona el archivo empleados.csv.

La tabla mostrará las filas cargadas (cédula, nombre, tipo, salario quincenal, bono y total a pagar).

Si alguna línea del CSV está mal, la app te mostrará una advertencia, pero cargará lo correcto.

Calcular

Pulsa Calcular para refrescar los resultados en pantalla (si cambiaste el archivo y recargaste, o si deseas actualizar la vista).

Exportar

Pulsa Exportar.

Elige dónde guardar la planilla quincenal (por ejemplo, planilla_quincena.csv).

Se creará un archivo con encabezado:
cedula;nombre;tipo;salarioQuincena;bono;totalAPagar.

Salir

Pulsa Salir para cerrar la aplicación.

Formato del archivo CSV de entrada

Cada línea representa una persona. Los campos se separan con ;.
Tipos admitidos:

ASALARIADO;cedula;nombre;salarioMensual

PORHORAS;cedula;nombre;tarifaHora;horasQuincena

TEMPORAL;cedula;nombre;tarifaDiaria;diasActivos

COMISIONISTA;cedula;nombre;base;porcentaje;ventasQuincena

PRACTICANTE;cedula;nombre;apoyoQuincena

Notas:

El porcentaje puede venir como 5 (5%) o 0.05 (5%).

Decimales válidos con coma o punto (ej.: 17500,5 o 17500.5).

Líneas vacías o que empiecen con # se ignoran.

Ejemplo de empleados.csv
# Ejemplos variados para probar
ASALARIADO;10101010;Ana Salgado;900000
PORHORAS;20202020;Luis Pérez;5000;82
TEMPORAL;30303030;María Gómez;20000;18
COMISIONISTA;40404040;Carlos Rojas;600000;5;12000000
PRACTICANTE;50505050;Sofía Torres;150000

ASALARIADO;11111111;Jhashua Canton;750000
PORHORAS;22222222;Marco López;4500;60
TEMPORAL;33333333;Laura Méndez;17500,5;12
COMISIONISTA;44444444;Diego Mora;500000;0.03;8000000
PRACTICANTE;55555555;Pedro Sánchez;0

Ejemplo de ejecución

Abre la app y pulsa Cargar → selecciona el CSV anterior.

Verás la tabla con filas como:

Cédula	Nombre	Tipo	Salario Quincena	Bono	Total
10101010	Ana Salgado	Asalariado	450000.00	45000.00	495000.00
20202020	Luis Pérez	PorHoras	410000.00	41000.00	451000.00
30303030	María Gómez	Temporal	300000.00	15000.00	315000.00
40404040	Carlos Rojas	Comisionista	900000.00	240000.00	1,140,000.00
50505050	Sofía Torres	Practicante	150000.00	0.00	150000.00
(valores ilustrativos con las políticas por defecto)					

Pulsa Exportar → elige ubicación y nombre (ej. planilla_quincena.csv).

Abre el archivo exportado:

cedula;nombre;tipo;salarioQuincena;bono;totalAPagar
10101010;Ana Salgado;Asalariado;450000.00;45000.00;495000.00
20202020;Luis Pérez;PorHoras;410000.00;41000.00;451000.00
30303030;María Gómez;Temporal;300000.00;15000.00;315000.00
40404040;Carlos Rojas;Comisionista;900000.00;240000.00;1140000.00
50505050;Sofía Torres;Practicante;150000.00;0.00;150000.00

Consejos rápidos

Si al Cargar aparece un aviso, revisa el CSV (tipo correcto y cantidad de campos).

Si los montos no se ven actualizados, pulsa Calcular para refrescar.

Guarda siempre la Exportación en una ruta que recuerdes (como Documentos o una carpeta out/).
