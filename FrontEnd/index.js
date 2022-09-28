const urlBase = 'http://localhost:8080';
window.addEventListener("load", renderHome());

function renderNav() {

    let navigation = document.createElement("nav");
    navigation.classList.add("d-flex", "justify-content-center","flex-nowrap");
    navigation.classList.add("shadow", "p-3", "mb-5","bg-secondary", "rounded");

    //navigation.className = "navbar";
    let navList = document.createElement("ul");
    navList.className = "dawnnav";
    let navHome = document.createElement("LI");
        navHome.innerText = "Home";
        navHome.addEventListener("click", renderHome);
    let navUsers = document.createElement("LI");
        navUsers.innerText = "Users";
        navUsers.addEventListener("click", renderLogin);
    let navLocations = document.createElement("LI");
        navLocations.innerText = "Locations";
        navLocations.addEventListener("click", renderLocations);
    let navSearch = document.createElement("LI");
        navSearch.innerText = "Search";
        navSearch.addEventListener("click", renderSearchHome);

    navList.append(navHome, navUsers, navLocations, navSearch);

    navigation.appendChild(navList);
    document.querySelector("body").appendChild(navigation);
    document.body.classList.add("bg-light");

}

function renderLogin(){
    derenderPage();
    let loginContainer = document.createElement("div");
    loginContainer.id = "login";
    loginContainer.className = "bannerLogin";
    loginContainer.classList.add("form-group","text-center","justify-content-center");
    loginContainer.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");
            
    let usernameLabel = document.createElement("p");        // create username static label "Username"
    usernameLabel.innerText = "Username";
    loginContainer.appendChild(usernameLabel);

    let usernameInput = document.createElement("input");    // create username input variable "user1"
    usernameInput.id = "username";
    usernameInput.type = "text";
    usernameInput.placeholder = "username";
    loginContainer.appendChild(usernameInput);
    usernameInput.classList.add("form-control","col-xs-2","w-25","mx-auto");
    loginContainer.appendChild(document.createElement("br"));

    let passwordLabel = document.createElement("p");        // create password static label "Password"
    passwordLabel.innerText = "Password";
    loginContainer.appendChild(passwordLabel);

    let passwordInput = document.createElement("input");    // create password input variable "pass1"
    passwordInput.type = "password";
    passwordInput.id = "password";
    passwordInput.placeholder = "password";
    loginContainer.appendChild(passwordInput);
    passwordInput.classList.add("form-control","col-xs-2","w-25","mx-auto");
    loginContainer.appendChild(document.createElement("br"));
    loginContainer.appendChild(document.createElement("br"));

    let submitButton = document.createElement("input");     // create submit button, goes to asyncLogin
    submitButton.type = "button";
    submitButton.value = "submit";
    submitButton.addEventListener("click", asyncLogin);
    submitButton.classList.add("btn", "btn-primary","m-3");
    loginContainer.appendChild(submitButton);

    let resetButton = document.createElement("input");      // create reset button to clear input strings
    resetButton.type = "button";
    resetButton.value = "reset";
    resetButton.classList.add("btn", "btn-primary");
    resetButton.addEventListener("click", function(){
        usernameInput.value = "";
        passwordInput.value = "";
    });
    loginContainer.appendChild(resetButton);

    document.querySelector("body").appendChild(loginContainer); // adds login container to html body, below nav bar
    
}

function derenderPage(){
    document.querySelector("body").innerHTML = "";
    renderNav();
}

async function asyncLogin(){
    let userInput = document.querySelector("#username").value;
    let passInput = document.querySelector("#password").value;
    const path = '/api/v1/users/login';

    let loginObj = {
        username: userInput,
        password: passInput
    };

    try{
        let response = await fetch(
            urlBase + path,
            {
                method: "POST",
                headers: new Headers({
                    'content-type':'application/json'
                }),
                body: JSON.stringify(loginObj)}
        )
        user = await response.json();
    }catch(error){
        console.error(`Error is ${error}`);
    }
    renderUserHome(user);
}

function renderHome() {
    derenderPage();
    let homediv = document.createElement("div");

    let homebanner = document.createElement("h1");
    homebanner.id = "homebanner";
    homebanner.innerText = "Welcome to Mountain Project Lite";
    homediv.appendChild(homebanner);
    homebanner.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded");
   // homebanner.classList.add("text-shadow: 1px 1px 2px red, 0 0 1em blue, 0 0 0.2em blue;");
    let homepic = document.createElement("img");
    homepic.src = "https://dl.dropboxusercontent.com/s/eamnmm5u5efedfq/chrome_1NHD4eZ2qx.jpg";
    homepic.id = "homepic";
    homediv.appendChild(homepic);
    homepic.classList.add("shadow","shadow-info", "p-3", "mb-5", "rounded-circle")

    document.querySelector("body").appendChild(homediv);
}

