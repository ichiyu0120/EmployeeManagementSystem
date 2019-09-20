/**
 * 送信されてきたGenderの値を初期値に設定する
 */
var gender = 'input[value="'+$('input:hidden[name="hiddenGender"]').val()+'"]';
$(gender).prop('checked',true);

/**
 * 送信されてきたroleの値を初期値に設定する
 */
var role = 'input[value="'+$('input:hidden[name="hiddenRole"]').val()+'"]';
$(role).prop('checked',true);

/**
 * 送信されてきたdeptIdの値を初期選択する
 */
var deptId = $('input:hidden[name="hiddenDeptId"]').val()
$('#dept_id').val(deptId);
