**☆社員管理システム☆**  
今更ですがEmployeeでした・・・。

**2019/09/13　プロジェクト作成**  
　[DBの中身]  
<details>
<summary>Empテーブル</summary>
<div>

　　　emp_id →int 自動採番 主キー  
　　　emp_pass →nvarchar(Max) NN  
　　　emp_name →nvarchar(30) NN  
　　　gendere →int NN  
　　　address →nvarchar(60) NN  
　　　birthday →Date NN  
　　　role →nvarchar(20) NN  
　　　dept_id →int NN Departmentのdept_idと関連付け  
　　　enable →bit NN 初期値true  
</div>
</details>

<details>
<summary>Departmentテーブル</summary>
<div>

　　　dept_id →int 自動採番 主キー  
　　　dept_name →nvarchar(15) NN  
</div>
</details>  


**メモ**  
<details>
<summary>・2019/09/13</summary>
<div>

indexページから一覧表示への画面遷移完了。  
ログイン処理はまだ実装していないので、ログインは素通り。  
</div>
</details>  


<details>
<summary>・2019/09/17</summary>
<div>

EmpMapper作成、Emp.javaにdeptIdのフィールド追加、countメソッド不要そうなので削除しました。  
↓権限(role)の表示はService内で変更でき、性別(gender)の変換方法がいくつか思い当たりましたが正解がわかりません。  
**◇疑問◇**  
要件定義ではgenderの値はint型で扱っており、Vで表示させる時は"男性""女性"として表示させる。  
<details>
<summary>考えた方法</summary>
<div>

方法1：V側のif文処理でgenderの値に応じて表示を変更する。  
方法2：EmpクラスにString型のフィールドを追加し、Serviceでif文処理で変更した値を格納する。  
方法3：genderテーブルを追加し、内部結合させて性別名を取得する。  
方法4：そもそもDBを変更して"男性""女性"で登録する。
</div>
</details>
一応現状は方法2を使って性別名で表記できてます。  

↓↓↓  
◇解決◇(2019/09/18)  
対応した文字列へ変換するConversionクラスを作成。  
Controllerで@AutowiredでBean化し、Vで呼び出す。  
◆改善◆  
Conversionクラスでif文を使っていた処理をMapを使用した処理へ変更。  
処理の中身だけ変更し、呼び出し方は現状では変更してません。  

社員名検索、部署名検索実装。  
社員名検索時未入力だと全件表示させてます。  
→現状Controller内でif分岐させています。  
</div>
</details>  


<details>
<summary>・2019/09/18</summary>
<div>

新規登録処理を作成。  
登録処理時にbirthdayがutil型で変な値になっているためsqlに入らずエラーが起きる。  
前回の作成物ではDTOの日付はutilで動いていたので、データバインドやバリデーション等で対処出来るはず。  
→一応解決済み。(2019/09/19)  
　checkからcompleteに遷移する時にエラーが出る為、checkからControllerに送る<input type="hidden">のvalueをフォーマットして送信した所登録されました。  
DBの値も正しい形式で登録されてます。  
</div>
</details>  


<details>
<summary>・2019/09/19</summary>
<div>

削除処理を作成。URLにCtrlと付いているのがイケてなく感じたので、名前を変更。  
**疑問メモ**  
htmlのラジオボタンやプルダウン形式のフォームに関して、更新の初期画面等で初期値をDBに登録されている値にしたい場合、if文でchecked等を各パターンで作成すれば出来るがifを使わないで処理できるかどうか模索中。  
→jQueryで出来そう・・・？  
↓   
→jQueryで出来ました！  
　jsファイルを読み込む場所をheadタグ内にすると反映されない為、ページ最下部に記述してます。(2019/09/20)  
</div>
</details>  


<details>
<summary>・2019/09/20</summary>
<div>

更新処理作成。  
確認画面から入力フォームに戻った際に生年月日の初期値が表示されない不具合を修正。  
新規登録、更新処理での入力チェック実装。エラーメッセージは入力項目毎に個別で出力させています。  
</div>
</details>  


<details>
<summary>・2019/09/24</summary>
<div>

ログイン、ログアウト処理実装。  
ログアウトが現状Getで処理してるのでPost処理へ変更予定。  
→Post処理で作成。レイアウトをCSSで修正。  
ログイン処理はセッション関係がこれからです。  
入力フォームにて部署名が送信された値で初期選択されていなかった不具合を修正。  
エラーページ作成。  
ログイン中のempIdはControllerで取得出来たので。もう少し試行錯誤。  
</div>
</details>


<details>
<summary>・2019/09/25 ←【New!】</summary>
<div>

ログインの次の処理でIdとNameをセッションに格納、各メソッドでmodelにセットしてhtmlに渡すというちょっと強引な方法ですが動作は確認出来てます。  
更新時、呼び出したIDとログイン中のIDを比較して同じ値であればsessionの中身も更新するようにしてます。  
併せて、ヘッダー部のempNameを押下すると更新画面に進めるようにしました。  
権限によって遷移できるページ、表示させる内容を変更しました。  
新規登録：管理者のみ  
更新時の権限の変更：管理者のみ  
一覧表示：一般権限は一部制限あり  

パスワード再設定ページ作成着手。  
indexから遷移してempIdとempNameの２つで参照して、該当データがあれば新しいパスワードへ更新する機能。  
入力画面から確認画面への遷移は一旦出来ました。一致するデータがない場合に入力ページに戻す処理をしてますが、!= nullでは上手く動かないかも？（ページ遷移は期待通りだが作成したエラーメッセージが表示されていない）  
ひとまず正常処理で最後まで進めるように作成してみます。  

</div>
</details>


<details>
<summary>[追加実装] ←【New!】</summary>
<div>

・検索結果の件数を表示。  
・検索結果0件だった場合の表示。  
・検索内容も併せて表示。  
</div>
</details>


<details>
<summary>[未実装]</summary>
<div>

・その他、細かい追加機能etc  
</div>
</details>  


<details>
<summary></summary>
<div>

</div>
</details>