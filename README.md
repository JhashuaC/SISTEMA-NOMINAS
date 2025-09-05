# 📊 Sistema de Nómina (JavaFX)

Bienvenido al **Sistema de Nómina** desarrollado en JavaFX.  
Este documento incluye:  
- 📖 Manual de uso (usuario final).  
- 📝 Ejemplo de ejecución.  
- 📂 Formato de entrada CSV.  
- 👨‍💻 Créditos de los autores.  

---

## ✅ Requisitos

- ☕ **Java 17 o superior** (JRE/JDK).  
- 🎭 **JavaFX** ya viene incluido en la app entregada.  
- 📂 Un archivo **CSV** con los colaboradores a calcular.  

---

## 🚀 Cómo iniciar la aplicación

### 🔹 Opción A: Doble clic
1. Localiza el archivo entregado (ej: `NominaApp.jar`, `.exe` o `.app`).  
2. Haz **doble clic**.  
3. Se abrirá la ventana principal con la tabla vacía y los botones:  
   👉 **Cargar, Calcular, Exportar, Salir**.  

### 🔹 Opción B: Desde NetBeans
1. Abre el proyecto en **NetBeans**.  
2. Presiona ▶️ **Run (F6)**.  
3. Aparecerá la misma ventana principal.  

---

## 📝 Uso paso a paso

### 📥 1. Cargar (importar CSV)
- Pulsa **Cargar**.  
- Selecciona tu archivo `empleados.csv`.  
- La tabla mostrará:  
  - **Cédula, Nombre, Tipo, Salario Quincena, Bono, Total a Pagar**.  
- ⚠️ Si alguna línea está mal, verás una advertencia, pero se cargará lo correcto.  

### 🧮 2. Calcular
- Pulsa **Calcular**.  
- La tabla refresca los totales.  
- Útil si actualizaste o recargaste el CSV.  

### 📤 3. Exportar
- Pulsa **Exportar**.  
- Elige la ubicación para guardar el archivo `planilla_quincena.csv`.  
- El archivo contendrá: cedula;nombre;tipo;salarioQuincena;bono;totalAPagar


### ❌ 4. Salir
- Pulsa **Salir** para cerrar la aplicación.  

---

## 📂 Formato del archivo CSV de entrada

Cada fila representa una persona (separador **;**)  

- 👔 **ASALARIADO** → `ASALARIADO;cedula;nombre;salarioMensual`  
- ⏱️ **PORHORAS** → `PORHORAS;cedula;nombre;tarifaHora;horasQuincena`  
- 📅 **TEMPORAL** → `TEMPORAL;cedula;nombre;tarifaDiaria;diasActivos`  
- 💼 **COMISIONISTA** → `COMISIONISTA;cedula;nombre;base;porcentaje;ventasQuincena`  
- 🎓 **PRACTICANTE** → `PRACTICANTE;cedula;nombre;apoyoQuincena`  

📌 Notas:  
- El porcentaje puede ser `5` (5%) o `0.05` (5%).  
- Decimales: **coma** o **punto** (`17500,5` o `17500.5`).  
- Líneas vacías o con `#` se ignoran.  

---

## 📊 Ejemplo de `empleados.csv`

```csv
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
