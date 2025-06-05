
export function removeImageBlurOnLoad() {

document.addEventListener('DOMContentLoaded', function() {
        // Get all elements with the class 'blur-image'
        const images = document.getElementsByClassName('blur-image');
     console.log("workinggggg");
     console.log("images"+images);
        // Loop through each image
        for (let i = 0; i < images.length; i++) {
            // Check if the image is already loaded
            if (images[i].complete) {
                images[i].style.filter = 'none'; // Remove the blur if already loaded
            } else {
                // Add load event listener
                images[i].addEventListener('load', function() {
                    this.style.filter = 'none'; // Removes the blur once the image is fully loaded
                });
            }
        }
    });
}
    removeImageBlurOnLoad();
  