Sistema de Gestión de Nómina (JavaFX)

Proyecto académico en JavaFX para gestionar la nómina quincenal de diferentes tipos de personal, con carga/parseo desde CSV, cálculo de salario + bono, exportación de la planilla y estilos CSS con paleta verde. Incluye pruebas JUnit en el paquete de tests.

📦 Características

UI JavaFX con TableView y botones:

Cargar: selecciona y carga un CSV de empleados.

Calcular: refresca/recalcula valores mostrados.

Exportar: guarda la planilla (CSV) con salario, bono y total a pagar.

Salir: cierra la aplicación.

Importación CSV robusta (admite decimales con , o . y porcentaje 5 o 0.05).

Exportación CSV con encabezado estándar.

Modelo orientado a objetos con polimorfismo:

Asalariado, PorHoras, Temporal, Comisionista extienden Empleado.

Practicante NO extiende Empleado. Se integra en la tabla mediante Adapter: PracticanteEmpleadoAdapter.

Política de Bonos clara y separada de salarioQuincena().

Estilos modernos (CSS JavaFX) con paleta: #16A34A, #22C55E, #065F46, #DCFCE7.

Pruebas unitarias (JUnit) en Test Packages.

🧱 Estructura del proyecto
src/
 ├─ main/java/
 │   ├─ app/                  # App launcher (JavaFX)
 │   ├─ controller/           # PrimaryController (lógica de UI)
 │   ├─ infra/                # RepositorioCSV, ExportadorCSV (IO)
 │   └─ modelo/               # Empleado y subtipos + Adapter
 │
 ├─ main/resources/
 │   ├─ view/                 # FXML (si aplica)
 │   └─ css/                  # app.css (tema verde)
 │
 └─ test/java/
     ├─ modelo/               # Tests de modelos
     └─ infra/                # Tests de IO (CSV)

👥 Modelos y Reglas de Cálculo

Empleado.nomina() = salarioQuincena() + Bono().
No mezcles bonos/incentivos dentro de salarioQuincena().

Asalariado

salarioQuincena() = salarioMensual / 2

Bono() = 5% del salario mensual

PorHoras

salarioQuincena() = tarifaHora * horasQuincena

Bono() = 10% del salario quincenal si horasQuincena > 80; si no, 0

Temporal

salarioQuincena() = tarifaDiaria * diasPagados, con tope de 15 días

Bono() = 5% del salario quincenal si diasActivos >= 12; si no, 0

Comisionista

salarioQuincena() = base/2 (si base es mensual) + (ventasQuincena * porcentaje)

Bono() = 2% de ventasQuincena

Practicante

No hereda de Empleado.

PracticanteEmpleadoAdapter lo adapta para la TableView<Empleado>.

salarioQuincena() = apoyoQuincena

Bono() = 0

Sugerencia de diseño: si luego quieres cambiar políticas sin tocar los modelos, crea una Strategy de Incentivos y haz que pagarIncentivo() delegue a esa estrategia.

🧾 Formato del CSV de entrada

Cada línea comienza por el tipo y luego los campos; separador ;.

ASALARIADO;cedula;nombre;salarioMensual
PORHORAS;cedula;nombre;tarifaHora;horasQuincena
TEMPORAL;cedula;nombre;tarifaDiaria;diasActivos
COMISIONISTA;cedula;nombre;base;porcentaje;ventasQuincena
PRACTICANTE;cedula;nombre;apoyoQuincena


porcentaje puede venir como 5 (5%) o 0.05 (5%).

Decimales con , o ..

Líneas vacías o que inicien con # se ignoran.

CSV de ejemplo (colócalo como data/empleados.csv)
# Ejemplos variados
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

💾 Exportación de la planilla

El botón Exportar genera un CSV con encabezado:

cedula;nombre;tipo;salarioQuincena;bono;totalAPagar


Ejemplo de fila:

10101010;Ana Salgado;Asalariado;450000.00;45000.00;495000.00


