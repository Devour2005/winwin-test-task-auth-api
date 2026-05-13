#!/usr/bin/env bash
set -euo pipefail

DB_NAME="${POSTGRES_DB:-winwin_app}"
DB_USER="${POSTGRES_USER:-app}"

echo "Initializing database '${DB_NAME}' for user '${DB_USER}'..."

psql -v ON_ERROR_STOP=1 --username "$DB_USER" --dbname "postgres" <<-EOSQL
    SELECT 'CREATE DATABASE ${DB_NAME}'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '${DB_NAME}')\gexec

    GRANT ALL PRIVILEGES ON DATABASE ${DB_NAME} TO ${DB_USER};
    ALTER DATABASE ${DB_NAME} OWNER TO ${DB_USER};

    \connect ${DB_NAME}
    GRANT ALL ON SCHEMA public TO ${DB_USER};
    ALTER SCHEMA public OWNER TO ${DB_USER};
EOSQL

echo "Database '${DB_NAME}' is ready."