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
    document.getElementById("id").innerHTML = tab;
}