// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
    order: [[6, "desc"]],
  });
});
