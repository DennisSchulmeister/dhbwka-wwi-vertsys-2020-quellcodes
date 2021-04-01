#! /bin/sh

# Beispielskript zum Starten der Docker-Container für "Golfcard Plus".
# Verbindet die Container über ein internes Netzwerk und gibt Port 8080 des
# Frontends über Port 8080 des Hosts frei.
#
# Nicht berücksichtigt wird hier, dass die Datenbank weiterhin "In-Memory" ist
# und die Anwendungen ihre Logs nicht-persistent im Container ablegen. Hierfür
# müssten noch Volumes definiert und ggf. separate Datenbank-Container gestartet
# werden, wenn die interne H2-Datenbank nicht genutzt werden soll.
#
# Im internen Container-Netzwerk besitzt jeder Container einen DNS-Eintrag mit
# seinem Namen. Dadurch können die Container untereinander kommunizieren, indem
# sie als Adresse einfach den Namen des jeweils anderen Containers verwenden.
# Dies macht im Grunde genommen die Verwendung des Spring Nameservers
# überflüssig.

SPRING_PROFILE=cloud

API_PORT_HTTP=8090
API_PORT_HTTPS=8443
API_USE_HTTPS=false

ADMIN_URL=http://host.docker.internal:8888
EUREKA_URL=http://host.docker.internal:8761

function run_container() {
    IMAGE=$1
    shift
    PORT=$1
    shift
    NAME=$1
    shift
    APP_DOMAIN=$1
    shift
    ARGUMENTS=$@

    echo
    echo "»»»» Container: $IMAGE"
    echo "»»»» Name: $NAME"
    echo "»»»» Port: $PORT"
    echo "»»»» Arguments: $@"
    echo

    #
    docker run \
                --name $NAME \
                --network gcplus \
                --add-host host.docker.internal:host-gateway \
                -e "SPRING_PROFILES_ACTIVE=$SPRING_PROFILE" \
                -e "PORT=$PORT" \
                -e "APP_DOMAIN=$APP_DOMAIN" \
                -e "API_PORT_HTTP=$API_PORT_HTTP" \
                -e "API_PORT_HTTPS=$API_PORT_HTTPS" \
                -e "API_USE_HTTPS=$API_USE_HTTPS" \
                -e "ADMIN_URL=$ADMIN_URL" \
                -e "EUREKA_URL=$EUREKA_URL" \
                $ARGUMENTS \
                $IMAGE
}

docker network create --driver bridge gcplus

# Funktion      Image-Label                 Port   Name                App Domain          Weitere Parameter
run_container   vertsys/gcplus-admin        8888   gcplus-admin        gcplus-admin        -p 8888:8888 --detach
run_container   vertsys/gcplus-nameserver   8761   gcplus-nameserver   gcplus-nameserver   -p 8761:8761 --detach
run_container   vertsys/gcplus-gateway      8090   gcplus-gateway      localhost           -p 8090:8090 --detach
run_container   vertsys/gcplus-golfcourse   8091   gcplus-golfcourse   gcplus-golfcourse   -p 8091:8091 --detach
run_container   vertsys/gcplus-scorecard    8092   gcplus-scorecard    gcplus-scorecard    -p 8092:8092 --detach
run_container   vertsys/gcplus-frontend     8080   gcplus-frontend     gcplus-frontend     -p 8080:8080 -it
