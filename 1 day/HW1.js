const { resolve } = require('path');
const pf = require('pathfinding')
const net = require('net');
let working = true;

class Point{ constructor (x, y) { this.x = x; this.y = y; } }
class Product{ constructor(name, grams) { this.name = name; this.grams = grams; } }
class Order { 
    
    constructor(location) 
    {
         this.location = location; 
         this.products = []; 
    } 
    AddProduct(product, quantity){ this.products.push([product, quantity]); }

}
  class Drone{

    constructor(power_consumption, battery)
    {
        this.power_consumption = power_consumption;
        this.battery = battery;
        this.path = [];
    }

    IsWorking() {

    }

    DistancePerKilo() {
        return this.battery / this.power_consumption;
    }

}

class Warehouse{ 
    
    constructor(location) 
    { 
        this.location = location;
        this.parked_drones = [];
    } 
}

function Random(max, min=0) {
    return Math.floor(Math.random() * (max - min) + min);
}

function Distance(A, B) {
    return Math.sqrt((B[0] - A[0]) * (B[0] - A[0]) + (B[1] - A[1]) * (B[1] - A[1]));
}

String.prototype.replaceAt = function(index, replacement) {
    return this.substr(0, index) + replacement + this.substr(index + replacement.length);
}

function sleep(time){
    return new Promise(resolve => setTimeout(resolve, time));
}

function ShowGrid(warehouses, orders, grid, drone_location){

    let map = [];

    for (let i = 0; i < grid.height; i++){
        map.push(' '.repeat(grid.width));
        for (let j = 0; j < grid.width; j++){
            if (warehouses.find(x => x.location.x == j && x.location.y == i)) map[i] = map[i].replaceAt(j, 'w');
            if (orders.find(x => x.location.x == j && x.location.y == i)) map[i] = map[i].replaceAt(j, 'x');
        }
    }
    if (drone_location) map[drone_location[1]] = map[drone_location[1]].replaceAt(drone_location[0], '@');

    return map;

}

async function main() {

    const fs = require('fs');
    let input = fs.readFileSync('input.txt', 'utf-8').replace(/(\r|\r)/gm, "").split('\n');

    const N = Number(input[0]);
    const number_of_products = Number(input[3]);
    let products = [];
    let orders = [];

    for (let i = 0; i <= number_of_products; i++){

        let data = input[4 + i].split('_');
        let new_product = new Product(data[0], data[1]);
        products.push(new_product);

    }

    const start_order_index = number_of_products + 5;

    let boundary_X = 0;
    let boundary_Y = 0;

    for (let i = start_order_index; i < input.length;){

        let order_details = input[i].replace(/\(|\)|,/gm, "").split(' ');
        
        let x = Number(order_details[0]);
        let y = Number(order_details[1]);

        if (boundary_X < x) boundary_X = x + 1;
        if (boundary_Y < y) boundary_Y = y + 1;

        let coordinates = new Point(x,y);
        let result = undefined;
    
        for(let order of orders) {
            if(order.location.x === coordinates.x && order.location.y === coordinates.y)
            {
                result = order;
            }
        }
        
        let order = undefined;
        if(result){
            order = result;
        }
        else{
            order = new Order(coordinates);
        }
        
        let M = Number(order_details[2]) + 1;
        for (let j = 1; j < M; j++){

            let product_details = input[i + j].split('_');
            for(let product of products) {
                if(product.name === product_details[0])
                {
                    order.AddProduct(product, product_details[1]);
                }
            }
        }

        if (!result) orders.push(order);
        
        i += M;
    }
    
    let grid = new pf.Grid(boundary_X, boundary_Y);
    let warehouses = [];

    let wh = 3;
    if(orders.length >= 6)
    {
        wh = 5;
    }
    for (let i = 0; i < wh; i++){

        let coordinates = undefined;
        do {   

            coordinates = new Point(Random(boundary_X), Random(boundary_Y));   

        } while (warehouses.find(x => x.location.x === coordinates.x && x.location.y === coordinates.y) 
        || orders.find(x => x.location.x === coordinates.x && x.location.y === coordinates.y));

        let warehouse = new Warehouse(coordinates);
        warehouses.push(warehouse);

    }

    let drones = [];

    for (let i = 0; i < N; i++){

        let index = Random(warehouses.length);
        let new_drone = new Drone(Number(input[1].split(' ')[i]), Number(input[2].split(' ')[i]));
        drones.push(new_drone);
        warehouses[index].parked_drones.push(new_drone);

    }

    let minutes = [];
    let paths = [];

    console.log(ShowGrid(warehouses, orders, grid));

    for (let i = 0; i < orders.length; i++){

        let sum = 0;
        for(let m = 0; m < orders[i].products.length; m++){
            sum += Number(orders[i].products[m][0].grams) * Number(orders[i].products[m][1])/1000;
        }

        let shortest_path = undefined;

        for (let j = 0; j < warehouses.length; j++){

            let backup = grid.clone();
            let finder = new pf.AStarFinder({allowDiagonal:true});
            if(warehouses[j].parked_drones.length)
            {
                let path = finder.findPath(warehouses[j].location.x, warehouses[j].location.y, orders[i].location.x, orders[i].location.y, backup);
                if(!shortest_path)
                {
                    shortest_path = [path,j];
                }

                if(shortest_path[0].length > path.length)
                {
                    shortest_path = [path,j];
                }
            }
        }
        let temp = [];
        for (let j = 0; j < shortest_path[0].length - 1; j++)
        {
            temp.push(shortest_path[0][shortest_path[0].length - j - 2]);
        }
        for(let item of temp)
        {
            shortest_path[0].push(item);
        }
        
        paths.push(shortest_path);
        
    }

    
    for(let path of paths) {
        let arrived = false;
        let distance = 0;
        for(let i = 0; i < path[0].length; i++) {
            if (!working) { await sleep(1); i--; continue; }
            if (orders.find(x => x.location.x == path[0][i][0] && x.location.y == path[0][i][1])) arrived = true;
            if (i < path[0].length - 1 && !arrived) distance += Distance(path[0][i], path[0][i + 1]);
            await sleep(1000);
            console.log('\033[2J');
            console.log(ShowGrid(warehouses, orders, grid, path[0][i]));
            
        }
        minutes.push([Math.round(distance), Math.round(Math.round((distance % 1) * 100) * 0.6)]);
    }

    for(let time of minutes) {
        console.log('Order delivered in', time[0], 'minutes and', time[1], 'seconds');
    }

}

const server = net.createServer((socket) => {

    socket.on('data', (buffer) => {
        let command = buffer.toString('utf-8').replace(/(\n|\n)/gm, "");
        if (command == 'pause') working = false;
        if (command == 'resume') working = true;
    });
    
    socket.on('close', ()=>{
        socket.end();
    });
  
});

server.listen(7071);

main();