package com.example.demo.form;


import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserForm {
	
	@Size(min = 1, max = 200)
	private String name;
	
	@Size(min = 1, max = 200)
	@Email //メールアドレスの形式であることをチェックするアノテーション
	private String email;

}

//javax.validationが見つからないとなっていたけど、外部ライブラリで
//validation-api-2.0.1.Final.jarファイルを追加したらエラー解消した
