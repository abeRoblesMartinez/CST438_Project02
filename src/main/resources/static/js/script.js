
displayItems();
getItems();


async function displayItems(){
    let url = "https://fast-dawn-34687.herokuapp.com/api/findUser?username=";
    url+=name;
    let data = await fetchData(url);
    let size = data.wishlists.length;
    // let pdata = JSON.stringify(JSON.parse(data),null,2);
    // document.querySelector("#wishlists").innerHTML += pdata;
    for (let i=0;i<size;i++){
        document.querySelector("#wishlists").innerHTML += `<div class="lists"><h4>${data.wishlists[i].name}</h4>`;
        for (let j=0;j< data.wishlists[i].items.length;j++){
            document.querySelector("#wishlists").innerHTML +=`<br> <h6>${data.wishlists[i].items[j].name}</h6><br>$ ${data.wishlists[i].items[j].price}`;
        }
        document.querySelector("#wishlists").innerHTML += "<br>";
    }
}

async function getItems(){
    let url = "https://fast-dawn-34687.herokuapp.com/api/allItems";
    let data = await fetchData(url);

    for (let i=0; i<data.length;i++){
        document.querySelector("#allItems").innerHTML +=`<br> <h4>${data[i].name}</h4><br>$ ${data[i].price}<br>${data[i].info}<br>`;
    }

}


async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;

const api_url = "http://fast-dawn-34687.herokuapp.com/api/allItems";

async function api(api_url) {
    const response = await fetch(api_url);

    var data = await response.json();
    console.log(data);
    if(response){
        hideloader();
    }
    show(data);
}

api(api_url);

function hideloader(){
    document.getElementById('loading').style.display = 'none';
}

function show(data) {
    let tab =
        `<tr>
         <th>Item</th>
         <th>Price</th>
         <th>Description</th>
         </tr>`;

         for(let r of data.list) {
         tab += ` <tr>
        <td>${r.name}</td>
        
        <td>${r.info}</td>
        </tr>`;
}
