
# Eigenes Docker Image für Projekt bauen im Verzeichnis des Dockerfiles:
docker build -t meinprojekt .


# Alle Services starten mit Docker Compose im Verzeichnis des Compose Files:
docker-compose up

# REST API Aufruf via Browser nach dem Start:
http://localhost:49160/api/test/337?search=hallo&search=welt&limit=50