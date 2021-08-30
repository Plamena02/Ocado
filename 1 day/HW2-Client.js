const net = require('net');
const readline = require('readline');

const client = new net.Socket();
client.connect(7071, process.argv[2], () => {
  console.log('Connected to server');
  const rl = readline.createInterface({ input: process.stdin });
  rl.on('line', (line) => {
      client.write(`${line}\n`);
  });
});