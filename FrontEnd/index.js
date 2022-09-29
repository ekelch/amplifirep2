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
        navUsers.innerText = "Login";
        navUsers.addEventListener("click", renderLogin);
    let navRegister = document.createElement("LI");
        navRegister.innerText = "Register";
        navRegister.addEventListener("click", renderPostUser);
    let navLocations = document.createElement("LI");
        navLocations.innerText = "Locations";
        navLocations.addEventListener("click", renderLocations);
    let navSearch = document.createElement("LI");
        navSearch.innerText = "Search";
        navSearch.addEventListener("click", renderSearchHome);

    navList.append(navHome, navUsers,navRegister, navLocations, navSearch);

    navigation.appendChild(navList);
    document.querySelector("body").appendChild(navigation);
    document.body.classList.add("bg-light");
}

function renderPostUser(){
    derenderPage();
    let userPosetContainer = document.createElement("div");
    userPosetContainer.id = "userPost";
    userPosetContainer.className = "bannerUserPost";

    userPosetContainer.classList.add("form-group","text-center","justify-content-center");
    userPosetContainer.classList.add("justify-content-center","text-center","shadow", "bg-white", "rounded","w-50","mx-auto");


    let registerLabel = document.createElement("h2"); 
    registerLabel.innerText = "Register a New User";
    userPosetContainer.appendChild(registerLabel);
    registerLabel.classList.add("col-xs-2","w-50","mx-auto");

    let usernameLabel = document.createElement("h5");        // create username static label "Username"
    usernameLabel.innerText = "User Name :";
    userPosetContainer.appendChild(usernameLabel);

    let usernameInput = document.createElement("input");    // create username input variable "user1"
    usernameInput.id = "username";
    usernameInput.type = "text";
    usernameInput.placeholder = "username";
    userPosetContainer.appendChild(usernameInput);
    usernameInput.classList.add("form-control","col-xs-2","w-25","mx-auto");

    let passwordLabel = document.createElement("h5");        // create password static label "Password"
    passwordLabel.innerText = "Password";
    userPosetContainer.appendChild(passwordLabel);

    let passwordInput = document.createElement("input");    // create password input variable "pass1"
    passwordInput.type = "password";
    passwordInput.id = "password";
    passwordInput.placeholder = "password";
    userPosetContainer.appendChild(passwordInput);
    passwordInput.classList.add("form-control","col-xs-2","w-25","mx-auto");
    
    userPosetContainer.appendChild(document.createElement("br"));

    let emailLabel = document.createElement("h5");        // create email  label
    emailLabel.innerText = " email";
    userPosetContainer.appendChild(emailLabel);

    let emailInput = document.createElement("input");    // create email input variable 
    emailInput.type = "text";
    emailInput.id = "email";
    emailInput.placeholder = "email";
    userPosetContainer.appendChild(emailInput);
    emailInput.classList.add("form-control","col-xs-2","w-25","mx-auto");

    let descriptionLabel = document.createElement("h5");        // create description  label 
    descriptionLabel.innerText = "description";
    userPosetContainer.appendChild(emailLabel);

    let descriptionInput = document.createElement("input");    // create description input variable 
    descriptionInput.type = "text";
    descriptionInput.id = "description";
    descriptionInput.placeholder = "description";
    userPosetContainer.appendChild(descriptionInput);
    descriptionInput.classList.add("form-control","col-xs-2","w-25","mx-auto");

    let zipcodeLabel = document.createElement("h5");        // create zipcode  label "
    zipcodeLabel.innerText = "zip code";
    userPosetContainer.appendChild(zipcodeLabel);

    let zipcodeInput = document.createElement("input");    // create zipcode input variable
    zipcodeInput.type = "text";
    zipcodeInput.id = "zipcode";
    zipcodeInput.placeholder = "zip code";
    userPosetContainer.appendChild(zipcodeInput);
    zipcodeInput.classList.add("form-control","col-xs-2","w-25","mx-auto");

    let photourlLabel = document.createElement("h5");        // create photourl  label "Password"
    photourlLabel.innerText = "Photo URL :";
    userPosetContainer.appendChild(photourlLabel);

    let photourlInput = document.createElement("input");    // create photourl input variable 
    photourlInput.type = "text";
    photourlInput.id = "photourl";
    photourlInput.placeholder = "Photo URL";
    userPosetContainer.appendChild(photourlInput);
    photourlInput.classList.add("form-control","col-xs-2","w-25","mx-auto");

    let submitButton = document.createElement("input");     // create submit button, goes to asyncLogin
    submitButton.type = "button";
    submitButton.value = "Register";
    //submitButton.addEventListener("click", postUser);
    submitButton.classList.add("btn", "btn-primary","w-25","mx-auto","text-center");
    userPosetContainer.appendChild(submitButton);
    
    submitButton.onclick = async function() {
        let newUser;
        if (usernameInput.value != '' & passwordInput.value != '' & emailInput.value != '' & descriptionInput.value != '' & zipcodeInput.value != '') { 
            newUser = {
                username: usernameInput.value,
                password: passwordInput.value,
                email: emailInput.value,
                description: descriptionInput.value,
                zipcode: zipcodeInput.value,
                photourl: photourlInput.value
            }
           // console.log(newUser);
        await postUser(newUser);
        renderLogin();
        } else{ 
            renderPostUser();
        }
    }
    document.querySelector("body").appendChild(userPosetContainer);
}

