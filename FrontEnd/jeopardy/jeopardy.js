window.onload = () => {
    retrieveQuestion();
    document.getElementById("reveal").onclick=reveal;
    document.getElementById("next").onclick=retrieveQuestion;
}

function retrieveQuestion(){
    document.getElementById("qdiv").style.display='none';
    let response = fetch('http://jservice.io/api/random');
    if (response.ok) {
        let data = response.json();
        parseJeopardy(data);
    } else {    
}
alert('HTTP-Error ' + response.status);
}

function parseJeopardy(data) {
    let category = document.getElementById("category");
    let answer = document.getElementById("answer");
    let question = document.getElementById("question");

    if (data.status == 200){
        category.innerHTML = data.category;
        answer.innerHTML = data.answer;
        question.innerHTML = data.question;
    }
}

function reveal() {
    document.getElementById("qdiv").style.display='block';
}