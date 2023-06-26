var id = 0;

$(document).ready(function () {
    table = $("#tableLlistat").DataTable({
        "paging": true,
        "lengthChange": false,
        "info": true,
        "autoWidth": false,
        "dom": "ltipr", //https://datatables.net/forums/discussion/21328/how-to-hide-filter-and-table-information
        "language": {
            //"url": "/json/datatables_ca_CA.json"
            "url": "/json/datatables_en_EN.json"
        },
        "processing": true,
        "serverSide": true,
        "order": [[0, "asc"]],
        "ajax": "/secure/user/getlist",
        "columns": [
            {data:'id'},
            {
            data: "fullName",
            //title: 'Full Name',

        },{
            data: "username",
            title: 'Username',
            searchable: false
        },{
            data: "email",
            title: 'Email',
            searchable: false
        },{
            data: "administrator",
            title: 'Administrator',
            searchable: false,
            render: function(data, type, row) {
                if (type === 'display') {
                    // Customize the rendering based on the boolean value
                    if (data) {
                        return "Yes";
                        //return '<span class="badge badge-success">Active</span>';
                    } else if (!data){
                        return "No";
                        //return '<span class="badge badge-danger">Inactive</span>';
                    }
                }
                return data; // Return the raw boolean value for other types
            }
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

        table.column(1).search($("#fullName").val());

        table.draw();
    });


    $('.btn.clear').click(function () {
        clearForm($("#formCercar"));
        $(".btn.search").click();
        return false;
    });

    $('.btn.consultar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'user/detall/' + id);
        }
    });

    $('.btn.modificar').click(function () {
        if (id > 0) {
            $(location).attr('href', urlaan + 'user/detall/' + id);
        }
    });

    $('.btn.nou').click(function () {
        $(location).attr('href', urlaan + 'user/detall/' + 0);
    });

    $('.btn.suprimir').click(function () {
        if (id > 0) {
            //$("#modalEliminar form").attr('action', urlaan + 'user/delete/' + id);
            //$("#modalEliminar").modal();
            $(location).attr('href', urlaan + 'user/delete/' + id);
        }
        return false;
    });

    $('#tableLlistat tbody').on('dblclick', 'tr', function () {
        id = table.row(this).data().id;
        if (id > 0) {
            $(location).attr('href', urlaan + 'user/detall/' + id);
        }
    });


})