function logout(){
    renderHome();
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
    //renderGalerie();
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
    renderGalerie();
}
function renderGalerie(){

    let images = [
        'https://dl.dropboxusercontent.com/s/z6mizq6hfjqk0c0/amarilloParadise-084.jpg',
        'https://dl.dropboxusercontent.com/s/njj3ux6by318d3j/IMG_4344.jpg',
        'https://dl.dropboxusercontent.com/s/s4aotdwvdfa9wjs/amarilloParadise-029.jpg',
        'https://dl.dropboxusercontent.com/s/2jrq7hcv9l0yb2c/amarilloParadise-111.jpg',
        'https://dl.dropboxusercontent.com/s/puow5fvtmejlxq0/IMG_4221.jpg',
        'https://dl.dropboxusercontent.com/s/ams56tolz9m5c24/IMG_4726.jpg',
        'https://dl.dropboxusercontent.com/s/rluw03et67757ve/IMG_3505.jpg'
    ];

    let i = 0;
    let selectionDiv = document.createElement("div");
    selectionDiv.className ="slider";
    let divImg = document.createElement("div");
    divImg.className ="img-box";

    let galeriPic1 = document.createElement("img");
    galeriPic1.src = "https://dl.dropboxusercontent.com/s/z6mizq6hfjqk0c0/amarilloParadise-084.jpg";
    galeriPic1.id = "galeriPic1";
    galeriPic1.className="slider-img";
    divImg.appendChild(galeriPic1);
/*
    let nextButton = document.createElement("input");      // create next button to slide
    nextButton.type = "button";
    nextButton.value = "Next =>";
    nextButton.className = "btn";
    nextButton.id = "nextb";
    nextButton.addEventListener("click", function(){
        if(i >= images.length-1) i = -1;
        i++;
        return setImg();			
    });
    
    let prevButton = document.createElement("input");      // create prev button to slide
    prevButton.type = "button";
    prevButton.value = " <= prev";
    prevButton.className = "btn";
    prevButton.id = "prevb";
    prevButton.addEventListener("click", function(){
        if(i <= 0) i = images.length;	
        i--;
        return setImg();				
    });*/
    function moveGalery() {
        if(i <= 0) i = images.length;	
        i--;	
        return setImg();	
        		
    }

    window.setInterval(moveGalery, 2000);

    function setImg(){
        return galeriPic1.setAttribute('src', images[i]);
    }

    selectionDiv.append(divImg);
    document.querySelector("body").appendChild(selectionDiv);
    console.log("5");



}

