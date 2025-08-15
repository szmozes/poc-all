FROM postgres:17
ENV POSTGRES_PASSWORD=postgres
COPY ./sql/*.sql /docker-entrypoint-initdb.d/
