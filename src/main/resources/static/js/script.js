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
}