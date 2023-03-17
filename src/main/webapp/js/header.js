/**
 * 
 */
//not working
$(document).ready(function() {
  // add click event to all nav-link elements
  $('.nav-link').click(function() {
    // remove active class from all nav-item elements
    $('.nav-item').removeClass('active');
    // add active class to the clicked nav-item element
    $(this).parent().addClass('active');
  });
});