async function getUserById(id) {
    const path = '/api/v1/users/';
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

async function updateUser(user){
    const path = '/api/v1/users/';
    const url = urlBase + path + user.user_id;

    let updatedUser = {
        userId : user.user_id,
        username: user.username,
        password: user.password,
        description: user.description,
        email: user.email,
        photo_url: user.photo_url,
        zipcode: user.zipcode
    };
    console.log(updatedUser);
    try{
        await fetch(
            url,
            {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedUser)}
        )
    }catch(error){
        console.error(`Error is ${error}`);
    }
}

function renderUserUpdate(user) {
    derenderPage();
    let updateDiv = document.createElement("div");
    let updateBanner = document.createElement("h2");
    updateBanner.innerText = 'Hello, what information would you like to update?';
    let updateWarning = document.createElement("h3");
    updateWarning.innerText = 'You cannot edit your username or email.';
    let updateOptionList = document.createElement("ul");
    let userPass = document.createElement("li");
    userPass.innerText = 'Password';
    let userPic = document.createElement("li");
    userPic.innerText = 'User Photo URL';
    let userDescription = document.createElement("li");
    userDescription.innerText = 'User Description';
    let userZip = document.createElement("li");
    userZip.innerText = 'Zipcode';
    updateOptionList.append(userPass, userPic, userDescription, userZip);
    updateDiv.appendChild(updateOptionList);
    
    updateOptionList.onclick = async function(event) {
        if (event.target.innerText == 'Password') {
            derenderPage();
            let updateType = document.createElement("h3");
            updateType.innerText = "Update your Password:";
            let updateInput = document.createElement("input");
            updateInput.placeholder = 'New Password';
            let updateBtn = document.createElement("input");
            updateBtn.type = "Button";
            updateBtn.value = "Submit";
            updateBtn.addEventListener("click", function() {user['password'] = updateInput.value;})
            updateBtn.addEventListener("click", function() {updateUser(user)})
            updateBtn.addEventListener("click", function() {renderUserHome(user)})
            document.querySelector("body").append(updateType, updateInput, updateBtn);
        }

        if (event.target.innerText == 'User Photo URL') {
            derenderPage();
            let updateType = document.createElement("h3");
            updateType.innerText = "Enter your new photo URL:";
            let updateInput = document.createElement("input");
            updateInput.placeholder = 'New URL';
            let updateBtn = document.createElement("input");
            updateBtn.type = "Button";
            updateBtn.value = "Submit";
            updateBtn.addEventListener("click", function() {user['photo_url'] = updateInput.value;})
            updateBtn.addEventListener("click", function() {updateUser(user)})
            updateBtn.addEventListener("click", function() {renderUserHome(user)})
            document.querySelector("body").append(updateType, updateInput, updateBtn);
        }

        if (event.target.innerText == 'Zipcode') {
            derenderPage();
            let updateType = document.createElement("h3");
            updateType.innerText = "Enter your new Zipcode:";
            let updateInput = document.createElement("input");
            updateInput.placeholder = 'New Zipcode';
            let updateBtn = document.createElement("input");
            updateBtn.type = "Button";
            updateBtn.value = "Submit";
            updateBtn.addEventListener("click", function() {user['zipcode'] = updateInput.value;})
            updateBtn.addEventListener("click", function() {updateUser(user)})
            updateBtn.addEventListener("click", function() {renderUserHome(user)})
            document.querySelector("body").append(updateType, updateInput, updateBtn);
        }

        if (event.target.innerText == 'User Description') {
            derenderPage();
            let updateType = document.createElement("h3");
            updateType.innerText = "Enter your user description:";
            let updateInput = document.createElement("input");
            updateInput.placeholder = 'New bio';
            let updateBtn = document.createElement("input");
            updateBtn.type = "Button";
            updateBtn.value = "Submit";
            updateBtn.addEventListener("click", function() {user['description'] = updateInput.value;})
            updateBtn.addEventListener("click", function() {updateUser(user)})
            updateBtn.addEventListener("click", function() {renderUserHome(user)})
            document.querySelector("body").append(updateType, updateInput, updateBtn);
        }
    }
    document.querySelector("body").appendChild(updateDiv);

}

