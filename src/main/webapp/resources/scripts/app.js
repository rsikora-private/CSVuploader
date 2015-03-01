/**
 * Created by Robert on 2015-02-26.
 */

$( document ).ready(function() {

    Handlebars.registerHelper("prettifyDate", function(timestamp) {
        var date = new Date(timestamp.millis);
        return date.toLocaleDateString();
    });

    $(document).ajaxError(function(event, request, settings){
        splashScreen.turnOff();
        alertify.error(request.responseText);
    });

    $('form').on('submit', makePreview);
    $('#btn-import').click(importData);


    function makePreview(event){
        splashScreen.turnOn();
        event.stopPropagation();
        event.preventDefault();
        var formData = new FormData($(this)[0]);
        $.ajax({
            url: 'uploadpreview',
            type: 'POST',
            data: formData,
            contentType: false,
            dataType: "json",
            accepts: {text: "application/json"},
            enctype: 'multipart/form-data',
            processData: false,
            success: function (response) {
                var source   = $("#preview").html();
                var template = Handlebars.compile(source);
                var data = { workers: response};
                $("#preview-placeholder").html(template(data));
                $("#btn-import").removeAttr("disabled");
                splashScreen.turnOff();
            }
        });
    }

    function importData(){
        splashScreen.turnOn();
        var formData = new FormData($('form')[0]);
        $.ajax({
            url: 'import',
            type: 'POST',
            data: formData,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (response) {
                splashScreen.turnOff();
                alertify.success("Successfully imported !");
            }
        });
    }


    var splashScreen  = {
        turnOn: function(){
            $("#splash").css('display', 'block');
        },
        turnOff: function(){
            $("#splash").css('display', 'none');
        }
    };


});



