const net = require("net");

const listen_ip = "";
const listen_port = 9999;

// Dictionary, das je Benutzername den dazugehörigen Socket speichert
let all_users = {};

let server = net.createServer((my_socket) => {
    // Begrüßungsnachricht an den Client senden und Namen abfragen
    my_socket.setEncoding("utf-8");
    my_socket.setNoDelay();

    let my_status = "ask-my_username";
    let my_username = "NEUER BENUTZER";

    my_socket.write("Willkommen, Fremder!\n");
    my_socket.write("Verrate mir deinen Namen: ");

    // Nachrichten des Clients empfangen und verarbeiten
    my_socket.on("data", (data) => {
        data = data.trim();
        console.log(`» ${my_username}: ${data}\n`);

        if (my_status === "ask-my_username") {
            if (data in all_users) {
                my_socket.write("Ein Benutzer mit diesem Namen ist bereits eingeloggt.\n");
                my_socket.write("Verrate mir deinen Namen: ");
            } else {
                my_username = data;
                all_users[my_username] = my_socket;
                my_status = "logged-in";

                my_socket.write(`Alles klar. Hallo, ${my_username}.\n\n`);
            }
        } else if (my_status === "logged-in") {
            if (data === "/WHO") {
                my_socket.write("Folgende User sind derzeit online:\n");

                for (let username in all_users) {
                    my_socket.write(`  » ${username}\n`);
                }

                my_socket.write("\n");
            } else {
                let timestamp = new Date().toLocaleString("de");

                for (let username in all_users) {
                    let other_socket = all_users[username];
                    other_socket.write(`[${timestamp}, ${my_username}] ${data}\n`);
                }
            }
        }

    });

    my_socket.on("close", () => delete all_users[my_username]);
});

console.log(`Server empfängt auf ${listen_ip}:${listen_port}`)
server.listen(listen_port, listen_ip);
