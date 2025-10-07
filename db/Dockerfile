# Use the latest MySQL image
FROM mysql:latest

LABEL maintainer="Cotximahou"

# Working directory (optional)
WORKDIR /tmp

# Copy your world.sql file
COPY world-db/world.sql /docker-entrypoint-initdb.d/

# Environment variables for MySQL setup
# Avoid using root externally; create a dedicated DB user
ENV MYSQL_DATABASE=world
ENV MYSQL_USER=world_appuser
ENV MYSQL_PASSWORD=world_app_password
# Root password is required by the MySQL image
ENV MYSQL_ROOT_PASSWORD=root_password

# Expose MySQL default port
EXPOSE 3306