function renderUserHome(user){
    derenderPage();
    let userinfoDiv = document.createElement("div");
    let userbanner = document.createElement("h1");
    userbanner.innerText = 'Welcome, ' + user.username + '!';
    let userpic = document.createElement("img");
    userpic.id = "userpic";
    userpic.src = user.photo_url;
    console.log(user);

    userinfoDiv.append(userbanner, userpic);
    document.querySelector("body").appendChild(userinfoDiv);
}

async function renderRoutesByLocation(location) {
    derenderPage();
    renderWeather(location.latlong);
    let nameHeader = document.createElement("h2");
    nameHeader.innerText = location.locationName;

    let locationRoutes = await getRoutesByLocation(location.id);
    let routeTable = document.createElement("table");
    routeTable.id = "routeTable";
    let routeHeader = document.createElement("tr");
    let thName = document.createElement("th");
    thName.innerText = "Route Name";
    let thDifficulty = document.createElement("th");
    thDifficulty.innerText = "Difficulty";
    let thLength = document.createElement("th");
    thLength.innerText = "Length (feet)";
    routeHeader.append(thName, thDifficulty, thLength);

    for (i=0; i<locationRoutes.length; i++){
        let routeItem = document.createElement("tr");
        let tdName = document.createElement("tr");
        tdName.innerText = locationRoutes[i].name;
        tdName.id = locationRoutes[i].route_id;
        let tdDifficulty = document.createElement("tr");
        tdDifficulty.innerText = locationRoutes[i].difficulty;
        let tdLength = document.createElement("tr");
        tdLength.innerText = locationRoutes[i].length;
        routeItem.append(tdName, tdDifficulty, tdLength);
        routeTable.append(routeItem);
    }

    routeTable.onclick = async function(event) {
        routeId = event.target.id;
        let route = await getRouteById(routeId);
        renderRoute(route);

        // let backButton = document.createElement("input");
        // backButton.type = "button";
        // backButton.value = "Back";
        // backButton.onclick = await renderRoutesByLocation(route.location_id);
        // document.querySelector("body").append(backButton);  
    }

    let backButton = document.createElement("input");
    backButton.type = "button";
    backButton.value = "Back";
    backButton.onclick = renderLocations;
    document.querySelector("body").append(backButton, nameHeader, routeTable);
}

function renderSearchHome() {
    derenderPage();
    let searchHeader = document.createElement("h2");
    searchHeader.innerText = "Search Routes By:"
    searchHeader.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded");
    document.querySelector("body").append(searchHeader);
    let searchDiv = document.createElement("div");
    let searchTypes = ['Name', 'Rating', 'Difficulty', 'Location'];
    let listeners = [searchByName, searchByRating, searchByDifficulty, searchByLocation];
    for (let i = 0; i < searchTypes.length; i++){
        let itemDiv = document.createElement("div");
        itemDiv.classList.add("row","align-items-center");
            let nameInput = document.createElement("input");
                nameInput.type = "text";
                nameInput.classList.add("form-control","col-4","w-25","mx-auto");
                nameInput.id = `${searchTypes[i]}Search`;
                nameInput.placeholder = `Search by ${searchTypes[i]}`;
            let searchBtn = document.createElement("button");
                searchBtn.innerText = "Submit";
                searchBtn.classList.add("btn", "btn-primary","col-3");
                searchBtn.addEventListener("click", listeners[i]);
        itemDiv.append(nameInput, searchBtn);
        searchDiv.appendChild(itemDiv);
        searchDiv.appendChild(document.createElement("br"));
        searchDiv.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");
    }
    document.querySelector("body").append(searchDiv);
}

const searchByName = async function() {
    let compressRoutes = await getAllRoutesCompress();
    let nameInput = document.querySelector("#NameSearch").value;
    let inputCompress = nameInput.replaceAll(/[^a-zA-Z0-9]/g, "").toLowerCase();
    for (let route of compressRoutes) {
        if (route.name == inputCompress){
            let actualRoute = await getRouteById(route.route_id)
            await renderRoute(actualRoute);
        }
    }
}

const searchByRating = async function() {
    console.log('5');
}

const searchByDifficulty = async function() {
    console.log('5');
}

const searchByLocation = async function() {
    console.log('5');
}

