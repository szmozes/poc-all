#!/usr/bin/env bash
set -euo pipefail

ADMIN_DB="postgres"
DATABASES=(
auth_engine
filter_specification
jooq
)

# Create databases if they don't exist
for DBNAME in "${DATABASES[@]}"; do
  if ! psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$ADMIN_DB" -tAc "SELECT 1 FROM pg_database WHERE datname='${DBNAME}'" | grep -q 1; then
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$ADMIN_DB" -c "CREATE DATABASE ${DBNAME};"
  fi
done