function renderUserHome(user){
    derenderPage();
    //Title User
    let userinfoDiv = document.createElement("div");
    let userbanner = document.createElement("h3");
    userbanner.innerText = 'Welcome, ' + user.username + '!';
    userbanner.classList.add("shadow-lg", "p-3", "mb-5", "bg-white", "rounded");
    userinfoDiv.classList.add("text-center");

    //div for colimg and coltext
    let userpictextDiv = document.createElement("div");
    //userpictextDiv.classList.add("raw");
    //div img or colimg
    let userpicDiv = document.createElement("div");
    let userpic = document.createElement("img");
    userpic.id = "userpic";
    userpic.src = user.photo_url;
    userpicDiv.appendChild(userpic);
    userpicDiv.classList.add("d-inline-block")
    //userpicDiv.classList.add("col-md-6");
    userpic.classList.add("rounded-circle","shadow-lg", "p-3", "mb-5", "bg-white","w-75","h-75");
    userpictextDiv.appendChild(userpicDiv);

    //div text or coltext
    let usertextinfoDiv = document.createElement("div");
    let userDescription = document.createElement("p");
    userDescription.innerText = 'Bio: ' + user.description;
    usertextinfoDiv.appendChild(userDescription);
    let userZip = document.createElement("p");
    userZip.innerText = 'Zipcode: ' + user.zipcode;
    usertextinfoDiv.appendChild(userZip);
    let userEmail = document.createElement("p");
    userEmail.innerText = 'Email: ' + user.email;
    usertextinfoDiv.appendChild(userEmail);
    usertextinfoDiv.classList.add("d-inline-block","shadow-lg", "p-3", "mb-5", "bg-white", "rounded")
    //usertextinfoDiv.classList.add("col-md-6");
    userpictextDiv.appendChild(usertextinfoDiv);

    let updateBtn = document.createElement("input");
    updateBtn.type = "Button";
    updateBtn.value = "Update User";
    updateBtn.classList.add("btn", "btn-info");
    usertextinfoDiv.appendChild(updateBtn);
    updateBtn.addEventListener("click", function() {renderUserUpdate(user)});

    userinfoDiv.append(userbanner,userpictextDiv);
    document.querySelector("body").appendChild(userinfoDiv);
}

async function renderRoutesByLocation(location) {
    derenderPage();
    let nameHeader = document.createElement("h2");
    nameHeader.innerText = location.locationName;
    nameHeader.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded");

    let locationRoutes = await getRoutesByLocation(location.id);
    let routeTable = document.createElement("table");
    routeTable.id = "routeTable";
    routeTable.classList.add("table", "table-striped");
    routeTable.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");
    let routeHeader = document.createElement("tr");
    let thName = document.createElement("th");
    thName.innerText = "Route Name";
    let thDifficulty = document.createElement("th");
    thDifficulty.innerText = "Difficulty";
    let thLength = document.createElement("th");
    thLength.innerText = "Length (feet)";
    routeHeader.append(thName, thDifficulty, thLength);
    routeTable.append(routeHeader);

    for (i=0; i<locationRoutes.length; i++){
        let routeItem = document.createElement("tr");
        let tdName = document.createElement("td");
        tdName.innerText = locationRoutes[i].name;
        tdName.id = locationRoutes[i].route_id;
        let tdDifficulty = document.createElement("td");
        tdDifficulty.innerText = locationRoutes[i].difficulty;
        let tdLength = document.createElement("td");
        tdLength.innerText = locationRoutes[i].length;
        routeItem.append(tdName, tdDifficulty, tdLength);
        routeTable.append(routeItem);
    }

    routeTable.onclick = async function(event) {
        routeId = event.target.id;
        let route = await getRouteById(routeId);
        renderRoute(route);
    }

    let backButton = document.createElement("input");
    backButton.type = "button";
    backButton.value = "Back ->";
    backButton.classList.add("btn", "btn-info","text-center","justify-content-center");
    backButton.onclick = renderLocations;

    let newRouteButton = document.createElement("input");
    newRouteButton.type = "button";
    newRouteButton.value = "Add a new route to this location";
    newRouteButton.classList.add("btn", "btn-info","text-center","justify-content-center");
    newRouteButton.onclick = function() {renderAddRoute(location)};

    document.querySelector("body").append(backButton, nameHeader, routeTable, newRouteButton);
    renderWeather(location.latlong);
}

