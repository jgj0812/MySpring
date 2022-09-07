var main = {
    init: function() {
        var _this = this;
        // document.getElementById('btn-save')
        // document.getQuerySelector('#btn-save')
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },

    save: function() {
        // 작성자 이름이 한글이라는 것을 보장하는 Regular Expression 검사
        // char_length()
        let regexp = /^[가-힣]{2, 4}$/;
        if (!regexp.test($('#author').val())) {
            alert('작성자는 한글로 2자에서 4자만 가능합니다.');
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
};

main.init();