// Erweitertes Beispiel basierend auf  https://nodejs.org/de/docs/guides/nodejs-docker-webapp/

'use strict';

const express = require('express');

const PORT = 8089;
const HOST = '0.0.0.0';

const app = express();
app.get('/api/test/:id', (req, res) => {
  res.send('Hello World! params: ' + JSON.stringify(req.params) + ' queryparams: ' + JSON.stringify(req.query));
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);