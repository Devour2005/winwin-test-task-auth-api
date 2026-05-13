## How to build start

Open a terminal and run a command `docker compose up -d --build`

# In case auth-api service hasn't started because of missing table error then you need to create tables.
1. Find file `resources/db/migration/V1__init_schema.sql`. 
2. In the terminal type the command `docker exec -it winwintesttesk-postgres-1 psql -U app -d winwin_app`
3. Copy SQL and insert it to the terminal.
4. in a new terminal run command `docker compose up -d`

## Endpoints (curl) for testing (POSTMAN)

# Register
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'

# Transform text (paste your token)
curl -X POST http://localhost:8080/api/process \
-H "Authorization: Bearer PASTE_TOKEN_HERE" \
-H "Content-Type: application/json" \
-d '{"text":"hello"}'

### ############################################################м
