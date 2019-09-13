**☆社員管理システム☆**  

**2019/09/13　プロジェクト作成**  
　[DBの中身]
　　Empテーブル  
　　　emp_id →int 自動採番 主キー  
　　　emp_pass →nvarchar(Max) NN  
　　　emp_name →nvarchar(30) NN  
　　　gendere →int NN  
　　　address →nvarchar(60) NN  
　　　birthday →Date NN  
　　　role →nvarchar(20) NN  
　　　dept_id →int NN Departmentのdept_idと関連付け  
　　　enable →bit NN 初期値true  

　　Departmentテーブル  
　　　dept_id →int 自動採番 主キー  
　　　dept_name →nvarchar(15) NN  

**メモ**  
・2019/09/13  
indexページから一覧表示への画面遷移完了。  
上記で使用するRepository,Model作成済み。  
ListController、EmpServiceに追記してlist.htmlを変更すればDBのデータを一覧表示出来るはず。  
ログイン処理はまだ実装していないので、ログインは素通り。  
次回は、ListController,EmpService,list.htmlの編集から始める。