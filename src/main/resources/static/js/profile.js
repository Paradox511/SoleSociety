function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
	console.log(value);
	console.log(parts);
}
function fetchUserProfile() {
	
    fetch('/api/users/profile')
    
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to fetch user profile');
        }
        return response.json();
    })
    .then(userData => {
        displayUserProfile(userData);
    })
    .catch(error => {
        console.error('Error fetching user profile:', error);
    });
}
function displayUserProfile(userData) {
    document.getElementById('profile-name').textContent = userData.fullName;
    document.getElementById('profile-email').textContent = userData.email;
    document.getElementById('profile-username').textContent = userData.username;
    // Populate other fields
}
window.onload = fetchUserProfile;
