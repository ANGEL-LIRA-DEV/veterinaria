## Presentación
El proyecto es una API hecha en Spring java con JPA 

## Endpoints
### 1. Registrar adoptante
`POST /api/adoptantes`

![[Pasted image 20260613230001.png]]

### 2. Listar adoptantes
`GET /api/adoptantes`

![[Pasted image 20260613230212.png]]

### 3. Buscar adoptante por id
`GET /api/adoptantes/{id}`

![[Pasted image 20260613230337.png]]

### 4. Actualizar adoptante
`PUT /api/adoptantes/{id}`

![[Pasted image 20260613230652.png]]

### 5. Borrado lógico
`DELETE /api/adoptantes/{id}`

![[Pasted image 20260613230834.png]]

Todos los endpoints comunes funcionan igual para todas las entidades

## Endpoints intermedios
### Endpoint 1 - Registrar mascota
`POST /api/mascotas`

![[Pasted image 20260613225130.png]]


### Endpoint 2 - Registrar vacuna
`POST /api/vacunas`

![[Pasted image 20260613223842.png]]


### Endpoint 3 - Consulta consolidada
`GET /api/mascotas/vacunacion-pendiente`

![[Pasted image 20260613225414.png]]