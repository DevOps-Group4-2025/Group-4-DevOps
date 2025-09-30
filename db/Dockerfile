# Use the latest MySQL image
FROM mysql/mysql-server:latest

LABEL authors="Cotximahou"

WORKDIR /tmp

# Copy world.sql file to the working directory of the container
COPY world-db/world.sql /tmp/

# This is specific to MySQL. Scripts here run automatically on container startup
COPY world-db/world.sql /docker-entrypoint-initdb.d/

# Environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=group4
ENV MYSQL_DATABASE=world
ENV MYSQL_USER=world-appuser
ENV MYSQL_PASSWORD=world-app-password

