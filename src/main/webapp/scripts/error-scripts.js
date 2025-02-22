
// This is to direct people to external link for help
document.addEventListener("DOMContentLoaded", function() {
    const button = document.getElementById("youTubeButton");
    if (button) {
        button.addEventListener("click", function() {
            window.location.href = "https://www.youtube.com/watch?v=01fRSHG3w5k";
        });
    } else {
        console.error("Button not found!");
    }
});