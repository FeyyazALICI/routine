# Use the official MySQL image as a base
FROM mysql:8.0

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=Light80s!
ENV MYSQL_DATABASE=routine_db
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=Light80s!

# Copy any initialization scripts (if needed)
COPY init.sql /docker-entrypoint-initdb.d/