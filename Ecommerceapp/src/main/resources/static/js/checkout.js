
document.addEventListener('DOMContentLoaded', function() {
    const buyNowButtons = document.querySelectorAll('.buy-now-btn');
    const ratings=document.querySelector("#productRating");
    buyNowButtons.forEach(function(button) {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // Prevent default anchor click behavior
  console.log("button clicked");
            // Get the parent card element
            const card = event.target.closest('.card');

            // Extract product data from the card's data attributes
            const productData = {
                id: card.getAttribute('data-id'),
                brand: card.getAttribute('data-brand'),
                desc: card.getAttribute('data-desc'),
                price: card.getAttribute('data-price'),
                rating: card.getAttribute('data-rating'),
                imageUrl: card.getAttribute('data-image-url')
            };

            // Store the product data in localStorage or sessionStorage
            localStorage.setItem('selectedProduct', JSON.stringify(productData));

            // Optionally, you can redirect to the checkout page
            window.location.href = '/checkout';  // Change to your actual checkout page URL
        });
    });
    
    
     
});

