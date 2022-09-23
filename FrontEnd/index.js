let banner = document.querySelector("#banner");

//window.addEventListener("load", renderLogin)
function derenderPage(){
    document.querySelector("body").innerHTML = "";
}
function renderLogin(){
    let loginContainer = document.createElement("div");
    loginContainer.id = "login";
    loginContainer.className = "bannerLogin";

    let usernameInput = document.createElement("input");

    usernameInput.id = "username";
    let usernameLabel = document.createElement("p");
    usernameLabel.innerText = "Username";

    loginContainer.appendChild(usernameLabel);

    usernameInput.type = "text";
    usernameInput.placeholder = "username";

    loginContainer.appendChild(usernameInput);
    loginContainer.appendChild(document.createElement("br"));

    let passwordLabel = document.createElement("p");
    passwordLabel.innerText = "Password";

    loginContainer.appendChild(passwordLabel);

    let passwordInput = document.createElement("input");

    passwordInput.type = "password";
    passwordInput.id = "password";

    loginContainer.appendChild(passwordInput);
    loginContainer.appendChild(document.createElement("br"));

    let submitButton = document.createElement("input");

    submitButton.type = "button";
    submitButton.value = "submit";

    submitButton.addEventListener("click", asyncLogin);

    loginContainer.appendChild(submitButton);

    let resetButton = document.createElement("input");

    resetButton.type = "button";
    resetButton.value = "reset";

    resetButton.addEventListener("click", function(){
        usernameInput.value = "";
        passwordInput.value = "";
    });

    loginContainer.appendChild(resetButton);

    document.querySelector("body").appendChild(loginContainer);
}

function renderHomepage(data){
    derenderPage();
    let userbanner = document.createElement("h1");
    userbanner.innerText = data.username;

    document.querySelector("body").appendChild(userbanner);
}

async function asyncLogin(){
    let userInput = document.querySelector("#username").value;
    let passInput = document.querySelector("#password").value;

    const url = `http://localhost:8080/api/v1/users/login;

    let loginObj = {
        username: userInput,
        password: passInput
    };

    try{
        let response = await fetch(
            url,
            {
                method: "POST",
                headers: new Headers({
                    'content-type':'application/json'
                }),
                body: JSON.stringify(loginObj)}
        )

        let data = await response.json();
        console.log(data);
        renderHomepage(data);
    }catch(error){
        console.error(`Error is ${error}`);
    }
}
  
  
  
  
  
  
  
  
  
  
  var down = document.getElementById("login_DOWN");
  // Create a break line element
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