function renderSearchHome() {
    derenderPage();
    let searchHeader = document.createElement("h2");
    searchHeader.innerText = "Search Routes By:"
    searchHeader.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded");
    document.querySelector("body").append(searchHeader);
    let searchDiv = document.createElement("div");
    let searchTypes = ['Name', 'Rating', 'Difficulty'];
    let listeners = [searchByName, searchByRating, searchByDifficulty];
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
    let allRoutes = await getAllRoutes();
    let compressRoutes = allRoutes;
    for (let route of compressRoutes) {
        route['name'] = route['name'].replaceAll(/[^a-zA-Z0-9]/g, "").toLowerCase();
    }
    let nameInput = document.querySelector("#NameSearch").value;
    let inputCompress = nameInput.replaceAll(/[^a-zA-Z0-9]/g, "").toLowerCase();
    for (let route of compressRoutes) {
        if (route.name == inputCompress){
            console.log(route);
            let actualRoute = await getRouteById(route.route_id)
            await renderRoute(actualRoute);
        }
    }
}
let bad = false;
const renderAddRoute = async function(location) {
    derenderPage();
    let locationId = location.id;
    let locationHeader = document.createElement("h2");
    locationHeader.innerText = `Submit a new route to ${location.locationName}`;
    let tryAgain = document.createElement('h3');
    tryAgain.innerText = "Please enter all required fields and submit again.";
    if (bad){
        document.querySelector("body").append(tryAgain);
    }
    let newRoute;
    let newRouteDiv = document.createElement("div");
    let newRouteName = document.createElement("input");
    newRouteName.placeholder = "Route Name";
    let newRouteDifficulty = document.createElement("input");
    newRouteDifficulty.placeholder = "Difficulty";
    let newRouteLength = document.createElement("input");
    newRouteLength.placeholder = "Length (feet)";
    let newRoutePhotoUrl = document.createElement("input");
    newRoutePhotoUrl.placeholder = "Photo URL (Optional)";
    let submitButton = document.createElement("input");
    submitButton.type = "Button";
    submitButton.value = "Submit New Route";

    newRouteDiv.append(locationHeader, newRouteName, newRouteDifficulty, newRouteLength, newRoutePhotoUrl, submitButton);
    document.querySelector("body").append(newRouteDiv);

    submitButton.onclick = async function() {
        if (newRouteName.value != '' & newRouteDifficulty.value != '' & newRouteLength.value != '') { 
            newRoute = {
                name: newRouteName.value,
                difficulty: newRouteDifficulty.value,
                length: newRouteLength.value
            }
            if (newRoutePhotoUrl.value != ''){
                newRoute.photo_url = newRoutePhotoUrl.value;
            }
        await postRoute(newRoute, location.id);
        } else {
            bad = true;
            renderAddRoute(location);
        }
    };
    submitButton.addEventListener("click", async function() {renderRoutesByLocation(location)});
    
}

const searchByRating = async function() {
    console.log('5');
}

