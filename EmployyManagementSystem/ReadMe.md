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
・2019/09/13  
indexページから一覧表示への画面遷移完了。  
ログイン処理はまだ実装していないので、ログインは素通り。  

・2019/09/17  
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

◇解決◇(2019/09/18)  
対応した文字列へ変換するConversionクラスを作成。  
Controllerで@AutowiredでBean化し、Vで呼び出す。  
◆改善◆ ←**New!**  
Conversionクラスでif文を使っていた処理をMapを使用した処理へ変更。  
処理の中身だけ変更し、呼び出し方は現状では変更してません。  

社員名検索、部署名検索実装。  
社員名検索時未入力だと全件表示させてます。  
→現状Controller内でif分岐させています。  

・2019/09/18  
新規登録処理を作成。  
登録処理時にbirthdayがutil型で変な値になっているためsqlに入らずエラーが起きる。  
前回の作成物ではDTOの日付はutilで動いていたので、データバインドやバリデーション等で対処出来るはず。  
→一応解決済み。(2019/09/19) ←**New!**  
　checkからcompleteに遷移する時にエラーが出る為、checkからControllerに送る<input type="hidden">のvalueをフォーマットして送信した所登録されました。  
DBの値も正しい形式で登録されてます。  

・2019/09/19 ←**New!**  
削除処理を作成。URLにCtrlと付いているのがイケてなく感じたので、名前を変更。
**疑問メモ**  
htmlのラジオボタンやプルダウン形式のフォームに関して、更新の初期画面等で初期値をDBに登録されている値にしたい場合、if文でselectedを各パターンで作成すれば出来るがifを使わないで処理できるかどうか模索中

