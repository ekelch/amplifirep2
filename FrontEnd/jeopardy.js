let score = 0;
let jeoAnswer = "";
let value = 0;

window.onload = () => {
    retrieveQuestion();
    document.getElementById("inputSubmit").onclick=reveal;
    document.getElementById("next").onclick=retrieveQuestion;
}

async function retrieveQuestion(){
    document.getElementById("qdiv").style.display='none';

    try {
        let response = await fetch('http://jservice.io/api/random');
            let data = await response.json();
            parseJeopardy(data);
            value = data[0].value;
            let submitButton = document.querySelector("#inputSubmit");
            submitButton.addEventListener("click", results);
            submitButton.addEventListener("click", reveal);
    } catch (error) {
        console.error(error);
    }
}

function parseJeopardy(data) {
    let category = document.getElementById("category");
    let answer = document.getElementById("answer");
    let question = document.getElementById("question");
    console.log(data[0]);
    category.innerText = 'Category: ' + data[0].category.title;
    answer.innerText = data[0].question;
    question.innerText = 'Answer: ' + data[0].answer;
    jeoAnswer =  data[0].answer;
}

function reveal() {
    document.getElementById("qdiv").style.display='block';
}

function results() {
    let userAnswer = document.querySelector("#inputAns").value;
    let rightWrong = document.querySelector("#rightwrong");
    if (userAnswer == jeoAnswer){
        score = score + value;
        rightWrong.innerText = `Correct Response, added ${value} to score!`;
    } else {
        score = score - value;
        rightWrong.innerText = `Incorrect Response, subtracted ${value} from score!`;
    }
    let displayScore = document.querySelector("#score");
    displayScore.innerText = `Your current score: ${score}`;
    value = document.getElementById("category");
}