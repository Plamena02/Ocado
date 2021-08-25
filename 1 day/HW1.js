class Point{ constructor (x, y) { this.x = x; this.y = y; } }

class Product{ constructor(name, grams) { this.name = name; this.grams = grams; } }

class Order { 
    
    constructor(location) { this.location = location; this.products = []; } 

    AddProduct(product, quantity){ this.products.push([product, quantity]); }

}

class Drone{

    constructor(start_location, power_consumption, battery){

        this.start_location = start_location;
        this.power_consumption = power_consumption;
        this.battery = battery;
        this.products_to_deliver = []; 
    }

    DistancePerKilometer() {
        return this.battery / this.power_consumption;
    }

}

class Warehouse{ constructor(location) { this.location = location; } }

function RandInt(max, min=0) {
    return Math.floor(Math.random() * (max - min) + min);
}

function Distance(A, B) {
    return Math.sqrt((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));
}

function compare(a, b) {
    return a[0] - b[0];
}

function main() {

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

    let largestX = 0;
    let largestY = 0;

    for (let i = start_order_index; i < input.length;){

        let order_details = input[i].replace(/\(|\)|,/gm, "").split(' ');
        
        let x = Number(order_details[0]);
        let y = Number(order_details[1]);

        if (largestX < x) largestX = x;
        if (largestY < y) largestY = y;

        let coords = new Point(x,y);
        let result = orders.find(z => z.location.x === coords.x && z.location.y === coords.y);
        let order = (result) ? result : new Order(coords);
        
        let M = Number(order_details[2]) + 1;
        for (let j = 1; j < M; j++){

            let product_details = input[i + j].split('_');
            order.AddProduct(products.find(x => x.name === product_details[0]), product_details[1]);

        }

        if (!result) orders.push(order);
        
        i += M;
    }

    let warehouses = [];

    for (let i = 0; i < RandInt(1, N + 1); i++){

        let coords = new Point(RandInt(largestX), RandInt(largestY));
        if (warehouses.find(x => x.location.x === coords.x && x.location.y === coords.y) || orders.find(x => x.location.x === coords.x && x.location.y === coords.y)){

            i--;
            continue;

        }

        let warehouse = new Warehouse(coords);
        warehouses.push(warehouse);

    }

    let drones = [];

    for (let i = 0; i < N; i++){

        let index = RandInt(warehouses.length);
        let new_drone = new Drone(warehouses[index].location, Number(input[1].split(' ')[i]), Number(input[2].split(' ')[i]));
        drones.push(new_drone);

    }

    for (let i = 0; i < orders.length; i++){

        let sum = 0;
        for(let m = 0; m < orders[i].products.length; m++){
            sum += Number(orders[i].products[m][0].grams) * Number(orders[i].products[m][1]);
        }

        let distances = [];
        for (let j = 0; j < warehouses.length; j++){ distances.push([Distance(orders[i].location, warehouses[j].location), j]); }
        distances.sort(compare);

        for (let j = 0; j < warehouses.length; j++){

            let drones_in_warehouse = drones.filter(x => x.start_location.x === warehouses[distances[j][1]].location.x && x.start_location.y === warehouses[distances[j][1]].location.y);
            let max = 0;
            for (let k = 0; k <drones_in_warehouse.length; k++)
            {
                let distance = drones_in_warehouse[k].DistancePerKilo()*sum/1000;
                 if(max < distance) max = distance; 
            }

            if (max/2 >= distances[j][0]) 
            return distances[j][0];
        }
    }
}

console.log(main());