Implementación clave:

infra.ExportadorCSV – generarLineasPlanilla(...) y exportarPlanilla(ruta, empleados)

PrimaryController.OnExportar(...) – muestra diálogo “Guardar como…” y llama al exportador.

🎨 Estilos (CSS JavaFX)

Paleta verde:

--primary: #16A34A

--accent: #22C55E

--dark: #065F46

--bg: #DCFCE7

Incluye:

Header de tabla plano (sin degradados de Modena).

Zebra rows.

Botones con hover/pressed y sombras suaves.

Card contenedora (aplica styleClass="card" a tu VBox/AnchorPane).

Si algo no se aplica, revisa que el CSS esté agregado a la escena y que los selectores sean correctos para JavaFX (.table-row-cell, no .row-cell).

🧩 Componentes principales

UI / Controller

controller.PrimaryController

onCargar(...): abre selector de archivos y delega en RepositorioCSV

onCalcular(...): refresca la tabla

OnExportar(...): guarda planilla via ExportadorCSV

Infraestructura

infra.RepositorioCSV

seleccionarArchivoCSV(Window)

cargarEmpleados(ruta, erroresOut) – retorna List<Empleado>

LeerLineas(...) / escribirLineas(...)

infra.ExportadorCSV

generarLineasPlanilla(...)

exportarPlanilla(ruta, empleados)

Modelo

modelo.Empleado (abstracto): salarioQuincena(), Bono(), nomina()

Subclases: Asalariado, PorHoras, Temporal, Comisionista

Practicante (no hereda de Empleado)

PracticanteEmpleadoAdapter (patrón Adapter)

▶️ Ejecución
Requisitos sugeridos

JDK 17+

JavaFX 17+

NetBeans/IntelliJ/Eclipse configurado para JavaFX.

Opciones VM (si ejecutas desde línea de comandos)

Ajusta la ruta a tu SDK de JavaFX:

--module-path "C:\javafx\lib" --add-modules=javafx.controls,javafx.fxml


En NetBeans, configura JavaFX en Project Properties → Run.

🧪 Pruebas (JUnit en Test Packages)

Project → Properties → Libraries → Test Libraries → Add Library → JUnit 5
(Si solo tienes JUnit 4, puedes usarlo cambiando imports).

Crea clases de prueba en Test Packages con el mismo package que el código.

Ejecuta Project → Test.

Tests incluidos (sugeridos):

modelo/AsalariadoTest.java

modelo/PorHorasTest.java

modelo/TemporalTest.java

modelo/ComisionistaTest.java

modelo/PracticanteEmpleadoAdapterTest.java

infra/RepositorioCSVTest.java

infra/ExportadorCSVTest.java

🛠️ Problemas comunes (Troubleshooting)

UnsupportedOperationException en Empleado.nomina()
Implementa:

public double nomina() { return salarioQuincena() + Bono(); }


Encabezado de tabla con degradado gris
Quita el degradado por defecto:

.table-view .column-header-background { -fx-background-insets: 0; }


Selector de CSS incorrecto
Usa .table-row-cell (no .row-cell).

CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS no existe
Usa TableView.CONSTRAINED_RESIZE_POLICY.

initialize() no se ejecuta
Verifica ortografía exacta (initialize, sin doble “t”).

➕ Extender el sistema

Nuevos tipos de personal
Crea una clase que extienda Empleado (o un Adapter si no quieres heredar) e implementa:

salarioQuincena()

Bono()

Cambiar política de bonos
Extrae la lógica a una Strategy y haz que pagarIncentivo() delegue ahí. Así no tocas los modelos y cumples OCP.

📄 Licencia

Uso académico/educativo. Puedes adaptar y reutilizar el código citando la fuente del proyecto.

🙌 Créditos

Desarrollado como práctica de POO, herencia y polimorfismo con JavaFX y JUnit. Si necesitas dejar el proyecto listo con Strategy y/o Planillable (interfaz unificadora), dilo y te lo agrego.
