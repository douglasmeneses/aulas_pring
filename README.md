# Demo - CRUD de Usuarios (Iniciante)

Projeto simples para aprender Spring Boot com:
- API REST
- Spring Data JPA
- Banco H2

## Endpoints

- `POST /api/usuarios`
- `GET /api/usuarios`
- `GET /api/usuarios/{id}`
- `PUT /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`

## Exemplo de JSON

```json
{
  "nome": "Maria Silva",
  "email": "maria@demo.com"
}
```

## H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:demo`
- User: `sa`
- Password: (vazio)

## Como rodar

```bash
./gradlew bootRun
```

## Como testar

```bash
./gradlew test
```

# aulas_pring
