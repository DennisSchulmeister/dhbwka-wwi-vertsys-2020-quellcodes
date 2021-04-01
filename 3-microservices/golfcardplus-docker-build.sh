#! /bin/sh

function do_build() {
    LABEL="$1"
    DIR="$2"

    docker build -t "$LABEL" "$DIR"
}

# Funktion   Image Lagel                 Verzeichnis mit Dockerfile
do_build     vertsys/gcplus-admin        Loesung_GolfcardPlus_Admin_Server
do_build     vertsys/gcplus-nameserver   Loesung_GolfcardPlus_Nameserver
do_build     vertsys/gcplus-gateway      Loesung_GolfcardPlus_API_Gateway
do_build     vertsys/gcplus-golfcourse   Loesung_GolfcardPlus_Backend_Golfplatz
do_build     vertsys/gcplus-scorecard    Loesung_GolfcardPlus_Backend_Scorekarte
do_build     vertsys/gcplus-frontend     Loesung_GolfcardPlus_Frontend

