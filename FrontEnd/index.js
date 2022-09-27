const urlBase = 'http://localhost:8080';
window.addEventListener("load", renderHome());

function renderNav() {

    let navigation = document.createElement("nav");
    navigation.className = "navbar";
    let navList = document.createElement("ul");
    navList.className = "dawnnav";
    let navHome = document.createElement("LI");
        navHome.innerText = "Home";
        navHome.addEventListener("click", renderHome);
    let navUsers = document.createElement("LI");
        navUsers.innerText = "Users";
        navUsers.addEventListener("click", renderLogin);
    let navRoutes = document.createElement("LI");
        navRoutes.innerText = "Routes";
        navRoutes.addEventListener("click", renderRoutes);
    let navLocations = document.createElement("LI");
        navLocations.innerText = "Locations";
        navLocations.addEventListener("click", renderLocations);
    let navSearch = document.createElement("LI");
        navSearch.innerText = "Search";

    navList.append(navHome, navUsers, navRoutes, navLocations, navSearch);

    navigation.appendChild(navList);
    document.querySelector("body").appendChild(navigation);
}

function renderLogin(){
    derenderPage();
    let loginContainer = document.createElement("div");
    loginContainer.id = "login";
    loginContainer.className = "bannerLogin";
            
    let usernameLabel = document.createElement("p");        // create username static label "Username"
    usernameLabel.innerText = "Username";
    loginContainer.appendChild(usernameLabel);

    let usernameInput = document.createElement("input");    // create username input variable "user1"
    usernameInput.id = "username";
    usernameInput.type = "text";
    usernameInput.placeholder = "username";
    loginContainer.appendChild(usernameInput);
    loginContainer.appendChild(document.createElement("br"));

    let passwordLabel = document.createElement("p");        // create password static label "Password"
    passwordLabel.innerText = "Password";
    loginContainer.appendChild(passwordLabel);

    let passwordInput = document.createElement("input");    // create password input variable "pass1"
    passwordInput.type = "password";
    passwordInput.id = "password";
    passwordInput.placeholder = "password";
    loginContainer.appendChild(passwordInput);
    loginContainer.appendChild(document.createElement("br"));

    let submitButton = document.createElement("input");     // create submit button, goes to asyncLogin
    submitButton.type = "button";
    submitButton.value = "submit";
    submitButton.addEventListener("click", asyncLogin);
    loginContainer.appendChild(submitButton);

    let resetButton = document.createElement("input");      // create reset button to clear input strings
    resetButton.type = "button";
    resetButton.value = "reset";
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
  
 
  var down = document.getElementById("login_DOWN");
  var br = document.createElement("br");

function login_form() {
               
    // Create a form dynamically
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "#");
 
    // Create an input element for username
    var UN = document.createElement("input");
    UN.setAttribute("type", "text");
    UN.setAttribute("name", "username");
    UN.setAttribute("placeholder", "User Name");



    // Create an input element for password
    var PWD = document.createElement("input");
    PWD.setAttribute("type", "password");
    PWD.setAttribute("name", "password");
    PWD.setAttribute("placeholder", "Password");


    // create a submit button
    var s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("value", "Submit");
        
    // Append the full name input to the form
    form.appendChild(UN);
        
    // Inserting a line break
    form.appendChild(br.cloneNode());
        
    // Append the Password to the form
    form.appendChild(PWD);
    form.appendChild(br.cloneNode());
        

    // Append the submit button to the form
    form.appendChild(s);

    document.getElementsByTagName("body")[0]
    .appendChild(form);
}

function renderHome() {
    derenderPage();
    let homediv = document.createElement("div");

    let homebanner = document.createElement("h1");
    homebanner.id = "homebanner";
    homebanner.innerText = "Welcome to Mountain Project Lite";
    homediv.appendChild(homebanner);

    let homepic = document.createElement("img");
    homepic.src = "https://dl.dropboxusercontent.com/s/eamnmm5u5efedfq/chrome_1NHD4eZ2qx.jpg";
    homepic.id = "homepic";
    homediv.appendChild(homepic);

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

async function renderRoutes() {
    derenderPage();
    let allRoutes = await getAllRoutes();

    let routeContainer = document.createElement("div");
    routeContainer.id = "routeContainer";
    let routeList = document.createElement("ul");
    routeList.id = "routeList";
    for (i=0; i<allRoutes.length; i++){
        let routeItem = document.createElement("li");
        let routeDesc = document.createElement("ul");
        routeItem.innerText = `Route Name: ${allRoutes[i].name}`;
        let difficulty = document.createElement("li");
        difficulty.innerText = `Difficulty: ${allRoutes[i].difficulty}`;
        let length = document.createElement("li");
        length.innerText = `Length: ${allRoutes[i].length} feet`
        routeDesc.append(difficulty, length);
        routeItem.appendChild(routeDesc);
        routeList.appendChild(routeItem);
    }
    routeContainer.appendChild(routeList);
    document.querySelector("body").appendChild(routeContainer);

}

async function renderRoutesByLocation(location) {
    derenderPage();
    renderWeather(location.latlong);
    let nameHeader = document.createElement("h2");
    nameHeader.innerText = location.locationName;

    let locationRoutes = await getRoutesByLocation(location.id);
    let routeContainer = document.createElement("div");
    routeContainer.id = "routeContainer";
    let routeList = document.createElement("ul");
    routeList.id = "routeList";
    for (i=0; i<locationRoutes.length; i++){
        let routeItem = document.createElement("li");
        let routeDesc = document.createElement("ul");
        routeItem.innerText = `Route Name: ${locationRoutes[i].name}`;
        let difficulty = document.createElement("li");
        difficulty.innerText = `Difficulty: ${locationRoutes[i].difficulty}`;
        let length = document.createElement("li");
        length.innerText = `Length: ${locationRoutes[i].length} feet`
        routeDesc.append(difficulty, length);
        routeItem.appendChild(routeDesc);
        routeList.appendChild(routeItem);
    }
    routeContainer.appendChild(routeList);

    let backButton = document.createElement("input");
    backButton.type = "button";
    backButton.value = "Back";
    backButton.onclick = renderLocations;

    
    document.querySelector("body").append(backButton, nameHeader, routeContainer);
}

function renderRouteInfo() {

}

async function renderLocations() { //display list of locations
    derenderPage();
    let locations = await getLocations(); 
    let locationContainer = document.createElement("div");
    locationContainer.id = "locationContainer";
    let locationList = document.createElement("ul");
    locationList.id = "locationList";
    for (i=0; i<locations.length; i++){
        let locationItem = document.createElement("li");
        locationItem.id = `${locations[i].id}`;
        locationItem.innerText = `${locations[i].locationName}`;
        locationList.appendChild(locationItem);
    }
    locationList.onclick = async function(event) {
        let locationId = event.target.id;
        let location = await getLocationById(locationId);
        renderRoutesByLocation(location);
    }
    locationContainer.appendChild(locationList);
    
    document.querySelector("body").appendChild(locationContainer);
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

const getCurrentWeather = async function getWeatherByLocation(latlong) {
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