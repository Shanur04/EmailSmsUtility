function createImage() {
  var shower = document.querySelector('.shower');
  var image = document.createElement('img');
  image.src = '/images/emailImage.png';
  shower.appendChild(image);
  setPositionAndRotation(image);
}

function setPositionAndRotation(image) {
  var randomSize = Math.floor(Math.random() * 100) + 50; // Random size between 20px and 100px
  var randomRotation = Math.floor(Math.random() * 360); // Random rotation between 0deg and 360deg
  image.style.width = randomSize + 'px';
  image.style.transform = 'rotate(' + randomRotation + 'deg)';

  var randomDelay = Math.random() * 3; // Random delay between 0s and 3s
  image.style.animationDelay = randomDelay + 's';

  var randomPosition = Math.floor(Math.random() * 100); // Random position between 0% and 100%
  image.style.left = randomPosition + '%';
}

function createMultipleImages(count) {
  for (var i = 0; i < count; i++) {
    createImage();
  }
}

createMultipleImages(20); // Create 10 images in the shower
