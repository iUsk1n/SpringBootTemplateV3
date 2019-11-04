SBT.namespace('SBT.Users.List');

SBT.Users.List = function(urls){
    this.ENABLED_ENABLE  = 'true';
    this.ENABLED_DISABLE = 'false';

    this.urls = urls;

    this.dataTables = null;

    this.jqXHR = null
    this.usersShow = null;
}

SBT.Users.List.prototype.init = function() {
    var my = this;

    my.dataTables = $('#table-users').DataTable($.extend({}, dataTableDefaultSettings, {
        processing: true,
        serverSide: true,
        ajax: my.urls.ajaxsource,
        order: [[0, 'asc']],
        columns: [
            {data: 'username'},
            {data: 'fullname'},
            {data: 'enabledString'},
            {data: 'groupsString'},
            {data: 'comment'},
            {data: 'updatedDateString'},
        ],
        rowCallback: function(row, data) {
            $(row).addClass('dataTables-row');
            if ($('#user-detail-id').val() === data.id) {
                $(row).addClass('dataTables-row__selected');
            }
            $(row).on('click', function() {
                $('.dataTables-row__selected').removeClass('dataTables-row__selected');
                $(this).addClass('dataTables-row__selected');

                if (my.jqXHR) {
                    my.jqXHR.abort();
                    my.jqXHR = null;
                }
                $('#detail').empty();
                my.usersShow = null;

                my.getDetail.call(my, data.id)
                    .done(function() {
                        my.usersShow = new SBT.Users.Show({
                            updateFrom: my.urls.updateForm,
                            'delete': my.urls['delete']
                        });

                        my.usersShow.init();
                    });

                return false;
            });
        }
    }));

    $('#btn-table-users-search').on('click', function() {
        var enabledEnable = $('#table-search-enabled__enable').prop('checked');
        var enabledDisable = $('#table-search-enabled__disable').prop('checked');
        var enabled = '';
        if (enabledEnable && !enabledDisable) {
            enabled = my.ENABLED_ENABLE;
        } else if (!enabledEnable && enabledDisable) {
            enabled = my.ENABLED_DISABLE;
        }

        my.dataTables
            .column(0).search($('#table-search-username').val())
            .column(1).search($('#table-search-fullname').val())
            .column(2).search(enabled)
            .column(3).search($('#table-search-groups').val())
            .column(4).search($('#table-search-comment').val())
            .draw();
    });
};

SBT.Users.List.prototype.getDetail = function(id) {
    var my = this;

    var deferred = new $.Deferred;

    my.jqXHR = $.ajax({
        url: my.urls.show + id,
        dataType: 'html'
    }).done(function(data) {
        $('#detail').html($('<div>').append(data).find('main')[0].innerHTML);
        deferred.resolve();
    }).fail(function(jqXHR, textStatus, errorThrown) {
        if(textStatus !== 'abort') {
            console.log(my.urls.show + id + ': ' + textStatus);
        }
        deferred.reject();
    }).always(function() {
        my.jqXHR = null;
    });

    return deferred.promise();
}


SBT.namespace('SBT.Users.Show');

SBT.Users.Show = function(urls){
    this.urls = urls;
}

SBT.Users.Show.prototype.init = function() {
    var my = this;

    //
}