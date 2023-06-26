var id = 0;

$(document).ready(function () {
    table = $("#tableLlistat").DataTable({
        "paging": true,
        "lengthChange": false,
        "info": true,
        "autoWidth": false,
        "dom": "ltipr", //https://datatables.net/forums/discussion/21328/how-to-hide-filter-and-table-information
        "language": {
            "url": "/json/datatables_en_EN.json"
            //"url": "/json/datatables_es_ES.json"
        },
        "processing": true,
        "serverSide": true,
        "order": [[0, "asc"]],
        "ajax": "/secure/project/getlist",
        "columns": [{
            data: "id",
            title: 'Id',
            searchable: false
        },{
            data: "title",
           /* title: 'Title'*/
        },{
            data: "description",
            title: 'Description',
            searchable: false
        },{
            data: "estimatedHours",
            title: 'Estimated hours',
            searchable: false
        },{
            data: "totalHours",
            title: 'Total hours',
            searchable: false
        },/*{
            data: "manager",
            title: 'Manager',
            searchable: false,
            render: function (data) {
                UserWebService userWebService = UserWebService;
                // Access the object using the returned ID
                var user = getUserById(data);

                // Customize the display of the object
                return user.username;
            }
        }*/]
    });

    $('#tableLlistat tbody').on('click', 'tr', function () {
        id = 0;
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            id = table.row(this).data().id;
            $(this).addClass('selected');
        }
    });

    /*Botons*/
    $('.btn.cercar').click(function () {

        table.column(1).search($("#title").val());

        table.draw();
    });


    $('.btn.clear').click(function () {
        clearForm($("#formCercar"));
        $(".btn.cercar").click();
        return false;
    });

    $('.btn.consultar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'project/detall/' + id);
        }
    });

    $('.btn.modificar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'project/detall/' + id);
        }
    });

    $('.btn.nou').click(function () {
        $(location).attr('href', urlaan + 'project/detall/' + 0);
    });

    $('.btn.suprimir').click(function () {
        if (id > 0) {
            //$("#modalEliminar form").attr('action', urlaan + 'user/delete/' + id);
            //$("#modalEliminar").modal();
            $(location).attr('href', urlaan + 'project/delete/' + id);
        }
        return false;
    });

    $('#tableLlistat tbody').on('dblclick', 'tr', function () {
        id = table.row(this).data().id;
        if (id > 0) {
            $(location).attr('href', urlaan + 'project/detall/' + id);
        }
    });


})