const searchByDifficulty = async function() {
    let diffInput = document.querySelector("#DifficultySearch").value;
    derenderPage();
    let allRoutes = await getAllRoutes();
    let routeTable = document.createElement("table");
    routeTable.id = "routeTable";
    routeTable.classList.add("table", "table-striped");
    routeTable.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");
    let routeHeader = document.createElement("tr");
    let thName = document.createElement("th");
    thName.innerText = "Route Name";
    let thLocation = document.createElement("th");
    thLocation.innerText = "Location";
    let thDifficulty = document.createElement("th");
    thDifficulty.innerText = "Difficulty";
    let thLength = document.createElement("th");
    thLength.innerText = "Length (feet)";
    routeHeader.append(thName, thLocation, thDifficulty, thLength);
    routeTable.append(routeHeader);

    for (let route of allRoutes){
        if (route.difficulty==diffInput){
            let routeItem = document.createElement("tr");
            let tdName = document.createElement("td");
            tdName.innerText = route.name;
            tdName.id = route.route_id;
            let tdLocation = document.createElement("td");
            tdLocation.innerText = route.location.locationName;
            let tdDifficulty = document.createElement("td");
            tdDifficulty.innerText = route.difficulty;
            let tdLength = document.createElement("td");
            tdLength.innerText = route.length;
            routeItem.append(tdName, tdLocation, tdDifficulty, tdLength);
            routeTable.append(routeItem);
        }
    }

    routeTable.onclick = async function(event) {
        routeId = event.target.id;
        let route = await getRouteById(routeId);
        renderRoute(route);
    }

    let diffDisplay = document.createElement("h2");
    diffDisplay.innerText = `Routes of difficulty: ${diffInput}`;
    document.querySelector("body").append(routeTable);
}

const renderRoute = async function(route) {
    derenderPage();
    console.log(route);
    let routeDiv = document.createElement("div");
        let routeHeader = document.createElement("h2");
        routeHeader.innerText = `${route.name}`;
        let routeImage;
        if (route.photo_url!=null){
            routeImage = document.createElement("img");
            routeImage.src = `${route.photo_url}`;
        }
        let routeDifficulty = document.createElement("p");
        routeDifficulty.innerText = `YDS Rating: 5.${route.difficulty}`;
        let routeLength = document.createElement("p");
        routeLength.innerText = `Length: ${route.length} feet`;

    routeDiv.append(routeHeader)
    if (route.photo_url!=null)
        routeDiv.append(routeImage);
    routeDiv.append(routeDifficulty, routeLength);
    
    let locationDiv = document.createElement("div");
        let locationName = document.createElement("h2");
        locationName.innerText = `Location: ${route.location.locationName}`;
        let latlong = document.createElement("p");
        latlong.innerText = `Latitude/Longitude: ${route.location.latlong}`;

    locationDiv.append(locationName, latlong);
        
    document.querySelector("body").append(routeDiv, locationDiv);
    renderWeather(route.location.latlong);
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
        let locationId = event.target.id;
        let location = await getLocationById(locationId);
        renderRoutesByLocation(location);
    }
    
    document.querySelector("body").appendChild(locationTable);
}

async function renderWeather(latlong) {
    let weather = await getCurrentWeather(latlong);

    let weatherDiv = document.createElement("div");
    weatherDiv.classList.add("form-group","text-center","justify-content-center");
    weatherDiv.classList.add("justify-content-center","text-center","shadow", "p-3", "mb-5", "bg-white", "rounded","w-50","mx-auto");

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

    let tempF = document.createElement("p");
    tempF.innerText = `Current Temperature (degrees Fahrenheit): ${weather.current.temp_f}`;
    weatherDiv.appendChild(tempF);

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

const postUser = async function(user){
    const path = '/api/v1/users/';
    const url = urlBase + path;
    try {
        let response = await fetch(
            url,
            {
                method: "POST",
                headers: new Headers({'content-type':'application/json'}),
                body: JSON.stringify(user)
            })
            let data = await response.json();
            return data;

    } catch (error) {
        console.error(`Error is ${error}`)
    }

}

const postRoute = async function(route, locationId){
    const path = '/api/v1/routes/';
    const url = urlBase + path + locationId;
    try {
        let response = await fetch(
            url,
            {
                method: "POST",
                headers: new Headers({'content-type':'application/json'}),
                body: JSON.stringify(route)
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