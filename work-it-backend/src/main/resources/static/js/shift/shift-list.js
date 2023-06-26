var id = 0;
var date = Date.now();

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
        "ajax": {
            'url': '/secure/shift/getlist',
            'data': {
                date: date
            },},
        "columns": [{
            data: "user",
            title: 'User',
            searchable: false,
            render: function (data) {
                return data.username;
            }
        },{
            data: "startDatetime",
            title: 'start',
            searchable: false
        },{
            data: "endDatetime",
            title: 'end',
            searchable: false
        }]
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

        table.column(0).search($("#startDatetime").val());

        table.draw();
    });


    $('.btn.clear').click(function () {
        clearForm($("#formCercar"));
        $(".btn.cercar").click();
        return false;
    });

    $('.btn.consultar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'shift/detall/' + id);
        }
    });

    $('.btn.modificar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'shift/detall/' + id);
        }
    });

    $('.btn.nou').click(function () {
        $(location).attr('href', urlaan + 'shift/detall/' + 0);
    });

    $('.btn.suprimir').click(function () {
        if (id > 0) {
            //$("#modalEliminar form").attr('action', urlaan + 'user/delete/' + id);
            //$("#modalEliminar").modal();
            $(location).attr('href', urlaan + 'shift/delete/' + id);
        }
        return false;
    });

    $('#tableLlistat tbody').on('dblclick', 'tr', function () {
        id = table.row(this).data().id;
        if (id > 0) {
            $(location).attr('href', urlaan + 'shift/detall/' + id);
        }
    });


})