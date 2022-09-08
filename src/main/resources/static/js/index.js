var main = {
    init: function() {
        var _this = this;
        // document.getElementById('btn-save')
        // document.getQuerySelector('#btn-save')
        $('#btn-save').on('click', function() {
            _this.save();
        });

        /*
            C017 update
         */
        $('#btn-update').on('click', function() {
            _this.update();
        });

        /*
            C019 delete
         */
        $('#btn-delete').on('click', function() {
            _this.delete();
        });

        /*
            D020 rec
        */
        $('#btn-rec').on('click', function() {
            _this.rec();
        });
    },

    save: function() {
        // 작성자 이름이 한글이라는 것을 보장하는 Regular Expression 검사
        // char_length()
        let regexp = /^[가-힣]{2,4}$/;
        if(!regexp.test($('#author').val()))
        {
            alert('작성자는 한글로 2~4자만 가능합니다.');
            return;
        }

        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        })
        .done(function(){
            alert('글이 등록되었습니다.');
            location.href='/';
        })
        .fail(function(error){
            alert('글쓰기 에러입니다.');
            alert(JSON.stringify(error));
        })
    },
    // C017 계속  /api/v1/posts/1
    // 주의 : 함수들이 콤마(,)로 나열되야 한다.
    // 처리가 끝나면, 수정하기 화면을 뿌려주는 IndexController를 처리
    update: function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        })
        .done(function(){
            alert('글이 변경되었습니다.');
            location.href='/';
        })
        .fail(function(error){
            alert('글수정 에러입니다.');
            alert(JSON.stringify(error));
        })
    },
    // C019 계속, /api/v1/posts/1 : DELETE
    delete: function() {
        if (confirm('삭제된 데이터는 복구할 수 없습니다.\n 정말 삭제하시겠습니까?'))
        {
            var id = $('#id').val();

            $.ajax({
                type: 'DELETE',
                url: '/api/v1/posts/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            })
            .done(function(){
                alert('글이 삭제되었습니다.');
                location.href='/';
            })
            .fail(function(error){
                alert('글삭제 에러입니다.');
                alert(JSON.stringify(error));
            })
        }
    },
    // D020 rec
    rec: function() {
        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/rec/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        })
        .done(function(){
            alert('글이 추천되었습니다.');
            let idRec = $('#idRec');
            let newRec = parseInt(idRec.html()) + 1;
            idRec.html(newRec);
            //location.href='/';
        })
        .fail(function(error){
            alert('글추천 에러입니다.');
            alert(JSON.stringify(error));
        })
    },
};

main.init();