const renderRoute = async function(route) {
    derenderPage();
    console.log(route);
    let routeDiv = document.createElement("div");
        let routeHeader = document.createElement("h2");
        routeHeader.innerText = `${route.name}`;
        let routeDifficulty = document.createElement("p");
        routeDifficulty.innerText = `YDS Rating: 5.${route.difficulty}`;
        let routeLength = document.createElement("p");
        routeLength.innerText = `Length: ${route.length} feet`;

    routeDiv.append(routeHeader, routeDifficulty);
    
    let locationDiv = document.createElement("div");
        let locationName = document.createElement("h2");
        locationName.innerText = `Location: ${route.location_id.locationName}`;
        let latlong = document.createElement("p");
        latlong.innerText = `Latitude/Longitude: ${route.location_id.latlong}`;

    locationDiv.append(locationName, latlong);
        
    document.querySelector("body").append(routeDiv, locationDiv);
    renderWeather(route.location_id.latlong);
}

async function renderLocations() { //display list of locations
    derenderPage();
    let locations = await getLocations(); 
    let locationTable = document.createElement("table");
    locationTable.classList.add("table", "table-striped");
    locationTable.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");
    locationTable.id = "locationTable";
    let locationHead = document.createElement("tr");
    let thName = document.createElement("th");
    thName.innerText = "Location Name";
    let thCoords = document.createElement("th");
    thCoords.innerText = "Coordinates";
    locationHead.append(thName, thCoords);
    locationHead.classList.add("bg-warning");
    locationTable.appendChild(locationHead);

    locationTable.id = "locationList";

    for (i=0; i<locations.length; i++){
        let locationItem = document.createElement("tr");
        let locationName = document.createElement("td");
        locationName.id = `${locations[i].id}`;
        locationName.innerText = `${locations[i].locationName}`;
        let locationCoords = document.createElement("td");
        locationCoords.id = `${locations[i].id}`;
        locationCoords.innerText = `${locations[i].latlong}`;
        locationItem.append(locationName, locationCoords);
        locationTable.appendChild(locationItem);
    }
    locationTable.onclick = async function(event) {
        console.log(event.target);
        let locationId = event.target.id;
        let location = await getLocationById(locationId);
        renderRoutesByLocation(location);
    }
    
    document.querySelector("body").appendChild(locationTable);
}

async function renderWeather(latlong) {
    let weather = await getCurrentWeather(latlong);
    console.log(weather);

    let weatherDiv = document.createElement("div");

    let locationInfo = document.createElement("h3");
    locationInfo.innerText = `Weather in ${weather.location.name}, ${weather.location.region}`;
    weatherDiv.appendChild(locationInfo);

    let conditionInfo = document.createElement("p");
    conditionInfo.innerText = `Weather Conditions: ${weather.current.condition.text}`;
    weatherDiv.appendChild(conditionInfo);

    let conditionImg = document.createElement("img");
    conditionImg.src = weather.current.condition.icon;
    weatherDiv.appendChild(conditionImg);

    let tempC = document.createElement("p");
    tempC.innerText = `Current Temperature (degrees Celsius): ${weather.current.temp_c}`;
    weatherDiv.appendChild(tempC);

    let humidity = document.createElement("p");
    humidity.innerText = `Current Relative Humidity: ${weather.current.humidity}%`;
    weatherDiv.appendChild(humidity);


    document.querySelector("body").append(weatherDiv);
}

async function getLocations() {
    const path = '/api/v1/locations';
    const url = urlBase + path;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

async function getLocationById(id) {
    const path = '/api/v1/locations/';
    const url = urlBase + path + id;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

async function getAllRoutes() {
    const path = '/api/v1/routes';
    const url = urlBase + path;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

const getRouteById = async function(id) {
    const path = '/api/v1/routes/';
    const url = urlBase + path + id;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

async function getAllRoutesCompress() {
    const path = '/api/v1/routescompress';
    const url = urlBase + path;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

async function getRoutesByLocation(id) {
    const path = '/api/v1/routesByLocationId/';
    const url = urlBase + path + id;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}

const getCurrentWeather = async function(latlong) {
    const weatherApiUrl = 'http://api.weatherapi.com/v1/current.json?key=d4add7b7a6f24255aa8202015221509&q='; // documentation: https://www.weatherapi.com/api-explorer.aspx
    const url = weatherApiUrl + latlong;
    try {
        let response = await fetch(
            url,
            {
                method: "GET",
                headers: new Headers({'content-type':'application/json'}),
                body: null
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }
}