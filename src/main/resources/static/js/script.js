console.log("WELCOME");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});


function changeTheme(){
    changePageTheme(currentTheme, "");
    // set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click", (event) => {
        let oldTheme = currentTheme;
        if(currentTheme === "dark"){
            currentTheme = "light";
        }else{
            currentTheme = "dark";
        }
        changePageTheme(currentTheme, oldTheme);
    })
}

// set theme in localstorage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme
function changePageTheme(theme, oldTheme){
    //updating in localstorage
    setTheme(currentTheme);

    //removing the current theme
    document.querySelector("html").classList.remove(oldTheme);

    //set the current theme
    document.querySelector("html").classList.add(theme);

    // change the text of button
    document
        .querySelector("#theme_change_button")
        .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}