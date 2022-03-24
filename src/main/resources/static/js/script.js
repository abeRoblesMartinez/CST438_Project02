displayItems();

async function displayItems(){
    let url = "http://localhost:8080/api/findUser?username=";
    url+=name;
    let data = await fetchData(url);
    let size = Object.keys(data.wishlists).length;
    for (let i=0;i<size;i++){
        document.querySelector("#wishlists").innerHTML += `<div class="lists"><h4>${data.wishlists[i].name}</h4>`;
        for (let j=0;j<Object.keys(data.wishlists[i]).length;j++){
            document.querySelector("#wishlists").innerHTML +=`<br> <h6>${data.wishlists[i].items[j].name}</h6><br>$ ${data.wishlists[i].items[j].price}`;
        }
    }
}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}