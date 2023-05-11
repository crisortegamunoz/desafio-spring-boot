# CONSIDERACIONES PREVIAS

En el siguiente desafío se busca tener una muestra de las habilidades técnicas involucradas en el cargo
solicitado.
Para el desarrollo deberás utilizar Java con Spring Boot, un gestor de configuración, consumir un
servicio, disponibilizar un servicio con opciones de filtrado, la solución debe encontrarse dockerizada
y autodocumentada (swagger). Además, se debe dejar disponible en algún repositorio público.
La escala de evaluación será considerada a partir del cumplimiento (Cumple Completamente, Cumple
Parcialmente, No Cumple)
Importante: Cuentas con 3 horas para el desarrollo del desafío.

# DESAFÍO
1. Utilizar Gradle o Maven como gestor de configuración del proyecto.
2. Utilizar el siguiente servicio base, cuya información debe estar contenida al momento de
inicializar el contexto. (No se debe volver a llamar a este servicio por cada invocación del
aplicativo): https://api.victorsanmartin.com/feriados/en.json
3. Incluir parámetro dentro de la operación para filtrar tipos (Religioso, Civil, por ejemplo) y por
rango de fecha. Usar alguna: @PathVariable / @RequestParam / @RequestBody
4. El servicio debe tener la capacidad de responder en application/xml y application.json.
5. Incluir Logs (Uso libre de librerías que estimes conveniente).
6. Incluir test unitarios en lo posible.
7. Entrega de la solución a través de repositorio público (debes crearlo y entregarnos la URL
para clonarlo).
8. Código documentado y auto descriptivo.
9. El servicio debe quedar Dockerizado