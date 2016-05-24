/** @jsx React.DOM */
var Table = React.createClass({
    /**
     * 初期化処理
     */
    getInitialState() {
        return {
            result: []
        }
    },

    /**
     * コンポーネントがマウントされた時の処理
     */
    componentDidMount(){
        var request = window.superagent;
        var _this = this;
        request.get("http://127.0.0.1:8080/todo/v1/items")
               .end(function(err, res){
                    _this.setState({result: res.body.result});
               })
    },

    /**
     * レンダリング処理
     */
    render() {
        // jsonの配列を要素ごとに処理
        var rowList = this.state.result.map((todo) => {
           // 詳細ボタンURL
           var detailUrl = "/todo/"+ todo.id;
           // 編集ボタンURL
           var updateUrl = "/todo/"+ todo.id + "/edit";
           // 削除ボタンURL
           var deleteUrl = "/todo/"+ todo.id;
           var limitDate = todo.limit_date.year + "/" + todo.limit_date.month + "/" + todo.limit_date.day;
           // テーブルROW
           return(
            <tr key={todo.id}>
                <td>{todo.id} </td>
                <td>{todo.title} </td>
                <td>{todo.tag} </td>
                <td>{todo.priority} </td>
                <td>{limitDate} </td>
                <td>{todo.content} </td>
                <td>{todo.status} </td>
                <td>
                    <a href={detailUrl} className="btn btn-default">詳細</a>
                    <a href={updateUrl} className="btn btn-info">編集</a>
                    <a data-method="delete" data-confirm="本当に削除してよろしいですか?"
                    href={deleteUrl} rel="nofollow" className="btn btn-danger">削除</a>
                </td>
            </tr>
           );
        });
        // ヘッダー
        return (
        <table border="1" className="table table-bordered">
            <thead id ="thead">
                <tr>
                    <th>ID</th><th>件名</th><th>タグ</th><th>優先度</th><th>期限</th><th>内容</th><th>ステータス</th><th></th>
                </tr>
            </thead>
            <tbody id="tbody">
                {rowList}
            </tbody>
        </table>
        );
    }
});
// 想定しているdivがある場合のみレンダリング
if(document.getElementById("tableGroup")) {
    React.render(<Table />, document.getElementById("tableGroup"));
}